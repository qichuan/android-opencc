#include <jni.h>
#include <malloc.h>
#include <string>
#include "Converter.hpp"
#include "Config.hpp"

namespace {

/**
 * RAII wrapper around GetStringUTFChars()/ReleaseStringUTFChars().
 *
 * The buffer returned by GetStringUTFChars() is owned by the JVM and is only
 * guaranteed to stay valid until ReleaseStringUTFChars() is called.  Holding
 * it in a scoped object makes the lifetime correct by construction, so the
 * release can no longer drift above the code that reads the buffer.
 */
class ScopedUtfChars {
public:
    ScopedUtfChars(JNIEnv *env, jstring str)
            : env_(env), str_(str), chars_(env->GetStringUTFChars(str, nullptr)) {}

    ~ScopedUtfChars() {
        if (chars_ != nullptr) {
            env_->ReleaseStringUTFChars(str_, chars_);
        }
    }

    ScopedUtfChars(const ScopedUtfChars &) = delete;
    ScopedUtfChars &operator=(const ScopedUtfChars &) = delete;

    // Null when the JVM failed to allocate the buffer; an OutOfMemoryError is
    // then already pending and must be allowed to propagate.
    bool valid() const { return chars_ != nullptr; }

    const char *c_str() const { return chars_; }

private:
    JNIEnv *env_;
    jstring str_;
    const char *chars_;
};

} // namespace

extern "C"
jstring
Java_com_zqc_opencc_android_lib_ChineseConverter_convert(
        JNIEnv *env, jclass type, jstring text_, jstring configFile_, jstring absoluteDataFolderPath_) {
    ScopedUtfChars text(env, text_);
    ScopedUtfChars configFile(env, configFile_);
    ScopedUtfChars absoluteDataFolderPath(env, absoluteDataFolderPath_);
    if (!text.valid() || !configFile.valid() || !absoluteDataFolderPath.valid()) {
        return nullptr;
    }

    opencc::Config config;
    opencc::ConverterPtr converter = config.NewFromFile(
            std::string(absoluteDataFolderPath.c_str()) + "/" + std::string(configFile.c_str()));

    // Must happen while `text` is still alive: since OpenCC 1.3.2 Convert()
    // takes a std::string_view and borrows the caller's buffer for the whole
    // conversion instead of copying it up front.
    const std::string converted = converter->Convert(text.c_str());

    return env->NewStringUTF(converted.c_str());
}
