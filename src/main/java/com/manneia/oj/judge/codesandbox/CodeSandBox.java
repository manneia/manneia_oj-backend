package com.manneia.oj.judge.codesandbox;

import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author lkx
 */
public interface CodeSandBox {

    /**
     * 执行代码
     * @param executeCodeRequest 执行代码请求
     * @return 返回执行结果
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
