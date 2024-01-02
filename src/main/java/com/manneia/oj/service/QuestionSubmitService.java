package com.manneia.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manneia.oj.model.dto.question.QuestionQueryRequest;
import com.manneia.oj.model.dto.qustionsubmit.QuestionSubmitAddRequest;
import com.manneia.oj.model.dto.qustionsubmit.QuestionSubmitQueryRequest;
import com.manneia.oj.model.entity.Question;
import com.manneia.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manneia.oj.model.entity.User;
import com.manneia.oj.model.vo.QuestionSubmitVo;
import com.manneia.oj.model.vo.QuestionVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkx
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2023-12-30 20:55:37
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser                用户信息
     * @return 返回提交结果的id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest 题目提交查询条件
     * @return 返回查询语句
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目提交封装
     *
     * @param questionSubmit 题目提交
     * @param loginUser  当前登录用户
     * @return 返回题目封装
     */
    QuestionSubmitVo getQuestionSubmitVo(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目提交封装
     *
     * @param questionSubmitPage 题目提交分页
     * @param loginUser      当前登录用户
     * @return 返回题目封装分页
     */
    Page<QuestionSubmitVo> getQuestionSubmitVoPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
