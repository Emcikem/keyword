package com.lyq.sensitive.word.constant.emnu;

/**
 * @author Emcikem
 * @create 2022/4/30
 * @desc
 */
public enum FormatEnum {

    CASECHAR("CASECHAR", "忽略大小写"),
    CHINESESTYLE("CHINESESTYLE", "忽略简繁体"),
    ENGLISHSTYLE("ENGLISHSTYLE", "忽略英文"),
    NUMBERSTYLE("NUMBERSTYLE", "忽略数字"),
    WIDTHCHAR("WIDTHCHAR", "忽略全半角符")
    ;

    private final String type;

    private final String desc;

    FormatEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
