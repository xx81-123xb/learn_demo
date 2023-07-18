package com.dupenghao.ssh.pojo;

import lombok.Data;

/**
 * Created by 杜鹏豪 on 2023/7/18.
 */
@Data
public class DbHostInfo extends HostInfo{

    private String dbusername;

    private String dbpassword;

    private String dbport;

    public DbHostInfo(String dbusername,String dbpassword, String dbport) {
        this.dbusername = dbusername;
        this.dbpassword = dbpassword;
        this.dbport = dbport;
    }

}
