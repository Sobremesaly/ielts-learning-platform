package com.example.preSpring.util;

import com.alibaba.druid.util.StringUtils;
import com.example.preSpring.service.msm.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 小叶子
 */
@Component
@CrossOrigin
public class SendMessage {

    @Autowired
    private SmsService smsService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public List<String> getCode(String phone) {

        //调用发送方法 （模拟业务 redis）
        //首先去redis中查该规定时间内手机号有没有生成验证码
        String code = redisTemplate.opsForValue().get(phone);
        System.out.println("redis中的验证码----------" + code);
        List<String> list = new ArrayList<>();
        /*返回值的第一项表示返回内容*/
        String first;
        /*第二项表示返回状态,成功或失败*/
        String second;
        if (!StringUtils.isEmpty(code)) {
            first = phone + " :" + "验证码已存在，请稍后重试";
            second = "error";
            list.add(first);
            list.add(second);
            return list;
        }
        //若Redis中没有，调用service发送短信方法
        boolean res = smsService.send(phone);
        System.out.println(res);
        if (res) {
            first = phone + " : " + "发送成功";
            second = "right";
        } else {
            first = phone + " : " + "发送失败";
            second = "error";
        }
        list.add(first);
        list.add(second);
        return list;
    }

}
