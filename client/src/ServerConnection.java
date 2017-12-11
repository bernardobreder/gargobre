import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ServerConnection implements AutoCloseable {

  private JSch ssh;

  private Session session;

  public ServerConnection(String user, String host, String password)
    throws IOException {
    try {
      ssh = new JSch();
      session = ssh.getSession(user, host, 22);
      Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.setPassword(password);
      session.connect();
    }
    catch (JSchException e) {
      throw new IOException(e);
    }
  }

  public int exec(String command) throws IOException {
    try {
      ChannelExec channel = execChannel("exec");
      channel.setCommand(command);
      channel.setErrStream(System.err);
      try (InputStream in = channel.getInputStream()) {
        channel.connect();
        byte[] tmp = new byte[1024];
        while (true) {
          while (in.available() > 0) {
            int i = in.read(tmp, 0, 1024);
            if (i < 0) {
              break;
            }
            System.out.print(new String(tmp, 0, i));
          }
          if (channel.isClosed()) {
            return channel.getExitStatus();
          }
        }
      }
    }
    catch (JSchException e) {
      throw new IOException(e);
    }
  }

  public void write(String remote, byte[] bytes) throws IOException {
    try {
      ChannelSftp channelSftp = sftp();
      File remoteFile = Paths.get(remote).toFile();
      channelSftp.cd(path(remoteFile.getParentFile()));
      channelSftp.put(new ByteArrayInputStream(bytes), remoteFile.getName());
    }
    catch (JSchException | SftpException e) {
      throw new IOException(e);
    }
  }

  public byte[] read(String remote) throws IOException {
    try {
      ChannelSftp channelSftp = sftp();
      File remoteFile = Paths.get(remote).toFile();
      channelSftp.cd(path(remoteFile.getParentFile()));
      byte[] buffer = new byte[1024];
      try (BufferedInputStream bis = new BufferedInputStream(channelSftp.get(
        remote))) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int readCount;
        while ((readCount = bis.read(buffer)) > 0) {
          os.write(buffer, 0, readCount);
        }
        return os.toByteArray();
      }
    }
    catch (JSchException | SftpException e) {
      throw new IOException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    session.disconnect();
  }

  protected String path(File remoteFile) {
    return remoteFile.toString().replace(File.separatorChar, '/');
  }

  protected ChannelExec execChannel(String command) throws JSchException {
    return (ChannelExec) session.openChannel(command);
  }

  protected ChannelSftp sftp() throws JSchException {
    Channel channel = session.openChannel("sftp");
    channel.connect();
    ChannelSftp channelSftp = (ChannelSftp) channel;
    return channelSftp;
  }

}
