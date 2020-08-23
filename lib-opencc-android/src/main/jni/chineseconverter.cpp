#include <jni.h>
#include <malloc.h>
#include "Converter.hpp"
#include "Config.hpp"

opencc::ConverterPtr converter[13];

extern "C"
jstring
Java_com_zqc_opencc_android_lib_ChineseConverter_convert(
        JNIEnv *env, jclass type, jstring text_, jstring configFile_, int ordinal, jstring absoluteDataFolderPath_) {
    const char *text = env->GetStringUTFChars(text_, nullptr);
    const char *configFile = env->GetStringUTFChars(configFile_, nullptr);
    const char *absoluteDataFolderPath = env->GetStringUTFChars(absoluteDataFolderPath_, nullptr);


    std::string folder(absoluteDataFolderPath);
    std::string file(configFile);

    if (!converter[ordinal]) {
        converter[ordinal] = opencc::Config().NewFromFile(folder + "/" + file);
    }
    std::string converted = converter[ordinal]->Convert(text);

    env->ReleaseStringUTFChars(text_, text);
    env->ReleaseStringUTFChars(configFile_, configFile);
    env->ReleaseStringUTFChars(absoluteDataFolderPath_, absoluteDataFolderPath);

    return env->NewStringUTF(converted.c_str());
}

extern "C"
void
Java_com_zqc_opencc_android_lib_ChineseConverter_initializeConvert(
        JNIEnv *env, jclass type, jstring configFile_, int ordinal, jstring absoluteDataFolderPath_, jboolean force) {
    const char *configFile = env->GetStringUTFChars(configFile_, nullptr);
    const char *absoluteDataFolderPath = env->GetStringUTFChars(absoluteDataFolderPath_, nullptr);


    std::string folder(absoluteDataFolderPath);
    std::string file(configFile);

    if (force || !converter[ordinal]) {
        converter[ordinal] = opencc::Config().NewFromFile(folder + "/" + file);
    }

    env->ReleaseStringUTFChars(configFile_, configFile);
    env->ReleaseStringUTFChars(absoluteDataFolderPath_, absoluteDataFolderPath);
}