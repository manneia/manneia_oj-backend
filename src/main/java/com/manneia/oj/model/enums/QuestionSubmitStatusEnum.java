package com.manneia.oj.model.enums;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目提交状态枚举
 *
 * @author lkx
 */

@Getter
public enum QuestionSubmitStatusEnum {
    WAITING("等待中", 0),
    RUNNING("判题中", 1),
    SUCCEED("成功", 2),
    ERROR("失败", 3);

    private final String text;

    private final Integer value;

    QuestionSubmitStatusEnum(final String text, final Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取所有状态的value
     *
     * @return 所有状态的value
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据value获取枚举
     *
     * @param value value
     * @return 返回枚举
     */
    public static QuestionSubmitStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitStatusEnum questionSubmitStatusEnum : QuestionSubmitStatusEnum.values()) {
            if (questionSubmitStatusEnum.value.equals(value)) {
                return questionSubmitStatusEnum;
            }
        }
        return null;
    }
}
