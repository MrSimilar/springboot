package com.xinyet.test;

import com.alibaba.fastjson.JSON;
import com.xinyet.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Date;

@Component
@Slf4j
public class SendMessageToRedis {

    @Autowired
    private RedisUtil redisUtil;

    public static final String MESSAGE = "this is the message to redis";

    public static final String REAL_SOLVE = "real_solve";

    @Scheduled(cron = "*/1 * * * * ?")
    public void send() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Bean bean = getBean(i);
            redisUtil.zAdd(REAL_SOLVE, JSON.toJSONString(bean), getDoubleByDate(bean.getAt()));
        }
        log.info("send message finished !");
    }

    private Bean getBean(Integer id) {
        return new Bean(id, String.valueOf(id), String.valueOf(id), MESSAGE + id, new Date());
    }


    /**
     * 时间转double
     *
     * @param date
     * @return 返回值类似：43322.3770190278
     */
    public double date2Double(Date date) {
        @SuppressWarnings("deprecation")
        long localOffset = date.getTimezoneOffset() * 60000L;
        double dd = (double) (date.getTime() - localOffset) / 24 / 3600 / 1000 + 25569.0000000;
        DecimalFormat df = new DecimalFormat("#.0000000000");//先默认保留10位小数
        return Double.parseDouble(df.format(dd));
    }

    public static double getDoubleByDate(Date date) {
        return date.getTime();
    }

}
