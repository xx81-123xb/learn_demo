package com.dupenghao.ssh.ssh;

import com.dupenghao.ssh.pojo.HostInfo;
import com.jcraft.jsch.*;

import java.io.InputStream;
import java.util.Vector;

/**
 * Created by 杜鹏豪 on 2023/7/18.
 */
public class SSHLinux {

    private HostInfo hostInfo;

    private static final JSch jSch = new JSch();

    private final Session session;

    private final Vector<Channel> channels = new Vector<>();

    public SSHLinux(HostInfo hostInfo) throws Exception {
        this.hostInfo = hostInfo;
        this.session = jSch.getSession(hostInfo.getUsername(), hostInfo.getIp(), hostInfo.getPort());
        this.session.setPassword(hostInfo.getPassword());
        this.session.setConfig("StrictHostKeyChecking", "no");
        session.setTimeout(6000);
        this.session.connect();
    }

    public ChannelShell getShellChannel() {
        try {
            ChannelShell shell = (ChannelShell) session.openChannel("shell");
            channels.add(shell);
            return shell;
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public ChannelExec getExecChannel() {
        try {
            ChannelExec exec = (ChannelExec) session.openChannel("exec");
            channels.add(exec);
            return exec;
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public ChannelSftp getSftpChannel() {
        try {
            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            channels.add(sftp);
            return sftp;
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        session.disconnect();
        channels.forEach(Channel::disconnect);
    }

    public void reconnect(){
        session.disconnect();
        try {
            session.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }

    public String exeSingleCommand(){
        return "";
    }

    public InputStream exeSingleCommandWithStream(){
        return null;
    }

}
