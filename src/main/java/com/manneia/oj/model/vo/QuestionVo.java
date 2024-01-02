package com.manneia.oj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.manneia.oj.model.dto.question.JudgeConfig;
import com.manneia.oj.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目返回封装类
 *
 * @author lkx
 * @TableName question
 */
@Data
public class QuestionVo implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置 (json 对象)
     */
    private JudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
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
     * 创建题目人的信息
     */
    private UserVo userVo;

    private static final long serialVersionUID = 1L;

    /**
     * 包装类转对象
     *
     * @param questionVo 题目封装类
     * @return 返回对象
     */
    public static Question voToObj(QuestionVo questionVo) {
        if (questionVo == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVo, question);
        List<String> tagList = questionVo.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        JudgeConfig config = questionVo.getJudgeConfig();
        if (config != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(config));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question 题目
     * @return 返回转换后的Vo对象
     */
    public static QuestionVo objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question, questionVo);
        questionVo.setTags(JSONUtil.toList(question.getTags(), String.class));
        questionVo.setJudgeConfig(JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class));
        return questionVo;
    }
}