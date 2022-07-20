package cn.zxlee.cooper;

import cn.zxlee.cooper.core.aliyun.business.sms.AliyunSendSms;
import cn.zxlee.cooper.core.aliyun.business.sms.entity.AliyunSendSmsResult;
import cn.zxlee.cooper.utils.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class CooperApplicationTests {

    @Autowired
    AliyunSendSms sendSms;

    @Test
    void contextLoads() {

    }


}
