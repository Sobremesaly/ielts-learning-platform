package com.example.preSpring.service.msm;


import com.example.preSpring.service.msm.SmsService;

import com.example.preSpring.tools.RandomUtil;
import com.example.preSpring.tools.TxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 小叶子
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean send(String phone) {
        //生成验证码
        String code = RandomUtil.getSixBitRandom();
        //调用工具类发送验证码到手机
        boolean res = TxSmsUtil.TXSms("+86" + phone,"1400759630","1596607","敏思前端技术个人公众号",code);
        System.out.println("-------------"+res);
        if (res){
            //发送成功后将验证码放到Redis中设置存活时间1分钟
            redisTemplate.opsForValue().set(phone,code,1, TimeUnit.MINUTES);
            return true;
        }else {
            return false;
        }
    }
}
