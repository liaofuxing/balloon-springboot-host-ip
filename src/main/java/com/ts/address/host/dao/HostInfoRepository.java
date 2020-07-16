package com.ts.address.host.dao;

import com.ts.address.host.entity.HostInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/07/03 15:32
 **/
@Repository
public interface HostInfoRepository extends JpaRepository<HostInfo, String> {
}
