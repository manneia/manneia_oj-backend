package com.manneia.oj.judge.codesandbox;

import com.manneia.oj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.manneia.oj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.manneia.oj.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * 代码沙箱工厂
 *
 * @author lkx
 */
public class CodeSandBoxFactory {

    /**
     * 根据类型创建代码沙箱
     * @param type 类型
     * @return 返回代码沙箱实例
     */
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSandBox();
            case "ThirdParty":
                return new ThirdPartyCodeSandBox();
            case "example":
            default:
                return new ExampleCodeSandBox();
        }
    }
}
