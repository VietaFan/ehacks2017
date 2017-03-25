package coms;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class SocketClient {
	DataOutputStream out;
	BufferedReader in;
	public SocketClient() {
		try {
			Socket s  = new Socket("192.168.56.1", 13481);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new DataOutputStream(s.getOutputStream());
		} catch (Exception e) {
			
		}
	}
	public String getLine() {
		try {
			if (in.ready()) {
				return in.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void sendLine(String s) {
		try {
			out.writeUTF(s+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
