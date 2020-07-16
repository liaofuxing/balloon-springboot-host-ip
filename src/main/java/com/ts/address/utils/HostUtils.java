package com.ts.address.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;


public class HostUtils {


    private static final Logger logger = LoggerFactory.getLogger(HostUtils.class);

    /**
     * 获取内网ip
     * @return 内网id
     * @throws UnknownHostException 异常
     */
    public static Map<String, String> getHostAddress() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        Map<String, String> hostMap = new HashMap<>();
        hostMap.put("hostAddress", addr.getHostAddress());
        hostMap.put("hostName", addr.getHostName());
        return hostMap;
    }

    /**
     * 获取外网ip
     * @return 外网ip
     * @throws IOException 异常
     */
    public static Map<String, String> getInternetIp() throws IOException {
        Map<String, String> hostMap = new HashMap<>();
        String ip = "";
        StringBuilder inputLine = new StringBuilder();
        String read;
        InputStreamReader ins = null;
        try {

            URL url = new URL("http://pv.sohu.com/cityjson");
            ins = new InputStreamReader(url.openStream());
            BufferedReader br = new BufferedReader(ins);
            while ((read = br.readLine()) != null) {
                inputLine.append(read);
            }
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(inputLine.toString().split("=")[1], Map.class);
            ip = (String) map.get("cip");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert ins != null;
            ins.close();
        }
        hostMap.put("hostAddress", ip);
        InetAddress addr = InetAddress.getLocalHost();
        hostMap.put("hostName", addr.getHostName());
        logger.info("IP地址是:{}", hostMap.toString());
        return hostMap;
    }
}
