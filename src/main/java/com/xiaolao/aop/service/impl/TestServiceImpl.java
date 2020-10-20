package com.xiaolao.aop.service.impl;

import com.xiaolao.aop.service.ITestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: WuJianGuo
 * @Date: 2020/9/18 16:22
 * @Description:
 */
@Service("TestServiceImpl")
public class TestServiceImpl implements ITestService {


    @Override
    public String say() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello World";
    }
}
