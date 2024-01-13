package com.manneia.oj;

import com.manneia.oj.judge.codesandbox.CodeSandBox;
import com.manneia.oj.judge.codesandbox.CodeSandBoxFactory;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.manneia.oj.model.enums.QuestionSubmitLanguageEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 主类（项目启动入口）
 *
 * @author <a href="https://github.com/manneia">程序员manneia</a>
 */
// todo 如需开启 Redis，须移除 exclude 中的内容
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.manneia.oj.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
