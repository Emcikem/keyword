package com.lyq.sensitive.word.utils;

import java.util.HashMap;
import java.util.Map;

public final class NumUtils {

    private static final String NUM_ONE = "⓪０零º₀⓿○" +
            "１２３４５６７８９" +
            "一二三四五六七八九" +
            "壹贰叁肆伍陆柒捌玖" +
            "¹²³⁴⁵⁶⁷⁸⁹" +
            "₁₂₃₄₅₆₇₈₉" +
            "①②③④⑤⑥⑦⑧⑨" +
            "⑴⑵⑶⑷⑸⑹⑺⑻⑼" +
            "⒈⒉⒊⒋⒌⒍⒎⒏⒐" +
            "❶❷❸❹❺❻❼❽❾" +
            "➀➁➂➃➄➅➆➇➈" +
            "➊➋➌➍➎➏➐➑➒" +
            "㈠㈡㈢㈣㈤㈥㈦㈧㈨" +
            "⓵⓶⓷⓸⓹⓺⓻⓼⓽" +
            "㊀㊁㊂㊃㊄㊅㊆㊇㊈" +
            "ⅰⅱⅲⅳⅴⅵⅶⅷⅸ" +
            "ⅠⅡⅢⅣⅤⅥⅦⅧⅨ";

    private static final String NUM_TWO = "0000000"+
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789";

    /**
     * 英文字母 map
     * @since 0.0.4
     */
    private static final Map<Character, Character> NUMBER_MAP = new HashMap<>(NUM_ONE.length());

    static {
        for(int i = 0; i < NUM_ONE.length(); i++) {
            NUMBER_MAP.put(NUM_ONE.charAt(i), NUM_TWO.charAt(i));
        }
    }

    /**
     * 映射后的 char
     */
    public static Character getMappingChar(final Character character) {
        final Character mapChar = NUMBER_MAP.get(character);
        if(mapChar != null) {
            return mapChar;
        }

        return character;
    }
}
