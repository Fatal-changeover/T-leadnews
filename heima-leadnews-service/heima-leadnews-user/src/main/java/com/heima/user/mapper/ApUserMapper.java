package com.heima.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.user.pojos.ApUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ApUserMapper
 * Package: com.heima.user.mapper
 * Description:
 *
 * @Author R
 * @Create 2024/5/12 17:19
 * @Version 1.0
 */
@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {

}
