#include <jni.h>
#include <malloc.h>
#include "Converter.hpp"
#include "Config.hpp"

opencc::ConverterPtr converter;
opencc::Config config;

extern "C"
jstring
Java_com_zqc_opencc_android_lib_ChineseConverter_convert(
        JNIEnv *env, jclass type, jstring text_, jstring configFile_, jstring absoluteDataFolderPath_) {
    const char *text = env->GetStringUTFChars(text_, 0);
    const char *configFile = env->GetStringUTFChars(configFile_, 0);
    const char *absoluteDataFolderPath = env->GetStringUTFChars(absoluteDataFolderPath_, 0);


    string folder(absoluteDataFolderPath);
    string file(configFile);

    converter = config.NewFromFile(folder + "/" + file);
    std::string converted = converter->Convert(text);

    // TODO
    env->ReleaseStringUTFChars(text_, text);
    env->ReleaseStringUTFChars(configFile_, configFile);
    env->ReleaseStringUTFChars(absoluteDataFolderPath_, absoluteDataFolderPath);

    return env->NewStringUTF(converted.c_str());
}