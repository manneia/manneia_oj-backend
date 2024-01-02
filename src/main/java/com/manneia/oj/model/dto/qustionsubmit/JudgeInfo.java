package com.manneia.oj.model.dto.qustionsubmit;

import lombok.Data;

/**
 * 题目信息
 *
 * @author lkx
 */
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 销毁内存 (kb)
     */
    private long memory;

    /**
     * 消耗时间 (ms)
     */
    private long time;
}
