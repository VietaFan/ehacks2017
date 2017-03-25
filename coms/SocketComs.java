package coms;
import java.io.*;
import java.net.*;
import java.util.*;

public class SocketComs {
	private ServerSocket svr;
	public ArrayList<Socket> others;
	private ArrayList<BufferedReader> ins; 
	private ArrayList<BufferedWriter> outs;
	public ArrayList<String> ips;
	public SocketComs() {
		try {
			svr = new ServerSocket(13481);
			others = new ArrayList<Socket>();
			ins = new ArrayList<BufferedReader>();
			outs = new ArrayList<BufferedWriter>();
			ips = new ArrayList<String>();
			svr.setSoTimeout(50);
			Socket s;
			Scanner inputSc = new Scanner(System.in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void updateOthers() {
		try {
			Socket s = svr.accept();
			others.add(s);
			ins.add(new BufferedReader (new InputStreamReader(s.getInputStream())));
			ips.add(s.getInetAddress().getHostAddress());
			outs.add(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
		} catch (IOException e) {
		}
		for (int i=0; i<others.size(); i++) {
			if (others.get(i).isInputShutdown()) {
				others.remove(i);
				ins.remove(i);
				ips.remove(i);
			}
		}
	}
	public String getLineFrom(int i) {
		try {
			if (ins.get(i).ready()) {
				return ins.get(i).readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void sendLineTo(int i, String s) {
		try {
			outs.get(i).write(s+'\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
