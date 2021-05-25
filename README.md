# Introduction

An Android port to [OPENCC](https://github.com/BYVoid/OpenCC), a library to convert Simplified Chinese to Traditional Chinese and vice versa. In additional, it also adopts the regional vocabulary and terminology interchangeably during conversion among Mainland China Simplified Chinese, Taiwan Traditional Chinese and Hong Kong Traditional Chinese.

## Note
This project uses git submodules to download the source code from OpenCC, please use --recursive flag when cloning this project

```
 git clone git@github.com:qichuan/android-opencc.git --recursive

```

## Example
```
滑鼠裡面的矽二極體壞了，導致游標解析度降低。
``` 
in Traditional Taiwan Chinese
will be converted to 
```
鼠标里面的硅二极管坏了，导致光标分辨率降低。
```
in Simplified Chinese and using Mainland China terminology

# Installation

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
	...
	    maven { url 'https://jitpack.io' }
	}
}
```

```
// Add the dependency
dependencies {
    ...
	implementation 'com.github.qichuan:android-opencc:1.2.0'
}
```

# Usage
To use Chinese converter is easy, just call `ChineseConverter.convert(originalText, conversionType, context));`

## Supported conversation types
- HK2S, Traditional Chinese (Hong Kong Standard) to Simplified Chinese 香港繁體（香港小學學習字詞表標準）到簡體
- HK2T, Traditional Chinese (Hong Kong variant) to Traditional Chinese 香港繁體（香港小學學習字詞表標準）到繁體
- JP2T, New Japanese Kanji (Shinjitai) to Traditional Chinese Characters (Kyūjitai) 日本漢字到繁體
- S2HK, Simplified Chinese to Traditional Chinese (Hong Kong Standard) 簡體到香港繁體（香港小學學習字詞表標準）
- S2T, Simplified Chinese to Traditional Chinese 簡體到繁體
- S2TW, Simplified Chinese to Traditional Chinese (Taiwan Standard) 簡體到臺灣正體
- S2TWP, Simplified Chinese to Traditional Chinese (Taiwan Standard) with Taiwanese idiom 簡體到繁體（臺灣正體標準）並轉換爲臺灣常用詞彙
- T2HK, Traditional Chinese to Traditional Chinese (Hong Kong Standard) 繁體到香港繁體（香港小學學習字詞表標準）
- T2S, Traditional Chinese to Simplified Chinese 繁體到簡體
- T2TW, Traditional Chinese to Traditional Chinese (Taiwan Standard) 繁體臺灣正體
- TW2S, Traditional Chinese (Taiwan Standard) to Simplified Chinese 臺灣正體到簡體
- T2JP, Traditional Chinese Characters (Kyūjitai) to New Japanese Kanji (Shinjitai) 繁體到日本漢字
- TW2T, Traditional Chinese (Taiwan standard) to Traditional Chinese 臺灣正體到繁體
- TW2SP, Traditional Chinese (Taiwan Standard) to Simplified Chinese with Mainland Chinese idiom 繁體（臺灣正體標準）到簡體並轉換爲中國大陸常用詞彙

# Explanation

android-opencc leverages on the original OpenCC project and invoke the native code via JNI, the text phrase dictionary files are shipped in the assets folder. Android NDK does not provide means to create and read file streams from directly from assets folder, therefore the dictionary files are then copied to the application data folder in the first call of `ChineseConverter.convert()`

If you need to update the dictionary files in the assets folder, please remember to call `ChineseConverter.clearDictDataFolder()` once to clear the old dictionary files, so the new dictionary files will be effective in the next `ChineseConverter.convert()` call.

# Compilation

You need the Android NDK for compilation, please download the [NDK](http://developer.android.com/ndk/downloads/index.html) and configure the path to NDK in `local.properties` file.

# Example apk

[Download here](https://www.dropbox.com/s/0qzcmchqf5hqyit/android-opencc-0.6.0.apk?dl=1)

Feel free to feedback if there are any issues, and hope this library can be useful for you.

# References
- https://github.com/BYVoid/OpenCC
- https://github.com/gelosie/OpenCC/tree/master/iOS
