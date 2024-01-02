package com.manneia.oj.model.vo;

import cn.hutool.json.JSONUtil;
import com.manneia.oj.model.entity.Post;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 帖子视图
 *
 * @author <a href="https://github.com/manneia">程序员manneia</a>
 */
@Data
public class PostVo implements Serializable {

    /**
     * id
     */
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
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 创建人信息
     */
    private UserVo user;

    /**
     * 是否已点赞
     */
    private Boolean hasThumb;

    /**
     * 是否已收藏
     */
    private Boolean hasFavour;

    /**
     * 包装类转对象
     *
     * @param postVO 帖子封装类
     * @return 返回对象
     */
    public static Post voToObj(PostVo postVO) {
        if (postVO == null) {
            return null;
        }
        Post post = new Post();
        BeanUtils.copyProperties(postVO, post);
        List<String> tagList = postVO.getTagList();
        post.setTags(JSONUtil.toJsonStr(tagList));
        return post;
    }

    /**
     * 对象转包装类
     *
     * @param post 帖子
     * @return 返回包装类
     */
    public static PostVo objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostVo postVO = new PostVo();
        BeanUtils.copyProperties(post, postVO);
        postVO.setTagList(JSONUtil.toList(post.getTags(), String.class));
        return postVO;
    }
}
