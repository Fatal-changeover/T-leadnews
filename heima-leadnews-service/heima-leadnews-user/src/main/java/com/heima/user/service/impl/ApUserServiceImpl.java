package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.retry.RetryUntilElapsed;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ApUserServiceImpl
 * Package: com.heima.user.service
 * Description:
 *
 * @Author R
 * @Create 2024/5/12 17:20
 * @Version 1.0
 */
@Service
@Transactional
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    @Override
    public ResponseResult login(LoginDto dto) {
        //正常用户
        if(StringUtils.isNotBlank(dto.getPhone()) && StringUtils.isNotBlank(dto.getPassword())){
            ApUser user = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            if(user == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户信息不存在");
            }
            //比对密码
            String salt = user.getSalt();
            String password = dto.getPassword();
            String pSwd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if(!pSwd.equals(user.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            //返回数据
            Map<String, Object> map = new HashMap<>();
            String token = AppJwtUtil.getToken(user.getId().longValue());
            map.put("token",token);
            user.setSalt("");
            user.setPassword("");
            map.put("user",user);
            return ResponseResult.okResult(map);
        }
        //游客登录
        Map<String, Object> map = new HashMap<>();
        map.put("token", AppJwtUtil.getToken(0l));
        return ResponseResult.okResult(map);
    }
}
