package com.manneia.oj.judge.codesandbox;

import com.manneia.oj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.manneia.oj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.manneia.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.manneia.oj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodeSandBoxTest {

    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void executeCode() {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String type = sc.next();
            CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
            String code = "int main() { return}";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3,4");
            ExecuteCodeRequest request = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
            ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(request);
            System.out.println(executeCodeResponse);
        }
    }


    @Test
    void executeCodeByValue() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        String code = "int main() { return}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3,4");
        ExecuteCodeRequest request = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(request);
        System.out.println(executeCodeResponse);
    }

    @Test
    void executeCodeByProxy() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        String code = "int main() { return}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3,4");
        ExecuteCodeRequest request = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(request);
        System.out.println(executeCodeResponse);
    }
}