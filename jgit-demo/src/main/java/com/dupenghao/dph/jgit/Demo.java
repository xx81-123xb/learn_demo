package com.dupenghao.dph.jgit;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileBasedConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.util.FS;
import org.eclipse.jgit.util.SystemReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 杜鹏豪 on 2023/6/19.
 */
public class Demo {

//    private static final String repoPath = "D:\\LCode\\git_test";
    private static final String repoPath = "D:\\temp\\gitdemo\\git_test";

    public static void main(String[] args) throws IOException, GitAPIException {

//        handleSshAuthFail();

        //1.打开repo
        FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
        repositoryBuilder.setGitDir(new File(repoPath, ".git"));
        repositoryBuilder.readEnvironment();
        repositoryBuilder.findGitDir();
        Repository repository = repositoryBuilder.build();
        Git git = Git.wrap(repository);
        PushCommand pushCommand = git.push();
        pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("123455","123456"));

        pushCommand.call();
    }
//
//    private static Boolean isChecked = false;
//
//    /**
//     * 解决ssh认证失败问题
//     */
//    private static void handleSshAuthFail() {
//        if (isChecked) {
//            return;
//        }
//
//        JschConfigSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
//
//            @Override
//            protected void configure(OpenSshConfig.Host hc, Session session) {
//                session.setConfig("StrictHostKeyChecking", "no");
//            }
//
//            @Override
//            protected JSch createDefaultJSch(FS fs) throws JSchException {
//                JSch sch = super.createDefaultJSch(fs);
//                //keyPath 公钥文件Path
//                sch.addIdentity("D:\\LCode\\demo\\jgit-demo\\id_rsa");
//                return sch;
//            }
//        };
//
//        //1.设置SshSessionFactory和SystemReader
//        SshSessionFactory.setInstance(sshSessionFactory);
//        SystemReader.setInstance(new MsshSystemReader());
//        isChecked = true;
//    }

}

//class MsshSystemReader extends SystemReader {
//
//    private final SystemReader delegate = getInstance();
//
//    @Override
//    public String getHostname() {
//        return delegate.getHostname();
//    }
//
//    @Override
//    public String getenv(String s) {
//        String envValue = delegate.getenv(s);
//        if ("GIT_SSH".equals(s) && envValue == null)
//            return findExecutable("ssh") == null ? findExecutable("plink") : "";
//        return envValue;
//    }
//
//    @Override
//    public String getProperty(String s) {
//        return delegate.getProperty(s);
//    }
//
//    @Override
//    public FileBasedConfig openUserConfig(Config config, FS fs) {
//        return delegate.openUserConfig(config, fs);
//    }
//
//    @Override
//    public FileBasedConfig openSystemConfig(Config config, FS fs) {
//        return delegate.openSystemConfig(config, fs);
//    }
//
//    @Override
//    public FileBasedConfig openJGitConfig(Config config, FS fs) {
//        return delegate.openJGitConfig(config, fs);
//    }
//
//    @Override
//    public long getCurrentTime() {
//        return delegate.getCurrentTime();
//    }
//
//    @Override
//    public int getTimezone(long l) {
//        return delegate.getTimezone(l);
//    }
//
//    private static final Pattern PATH_SPLITTER = Pattern.compile(Pattern.quote(File.pathSeparator));
//
//    private static String findExecutable(String exe) {
//        String path = System.getenv("PATH");
//        String[] envPaths = PATH_SPLITTER.split(path);
//        List<String> paths = new ArrayList<>();
//        if (envPaths.length == 0) {
//            paths.add(exe);
//        } else {
//            for (String envPath : envPaths) {
//                paths.add(envPath + File.separator + exe);
//            }
//        }
//        for (String path1 : paths) {
//            if (Files.isExecutable(Paths.get(path1))) {
//                return path1;
//            }
//        }
//        return null;
//    }
//
//}
