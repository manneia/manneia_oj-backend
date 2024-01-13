package com.manneia.oj.judge.strategy;

import com.manneia.oj.model.dto.question.JudgeCase;
import com.manneia.oj.model.dto.qustionsubmit.JudgeInfo;
import com.manneia.oj.model.entity.Question;
import com.manneia.oj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文 (用于定义在策略中传递的参数)
 * @author lkx
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;

    private Question question;
}
