package com.dupenghao.ssh.windows;

import com.dupenghao.ssh.pojo.HostInfo;
import com.dupenghao.ssh.ssh.SSHLinux;

/**
 * Created by 杜鹏豪 on 2023/7/18.
 */
public class SshDemo {

    /**
     * 测试:windows的ssh连接需要windows开启ssh服务
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String ip="192.168.1.89";
        int port=22;
        String username="root";
        String password="SHENLAN@2016";

        HostInfo hostInfo = new HostInfo(ip, port, username, password);
        SSHLinux sshLinux = new SSHLinux(hostInfo);
        if (sshLinux.isConnected()){
            System.out.println("连接成功");
        }



    }

}
