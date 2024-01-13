package com.manneia.oj.judge.codesandbox.impl;

import com.manneia.oj.judge.codesandbox.CodeSandBox;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.manneia.oj.model.dto.qustionsubmit.JudgeInfo;
import com.manneia.oj.model.enums.JudgeInfoMessageEnum;
import com.manneia.oj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 *
 * @author lkx
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String language = executeCodeRequest.getLanguage();
        String code = executeCodeRequest.getCode();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutput(inputList);
        executeCodeResponse.setMessage("success");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
