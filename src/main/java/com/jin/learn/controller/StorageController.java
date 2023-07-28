package com.jin.learn.controller;

import com.jin.learn.dto.ApiResponse;
import com.jin.learn.entity.StorageTbl;
import com.jin.learn.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private StorageService storageService;


    @PostMapping("deduct")
    public ApiResponse deductStorage(@RequestBody StorageTbl storage) {
        storageService.deduct(storage.getCommodityCode(), storage.getCount());
        return ApiResponse.OK();
    }



}
