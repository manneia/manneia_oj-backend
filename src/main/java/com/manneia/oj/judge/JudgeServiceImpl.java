package com.manneia.oj.judge;

import cn.hutool.json.JSONUtil;
import com.manneia.oj.common.ErrorCode;
import com.manneia.oj.exception.BusinessException;
import com.manneia.oj.judge.codesandbox.CodeSandBox;
import com.manneia.oj.judge.codesandbox.CodeSandBoxFactory;
import com.manneia.oj.judge.codesandbox.CodeSandBoxProxy;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.manneia.oj.judge.strategy.DefaultJudgeStrategy;
import com.manneia.oj.judge.strategy.JudgeContext;
import com.manneia.oj.model.dto.question.JudgeCase;
import com.manneia.oj.model.dto.qustionsubmit.JudgeInfo;
import com.manneia.oj.model.entity.Question;
import com.manneia.oj.model.entity.QuestionSubmit;
import com.manneia.oj.model.enums.QuestionSubmitStatusEnum;
import com.manneia.oj.model.vo.QuestionSubmitVo;
import com.manneia.oj.service.QuestionService;
import com.manneia.oj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lkx
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //        1.   传入题目的提交id,获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);

        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        Long id = questionSubmit.getId();
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(id);
        if (question == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //        2.   如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        //        3.   更改判题（题目提交）的状态为“判题中“，防止重复执行，也上用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        //        4.   调用沙箱，获取到执行结果
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取测试用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        ExecuteCodeRequest request = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(request);
        //        5.   根据沙箱的执行结果，设置题目的判题状态和信息
        List<String> outputList = executeCodeResponse.getOutput();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeContext.setQuestion(question);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        //        6. 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        return questionSubmitService.getById(questionId);
    }
}
