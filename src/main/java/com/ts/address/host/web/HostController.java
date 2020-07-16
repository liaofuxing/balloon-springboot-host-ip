package com.ts.address.host.web;

import com.ts.address.host.entity.HostInfo;
import com.ts.address.host.service.HostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/host")
public class HostController {

    @Autowired
    private HostInfoService hostInfoService;

    @GetMapping("saveHost")
    public void saveHost(){
        HostInfo hostInfo = new HostInfo();
        hostInfo.setHostName("1111");
        hostInfo.setOldHostAddress("2222");
        hostInfo.setHostAddress("22222");
        hostInfo.setUpdateTime("2222");
        hostInfoService.saveHostInfo(hostInfo);
    }
}
