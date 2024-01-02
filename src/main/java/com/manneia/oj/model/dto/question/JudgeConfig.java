package com.manneia.oj.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 *
 * @author lkx
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制 (ms)
     */
    private long timeLimit;

    /**
     * 内存限制 (kb)
     */
    private long memoryLimit;

    /**
     * 堆栈限制 (kb)
     */
    private long stackLimit;
}
