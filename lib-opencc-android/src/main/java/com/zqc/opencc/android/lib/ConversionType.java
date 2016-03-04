package com.zqc.opencc.android.lib;

/**
 * Created by zhangqichuan on 2/3/16.
 */
public enum ConversionType {
    HK2S,
    S2HK,
    S2T,
    S2TW,
    S2TWP,
    T2HK,
    T2S,
    T2TW,
    TW2S,
    TW2SP;

    public String getValue() {
        if (this == HK2S) {
            return "hk2s.json";
        } else if (this == S2HK) {
            return "s2hk.json";
        } else if (this == S2T) {
            return "s2t.json";
        } else if (this == S2TW) {
            return "s2tw.json";
        } else if (this == S2TWP) {
            return "s2twp.json";
        } else if (this == T2HK) {
            return "t2hk.json";
        } else if (this == T2S) {
            return "t2s.json";
        } else if (this == T2TW) {
            return "t2tw.json";
        } else if (this == TW2S) {
            return "tw2s.json";
        } else if (this == TW2SP) {
            return "tw2sp.json";
        }
        return "s2t.json";
    }
}