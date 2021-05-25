package com.zqc.opencc.andorid.lib;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.zqc.opencc.android.lib.ChineseConverter;
import com.zqc.opencc.android.lib.ConversionType;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by zhangqichuan on 7/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @After
    public void tearDown() {
        ChineseConverter.clearDictDataFolder(context);
    }

    @Test
    public void testHK2S() {
        baseTest("虛偽歎息\n" +
                        "潮濕灶台\n" +
                        "沙河涌洶湧的波浪",

                "虚伪叹息\n" +
                        "潮湿灶台\n" +
                        "沙河涌汹涌的波浪", ConversionType.HK2S);
    }

    @Test
    public void testS2HK() {
        baseTest("虚伪叹息\n" +
                        "潮湿灶台\n" +
                        "沙河涌汹涌的波浪",

                "虛偽歎息\n" +
                        "潮濕灶台\n" +
                        "沙河涌洶湧的波浪", ConversionType.S2HK);
    }

    @Test
    public void testS2T() {
        baseTest("夸夸其谈 夸父逐日\n" +
                        "我干什么不干你事。\n" +
                        "太后的头发很干燥。\n" +
                        "燕燕于飞，差池其羽。之子于归，远送于野。\n" +
                        "请成相，世之殃，愚暗愚暗堕贤良。人主无贤，如瞽无相何伥伥！请布基，慎圣人，愚而自专事不治。主忌苟胜，群臣莫谏必逢灾。\n" +
                        "曾经有一份真诚的爱情放在我面前，我没有珍惜，等我失去的时候我才后悔莫及。人事间最痛苦的事莫过于此。如果上天能够给我一个再来一次得机会，我会对那个女孩子说三个字，我爱你。如果非要在这份爱上加个期限，我希望是，一万年。\n" +
                        "新的理论被发现了。\n" +
                        "鲶鱼和鲇鱼是一种生物。\n" +
                        "金胄不是金色的甲胄。",

                "誇誇其談 夸父逐日\n" +
                        "我幹什麼不干你事。\n" +
                        "太后的頭髮很乾燥。\n" +
                        "燕燕于飛，差池其羽。之子于歸，遠送於野。\n" +
                        "請成相，世之殃，愚闇愚闇墮賢良。人主無賢，如瞽無相何倀倀！請布基，慎聖人，愚而自專事不治。主忌苟勝，羣臣莫諫必逢災。\n" +
                        "曾經有一份真誠的愛情放在我面前，我沒有珍惜，等我失去的時候我才後悔莫及。人事間最痛苦的事莫過於此。如果上天能夠給我一個再來一次得機會，我會對那個女孩子說三個字，我愛你。如果非要在這份愛上加個期限，我希望是，一萬年。\n" +
                        "新的理論被發現了。\n" +
                        "鮎魚和鮎魚是一種生物。\n" +
                        "金胄不是金色的甲冑。", ConversionType.S2T);
    }

    @Test
    public void testS2TW() {
        baseTest("着装污染虚伪发泄棱柱群众里面\n" +
                        "鲶鱼和鲇鱼是一种生物。",

                "著裝汙染虛偽發洩稜柱群眾裡面\n" +
                        "鯰魚和鯰魚是一種生物。", ConversionType.S2TW);
    }

    @Test
    public void testS2TWP() {
        baseTest("鼠标里面的硅二极管坏了，导致光标分辨率降低。\n" +
                        "我们在老挝的服务器的硬盘需要使用互联网算法软件解决异步的问题。\n" +
                        "为什么你在床里面睡着？",

                "滑鼠裡面的矽二極體壞了，導致游標解析度降低。\n" +
                        "我們在寮國的伺服器的硬碟需要使用網際網路演算法軟體解決非同步的問題。\n" +
                        "為什麼你在床裡面睡著？", ConversionType.S2TWP);
    }

    @Test
    public void testT2S() {
        baseTest("曾經有一份真誠的愛情放在我面前，我沒有珍惜，等我失去的時候我才後悔莫及。人事間最痛苦的事莫過於此。如果上天能夠給我一個再來一次得機會，我會對那個女孩子說三個字，我愛你。如果非要在這份愛上加個期限，我希望是，一萬年。",

                "曾经有一份真诚的爱情放在我面前，我没有珍惜，等我失去的时候我才后悔莫及。人事间最痛苦的事莫过于此。如果上天能够给我一个再来一次得机会，我会对那个女孩子说三个字，我爱你。如果非要在这份爱上加个期限，我希望是，一万年。",
                ConversionType.T2S);
    }

    @Test
    public void testTW2S() {
        baseTest("著裝著作汙染虛偽發洩稜柱群眾裡面",

                "着装著作污染虚伪发泄棱柱群众里面", ConversionType.TW2S);
    }

    @Test
    public void testTW2SP() {
        baseTest("滑鼠裡面的矽二極體壞了，導致游標解析度降低。\n" +
                        "我們在寮國的伺服器的硬碟需要使用網際網路演算法軟體解決非同步的問題。\n" +
                        "為什麼你在床裡面睡著？",

                "鼠标里面的硅二极管坏了，导致光标分辨率降低。\n" +
                        "我们在老挝的服务器的硬盘需要使用互联网算法软件解决异步的问题。\n" +
                        "为什么你在床里面睡着？",
                ConversionType.TW2SP);
    }

    private void baseTest(String originalText, String expectedText, ConversionType conversionType) {
        Assert.assertEquals(expectedText, ChineseConverter.convert(originalText, conversionType, context));
    }
}
