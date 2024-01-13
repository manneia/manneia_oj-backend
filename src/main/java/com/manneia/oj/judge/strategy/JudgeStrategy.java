package com.manneia.oj.judge.strategy;

import com.manneia.oj.model.dto.qustionsubmit.JudgeInfo;

/**
 * 判题策略
 *
 * @author lkx
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext 判题上下文
     * @return 返回判题信息
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
