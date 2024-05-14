package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;

/**
 * ClassName: ApUserService
 * Package: com.heima.user.service
 * Description:
 *
 * @Author R
 * @Create 2024/5/12 17:20
 * @Version 1.0
 */
public interface ApUserService extends IService<ApUser> {

    /**
     * app端登录
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
