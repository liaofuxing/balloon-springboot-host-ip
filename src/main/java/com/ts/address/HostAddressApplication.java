package com.ts.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>必须扫描 com.balloon.springboot.core.exception ,否则全局异常拦截不会生效</p>
 * <p>使用 @ComponentScan 扫描 com.balloon.springboot.core.exception 路径, 是因为该路径下配置了全局异常拦截</p>
 * <p>要想使用自动装载的 Security, 请在yml中配置开启, 并排除掉 UserDetailsServiceAutoConfiguration.class, 以禁用默认控制台的默认密码</p>
 *
 * @author liaofuxing
 */
//@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
@ComponentScan({"com.balloon.springboot.core.exception", "com.ts"})
@SpringBootApplication
public class HostAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(HostAddressApplication.class, args);
    }

}
