package com.example.preSpring.tools;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;


/**
 * @author 小叶子
 */
public class TxSmsUtil {
    public static boolean TXSms(String phone, String sdkAppId, String templateId, String signName, String code) {
        try{
            //这里是实例化一个Credential，也就是认证对象，参数是密钥对；你要使用肯定要进行认证
            Credential credential = new Credential("AKIDL4h9Pm3f5HG4lEvZErT6dy72lHRYcXNH", "lQJ1Wu88sOjsmS5GHLFzWr70kIuisz15");

            //HttpProfile这是http的配置文件操作，比如设置请求类型(post,get)或者设置超时时间了、还有指定域名了
            //最简单的就是实例化该对象即可，它的构造方法已经帮我们设置了一些默认的值
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");


            //实例化一个客户端配置对象,这个配置可以进行签名（使用私钥进行加密的过程），对方可以利用公钥进行解密
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);

            //实例化要请求产品(以sms为例)的client对象
            SmsClient smsClient = new SmsClient(credential, "ap-guangzhou", clientProfile);

            //实例化request封装请求信息
            com.tencentcloudapi.sms.v20190711.models.SendSmsRequest request = new SendSmsRequest();
            String[] phoneNumber = {phone};
            request.setPhoneNumberSet(phoneNumber);     //设置手机号
            request.setSmsSdkAppid(sdkAppId); //1400759630
            request.setSign(signName);     //"敏思前端技术个人公众号"
            request.setTemplateID(templateId);     //1596607
            //生成随机验证码，我的模板内容的参数只有一个
            String[] templateParamSet = {code,"1"};

            request.setTemplateParamSet(templateParamSet);
            String sessionContext = "";
            request.setSessionContext(sessionContext);
            //发送短信
            SendSmsResponse response = smsClient.SendSms(request);
            System.out.println(SendSmsResponse.toJsonString(request));
            return true;
        } catch(TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return false;
    }
}
