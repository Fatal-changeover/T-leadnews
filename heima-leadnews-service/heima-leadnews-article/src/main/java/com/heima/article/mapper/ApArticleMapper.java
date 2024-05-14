package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: ApArticleMapper
 * Package: com.heima.article.mapper
 * Description:
 *
 * @Author R
 * @Create 2024/5/13 9:58
 * @Version 1.0
 */

/**
 * 加载文章列表
 * dto
 * type 1加载更多 2加载最新
 */
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    public List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}
