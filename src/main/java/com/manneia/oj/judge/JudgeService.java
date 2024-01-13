package com.manneia.oj.judge;

import com.manneia.oj.model.entity.QuestionSubmit;

/**
 * @author lkx
 */
public interface JudgeService {

    /**
     * 执行判题
     * @param questionSubmitId 题目提交id
     * @return 返回执行结果
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
