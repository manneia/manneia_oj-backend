package com.manneia.oj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manneia.oj.common.BaseResponse;
import com.manneia.oj.common.ErrorCode;
import com.manneia.oj.common.ResultUtils;
import com.manneia.oj.exception.BusinessException;
import com.manneia.oj.model.dto.qustionsubmit.QuestionSubmitAddRequest;
import com.manneia.oj.model.dto.qustionsubmit.QuestionSubmitQueryRequest;
import com.manneia.oj.model.entity.QuestionSubmit;
import com.manneia.oj.model.entity.User;
import com.manneia.oj.model.vo.QuestionSubmitVo;
import com.manneia.oj.service.QuestionSubmitService;
import com.manneia.oj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/manneia">程序员manneia</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest 题目提交创建请求
     * @param request                  网路请求
     * @return resultNum 返回题目结交结果
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 获取题目提交列表
     *
     * @param questionSubmitQueryRequest 题目提交查询请求
     * @param request                    网路请求
     * @return resultNum 返回题目提交列表
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVo>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        int current = questionSubmitQueryRequest.getCurrent();
        int pageSize = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, pageSize),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVoPage(questionSubmitPage, loginUser));
    }
}
