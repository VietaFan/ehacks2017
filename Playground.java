import java.net.InetAddress;
import java.net.UnknownHostException;

import Jama.*;
import coms.*;
import java.util.*;
import java.io.*;
public class Playground {
	public static void main(String[] args) throws Exception {
		SocketClient sc = new SocketClient();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			sc.sendLine(br.readLine());
			String s = sc.getLine();
			while (s != null) {
				System.out.println(s);
				s = sc.getLine();
			}
		}
		/*System.out.println("Hello, world\n");
		Matrix M = new Matrix(new double[][] { new double[] {3, 5, 6},
			new double[] {7, 19, 22}});
		M.print(3, 2);
		System.out.println(3^2);*/
		/*try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*SocketComs sc = new SocketComs();
		while (true) {
			sc.updateOthers();
			for (int i=0; i<sc.others.size(); ++i) {
				String s = sc.getLineFrom(i);
				while (s != null) {
					System.out.printf("%s: %s\n", sc.ips.get(i), s);
					s = sc.getLineFrom(i);
				}
			}
		}*/
	}
}