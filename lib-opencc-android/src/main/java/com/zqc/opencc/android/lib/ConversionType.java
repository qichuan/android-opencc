package com.zqc.opencc.android.lib;

/**
 * Created by zhangqichuan on 2/3/16.
 */
public enum ConversionType {
    HK2S,  //hk2s.json  Traditional Chinese (Hong Kong variant) to Simplified Chinese 香港繁體到簡體
    HK2T,  //hk2t.json  Traditional Chinese (Hong Kong variant) to Traditional Chinese 香港繁體到繁體（OpenCC 標準）
    JP2T,  //jp2t.json  New Japanese Kanji (Shinjitai) to Traditional Chinese Characters (Kyūjitai) 日文新字體到繁體（OpenCC 標準，舊字體）
    S2HK,  //s2hk.json  Simplified Chinese to Traditional Chinese (Hong Kong variant) 簡體到香港繁體
    S2T,   //s2t.json   Simplified Chinese to Traditional Chinese 簡體到繁體
    S2TW,  //s2tw.json  Simplified Chinese to Traditional Chinese (Taiwan Standard) 簡體到臺灣正體
    S2TWP, //s2twp.json Simplified Chinese to Traditional Chinese (Taiwan Standard) with Taiwanese idiom 簡體到繁體（臺灣正體標準）並轉換爲臺灣常用詞彙
    T2HK,  //t2hk.json  Traditional Chinese (OpenCC Standard) to Hong Kong variant 繁體（OpenCC 標準）到香港繁體
    T2JP,  //t2hk.json  Traditional Chinese Characters (Kyūjitai) to New Japanese Kanji (Shinjitai) 繁體（OpenCC 標準，舊字體）到日文新字體
    T2S,   //t2s.json   Traditional Chinese to Simplified Chinese 繁體到簡體
    T2TW,  //t2tw.json  Traditional Chinese to Traditional Chinese (Taiwan Standard) 繁體臺灣正體
    TW2S,  //tw2s.json  Traditional Chinese (Taiwan Standard) to Simplified Chinese 臺灣正體到簡體
    TW2SP, //tw2sp.json Traditional Chinese (Taiwan Standard) to Simplified Chinese with Mainland Chinese idiom 繁體（臺灣正體標準）到簡體並轉換爲中國大陸常用詞彙
    TW2T;  //tw2sp.json Traditional Chinese (Taiwan standard) to Traditional Chinese 臺灣正體到繁體（OpenCC 標準）

    public String getValue() {
        return name().toLowerCase() + ".json";
    }
}
