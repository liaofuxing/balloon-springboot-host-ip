package com.ts.address.host.observer;

import com.balloon.core.observer.AbstractObServer;
import com.balloon.springboot.core.spring.SpringContextHolder;
import com.balloon.springboot.mail.service.impl.MailServiceImpl;
import com.ts.address.host.entity.HostInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/07/03 15:32
 **/
public class HostMallObServer extends AbstractObServer {

    private static final Logger logger = LoggerFactory.getLogger(HostMallObServer.class);


    @Override
    public void execute(Object o) {
        logger.info("发送邮件的observer被通知");
        HostInfo hostInfo = (HostInfo) o;
        MailServiceImpl mailService = SpringContextHolder.getBean(MailServiceImpl.class);
        Map<String, Object> model = new HashMap<>();
        model.put("hostAddress", hostInfo.getHostAddress());
        model.put("hostName", hostInfo.getHostName());
        model.put("oldHostAddress", hostInfo.getOldHostAddress());
        model.put("updateTime", hostInfo.getUpdateTime());
        String filePtah = "email.html";
        mailService.sendBatchTemplateFreemarkerMail(model, filePtah);
    }
}
