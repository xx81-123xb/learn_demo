package com.dupenghao.ssh.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 杜鹏豪 on 2023/7/18.
 */
@Data
//@Slf4j
public class HostInfo {

    private String ip;
    private int port;
    private String username;
    private String password;

    private String comment;

    public HostInfo() {

    }

    public HostInfo(String ip, int port, String username, String password, String comment) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.comment = comment;
    }

    public HostInfo(String ip, int port, String username, String password) {
        this(ip, port, username, password, "");
    }

}
