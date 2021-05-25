#include <jni.h>
#include <malloc.h>
#include "Converter.hpp"
#include "Config.hpp"

extern "C"
jstring
Java_com_zqc_opencc_android_lib_ChineseConverter_convert(
        JNIEnv *env, jclass type, jstring text_, jstring configFile_, jstring absoluteDataFolderPath_) {
    const char *text = env->GetStringUTFChars(text_, 0);
    const char *configFile = env->GetStringUTFChars(configFile_, 0);
    const char *absoluteDataFolderPath = env->GetStringUTFChars(absoluteDataFolderPath_, 0);

    opencc::Config config;
    opencc::ConverterPtr converter = config.NewFromFile(std::string(absoluteDataFolderPath) + "/" + std::string(configFile));

    env->ReleaseStringUTFChars(text_, text);
    env->ReleaseStringUTFChars(configFile_, configFile);
    env->ReleaseStringUTFChars(absoluteDataFolderPath_, absoluteDataFolderPath);

    return env->NewStringUTF(converter->Convert(text).c_str());
}