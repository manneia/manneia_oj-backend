package com.manneia.oj.judge.codesandbox.impl;

import com.manneia.oj.judge.codesandbox.CodeSandBox;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱 (实际调用接口的沙箱)
 * @author lkx
 */
public class RemoteCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("remote code");
        return null;
    }
}
