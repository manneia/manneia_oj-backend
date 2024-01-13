package com.manneia.oj.judge.codesandbox.impl;

import com.manneia.oj.judge.codesandbox.CodeSandBox;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱 (调用外部代码沙箱)
 * @author lkx
 */
public class ThirdPartyCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("thirdParty code");
        return null;    }
}
