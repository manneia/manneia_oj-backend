package com.manneia.oj.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目提交编程语言枚举
 *
 * @author lkx
 */
@Getter
public enum QuestionSubmitLanguageEnum {
    JAVA("java", "java"),
    C("C", "C"),
    CPLUSPLUS("C++", "C++"),
    JAVASCRIPT("javascript", "javascript"),
    RUST("rust", "rust"),
    GOLANG("golang", "golang");


    private final String text;

    private final String value;

    QuestionSubmitLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取所有状态的value
     *
     * @return 所有状态的value
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据value获取枚举
     * @param value value
     * @return 返回枚举
     */
    public static QuestionSubmitLanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitLanguageEnum questionSubmitLanguageEnum : QuestionSubmitLanguageEnum.values()) {
            if (questionSubmitLanguageEnum.value.equals(value)) {
                return questionSubmitLanguageEnum;
            }
        }
        return null;
    }

}
