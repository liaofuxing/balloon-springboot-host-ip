package com.ts.address.scheduled;

import com.balloon.core.date.DatePattern;
import com.balloon.core.date.DateTimeUtils;
import com.balloon.core.observer.ObFactory;
import com.balloon.core.observer.Subject;
import com.ts.address.host.entity.HostInfo;
import com.ts.address.host.observer.HostMallObServer;
import com.ts.address.host.observer.HostUpdateObServer;
import com.ts.address.host.service.HostInfoService;
import com.ts.address.utils.HostUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * 定时器
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/07/03 15:32
 **/
@Service
@EnableScheduling
public class HostScheduledService {

    private static final Logger logger = LoggerFactory.getLogger(HostScheduledService.class);

    @Autowired
    private HostInfoService hostInfoService;

    private static final Subject subject;

    static {
        subject = ObFactory.getOrCreate("host");
        HostMallObServer hostObServer = new HostMallObServer();
        HostUpdateObServer hostUpdateObServer = new HostUpdateObServer();
        subject.add(hostObServer);
        subject.add(hostUpdateObServer);
    }
     // 间隔20秒执行
     // @Scheduled(cron = "0/20 * * * * ? ")
     // 间隔 30分钟
    @Scheduled(cron = "0 0/30 * * * ?")
    public void getHost() throws IOException, ExecutionException, InterruptedException {
        logger.info("------------------自动任务开始执行-----------------------");
        HostInfo localHost = getLocalHost();
        HostInfo hostInfo = hostInfoService.getHostInfo();
        if(hostInfo != null){
            if (!localHost.getHostAddress().equals(hostInfo.getHostAddress())) {
                localHost.setOldHostAddress(hostInfo.getHostAddress());
                LocalDateTime now = LocalDateTime.now();
                localHost.setUpdateTime(DateTimeUtils.formatDateTime(now, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
                subject.notifyObservers(localHost);
            }
        }else {
            localHost.setOldHostAddress("");
            LocalDateTime now = LocalDateTime.now();
            localHost.setUpdateTime(DateTimeUtils.formatDateTime(now, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            subject.notifyObservers(localHost);
        }
        logger.info("--------------------自动任务结束-------------------------");
    }

    public HostInfo getLocalHost() throws IOException {
        Map<String, String> hostMap = HostUtils.getInternetIp();
        String hostAddress = hostMap.get("hostAddress");
        String hostName = hostMap.get("hostName");
        logger.info("查询到的外网IP是:{}", hostAddress);
        return new HostInfo(hostAddress, hostName);
    }

}
