package com.ts.address.host.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "host_info")
@Data
public class HostInfo {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name="id", strategy="org.hibernate.id.UUIDGenerator")
    private String id;
    private String hostAddress;
    private String hostName;
    private String oldHostAddress;
    private String updateTime;

    public HostInfo(){
    }


    public HostInfo(String hostAddress, String hostName){
        this.hostName = hostName;
        this.hostAddress = hostAddress;
    }


    @Override
    public String toString() {
        return "HostInfo{" +
                "id='" + id + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                ", hostName='" + hostName + '\'' +
                '}';
    }
}
