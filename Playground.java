import java.net.InetAddress;
import java.net.UnknownHostException;

import Jama.*;
import coms.*;
import java.util.*;
import java.io.*;
public class Playground {
	public static void main(String[] args) throws Exception {
		GameSocket2 gs = new GameSocket2();
		while (true) {
			String s = gs.recv();
			if (s != null)
				System.out.println(s);
		}
		/*try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SocketComs sc2 = new SocketComs();
		while (sc2.others.size() == 0) {
			sc2.updateOthers();
		}
		SocketClient sc = new SocketClient();
		System.out.println("done");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			sc2.updateOthers();
			for (int i=0; i<sc2.others.size(); ++i) {
				try {
				String s = sc2.getLineFrom(i).trim();
				while (s != null) {
					System.out.printf("%s: %s\n", sc2.ips.get(i), s);
					s = sc2.getLineFrom(i);
				}
				} catch (Exception e) {
					sc2.updateOthers();
				}
			}
			if (br.ready())
				sc.sendLine(br.readLine());
			String t = sc.getLine();
			while (t != null) {
				System.out.println(t);
				t = sc.getLine();
			}
		}
		/*System.out.println("Hello, world\n");
		Matrix M = new Matrix(new double[][] { new double[] {3, 5, 6},
			new double[] {7, 19, 22}});
		M.print(3, 2);
		System.out.println(3^2);*/
		/*
		SocketComs sc = new SocketComs();
		while (true) {
			sc.updateOthers();
			for (int i=0; i<sc.others.size(); ++i) {
				String s = sc.getLineFrom(i);
				while (s != null) {
					System.out.printf("%s: %s\n", sc.ips.get(i), s);
					s = sc.getLineFrom(i);
				}
			}
		}
		sc.close();
		*/
	}
}