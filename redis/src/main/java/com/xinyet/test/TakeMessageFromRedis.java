package com.xinyet.test;

import com.xinyet.utils.RedisUtil;
import com.xinyet.utils.RedissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TakeMessageFromRedis {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonUtil redissonUtil;

    @Scheduled(cron = "*/1 * * * * ?")
    public void send() {
        try {
            boolean lock = redissonUtil.tryLock(SendMessageToRedis.REAL_SOLVE, TimeUnit.SECONDS, 1, 5);
            if (lock) {
                Set<Object> objects = redisUtil.zRange(SendMessageToRedis.REAL_SOLVE, 0, -1);
                redisUtil.zRemoveRange(SendMessageToRedis.REAL_SOLVE, 0, -1);
                for (Object o : objects) {
                    log.info("o:{}", o);
                }
                log.info("读取完成");
                redissonUtil.unlock(SendMessageToRedis.REAL_SOLVE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("handle take message error:{}", e.getMessage());
        }
    }
}
