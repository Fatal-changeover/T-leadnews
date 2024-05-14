package com.heima.wemedia.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: WmMaterialController
 * Package: com.heima.wemedia.controller.v1
 * Description:
 *
 * @Author R
 * @Create 2024/5/14 8:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {
    @Autowired
    private WmMaterialService wmMaterialService;
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);
    }

    //素材列表查询
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto) {
        return wmMaterialService.findList(dto);
    }
}
