package com.heima.model.user.dtos;

import lombok.Data;

/**
 * ClassName: LoginDto
 * Package: com.heima.model.user.dtos
 * Description:
 *
 * @Author R
 * @Create 2024/5/12 17:13
 * @Version 1.0
 */
@Data
public class LoginDto {
    private String phone;
    private String password;
}
