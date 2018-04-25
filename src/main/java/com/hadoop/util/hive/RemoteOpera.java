package com.hadoop.util.hive;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by suntree.xu on 2016-8-11.
 */
@Repository
public class RemoteOpera {

    public static void main(String[] args) throws IOException{
        Connection conn = new Connection("10.0.0.100", 22);
        conn.connect();
        boolean isAuthenticated = conn.authenticateWithPassword("root", "hadoop");
        if (isAuthenticated == false)
            throw new IOException("Authentication failed.1");
        SCPClient client = new SCPClient(conn);
        client.put("D://xsx//Competence+//work.txt", "/home/compentence/");
		/* Create a session */

		/*Session sess = conn.openSession();

		//sess.execCommand("uname -a && date && uptime && who");
		sess.execCommand("date");
		System.out.println("Here is some information about the remote host:");


		 * This basic example does not handle stderr, which is sometimes dangerous
		 * (please read the FAQ).


		InputStream stdout = new StreamGobbler(sess.getStdout());

		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

		while (true)
		{
			String line = br.readLine();
			if (line == null)
				break;
			System.out.println(line);
		}

		 Show exit status, if available (otherwise "null")

		System.out.println("ExitCode: " + sess.getExitStatus());

		 Close this session

		sess.close();*/

		/* Close the connection */

        conn.close();

    }

    /**
     *
     * @param host
     * @param username
     * @param password
     * @param localFilepath
     * @param remotePath
     * @return
     */
    public boolean transferFile(String host,String username,String password,String localFilepath,String remotePath){
        try{
            Connection conn = new Connection(host, 22);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");
            SCPClient client = new SCPClient(conn);
            client.put(localFilepath, remotePath);
            conn.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 远程执行shell命令
     * @param host
     * @param username
     * @param password
     * @param cmd
     * @return
     */
    public String excuteCmd(String host,String username,String password,String cmd){
        String result = "";
        try{
            Connection conn = new Connection(host, 22);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            //sess.execCommand("uname -a && date && uptime && who");
            sess.execCommand(cmd);
            //System.out.println("Here is some information about the remote host:");
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                //System.out.println(line);
                result += line;
            }
            result += "ExitCode: " + sess.getExitStatus();
            //System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
		/* Close the connection */
            conn.close();
        }catch (Exception e){
            result = "";
            result += "命令执行错误，错误信息为：";
            result +=e.toString();
        }
        return result;
    }


}
