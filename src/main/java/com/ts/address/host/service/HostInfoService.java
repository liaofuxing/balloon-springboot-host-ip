package com.ts.address.host.service;

import com.balloon.core.collection.CollectionUtils;
import com.balloon.springboot.core.entity.InitBaseEntity;
import com.ts.address.host.dao.HostInfoRepository;
import com.ts.address.host.entity.HostInfo;
import com.ts.address.utils.HostUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/10/08 15:33
 **/
@Service
public class HostInfoService {

    @Autowired
    private HostInfoRepository hostInfoRepository;

    public HostInfo getHostInfo() {
        List<HostInfo> hostInfoList = hostInfoRepository.findAll();
        if(CollectionUtils.isNotEmpty(hostInfoList)) {
            return hostInfoRepository.findAll().get(0);
        }
        return null;
    }

    /**
     * update or save
     * @param hostInfo host实体
     */
    public void updateOrSaveHostInfo(HostInfo hostInfo) {
        List<HostInfo> hostInfoList = hostInfoRepository.findAll();
        if(CollectionUtils.isNotEmpty(hostInfoList)) {
            HostInfo hostInfoDB = hostInfoRepository.findAll().get(0);
            hostInfo.setId(hostInfoDB.getId());
            InitBaseEntity.initDateTime(hostInfo);
        }
        hostInfoRepository.save(hostInfo);
    }

    public void saveHostInfo(HostInfo hostInfo){
        hostInfoRepository.save(hostInfo);
    }

}
