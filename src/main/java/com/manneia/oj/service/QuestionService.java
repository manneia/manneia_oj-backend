package com.manneia.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manneia.oj.model.dto.question.QuestionQueryRequest;
import com.manneia.oj.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manneia.oj.model.vo.QuestionVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkx
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2023-12-30 20:54:43
 */
public interface QuestionService extends IService<Question> {

    /**
     * 校验题目合法
     *
     * @param question 题目
     * @param add      是否添加
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest 题目查询条件
     * @return 返回查询语句
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param question 题目
     * @param request  请求
     * @return 返回题目封装
     */
    QuestionVo getQuestionVo(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param questionPage 题目分页
     * @param request      请求
     * @return 返回题目封装分页
     */
    Page<QuestionVo> getQuestionVoPage(Page<Question> questionPage, HttpServletRequest request);
}
