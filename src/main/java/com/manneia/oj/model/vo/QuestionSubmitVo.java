package com.manneia.oj.model.vo;

import cn.hutool.json.JSONUtil;
import com.manneia.oj.model.dto.qustionsubmit.JudgeInfo;
import com.manneia.oj.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lkx
 */
@Data
public class QuestionSubmitVo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 代码
     */
    private String code;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态
     */
    private Integer status;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交用户信息
     */
    private UserVo userVo;

    /**
     * 题目信息
     */
    private QuestionVo questionVo;

    private static final long serialVersionUID = 1L;

    /**
     * 包装类转对象
     *
     * @param questionSubmitVo 题目提交封装类
     * @return 返回对象
     */
    public static QuestionSubmit voToObj(QuestionSubmitVo questionSubmitVo) {
        if (questionSubmitVo == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVo, questionSubmit);
        JudgeInfo judgeInfo = questionSubmitVo.getJudgeInfo();
        if (judgeInfo != null) {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        }
        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit 题目提交
     * @return 返回转换后的Vo对象
     */
    public static QuestionSubmitVo objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVo questionSubmitVo = new QuestionSubmitVo();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVo);
        questionSubmitVo.setJudgeInfo(JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class));
        return questionSubmitVo;
    }
}
