package com.ts.address.host.observer;

import com.balloon.core.observer.AbstractObServer;
import com.balloon.springboot.core.spring.SpringContextHolder;
import com.ts.address.host.entity.HostInfo;
import com.ts.address.host.service.HostInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/07/03 15:32
 **/
public class HostUpdateObServer  extends AbstractObServer {

    private static final Logger logger = LoggerFactory.getLogger(HostUpdateObServer.class);

    @Override
    public void execute(Object o) {
        logger.info("执行更新的observer被通知");
        HostInfoService bean = SpringContextHolder.getBean(HostInfoService.class);
        bean.updateOrSaveHostInfo((HostInfo)o);
    }
}
