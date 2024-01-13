package com.manneia.oj.judge;

import com.manneia.oj.judge.strategy.DefaultJudgeStrategy;
import com.manneia.oj.judge.strategy.JavaJudgeStrategy;
import com.manneia.oj.judge.strategy.JudgeContext;
import com.manneia.oj.judge.strategy.JudgeStrategy;
import com.manneia.oj.model.dto.qustionsubmit.JudgeInfo;
import com.manneia.oj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理 (简化调用)
 *
 * @author lkx
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext 判题上下文
     * @return 返回判题信息
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
