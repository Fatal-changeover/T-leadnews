package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heima.common.constants.ArticleConstants;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * ClassName: ApArticleServiceImpl
 * Package: com.heima.article.service.impl
 * Description:
 *
 * @Author R
 * @Create 2024/5/13 10:04
 * @Version 1.0
 */
@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    private final static  short MAX_PAGE_SIZE = 50;
    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult load(Short loadtype, ArticleHomeDto dto) {
        //校验参数
        Integer size = dto.getSize();
        if(size == null || size == 0) {
            size = 10;
        }
        //最大size是50以下
        size = Math.min(size,MAX_PAGE_SIZE);
        dto.setSize(size);
        //检验参数-type
        if(!loadtype.equals(ArticleConstants.LOADTYPE_LOAD_MORE) ||!loadtype.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            loadtype = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        if(StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //时间校验
        if(dto.getMaxBehotTime() == null) dto.setMaxBehotTime(new Date());
        if(dto.getMinBehotTime() == null) dto.setMinBehotTime(new Date());

        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadtype);
        ResponseResult responseResult = ResponseResult.okResult(apArticles);
        return responseResult;
    }
}
