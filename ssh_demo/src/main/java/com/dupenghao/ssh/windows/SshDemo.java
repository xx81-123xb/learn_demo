package com.dupenghao.ssh.windows;

import com.dupenghao.ssh.pojo.HostInfo;
import com.dupenghao.ssh.ssh.SSHLinux;

/**
 * Created by 杜鹏豪 on 2023/7/18.
 */
public class SshDemo {

    public static void main(String[] args) throws Exception {
        String ip="192.168.1.89";
        int port=22;
        String username="root";
        String password="SHENLAN@2016";

        HostInfo hostInfo = new HostInfo(ip, port, username, password);
        SSHLinux sshLinux = new SSHLinux(hostInfo);




    }

}
