import java.awt.*;

import coms.*;
import gamemech.*;
import leaputils.*;

public class GameSocket {
	SocketComs cout;
	SocketClient cin;
	public GameSocket() {
		cout = new SocketComs();
		while (cout.others.size() == 0) {
			cout.updateOthers();
		}
		cin = new SocketClient();		
	}
	public void send(String s) {
		cout.sendLineTo(0, s);
	}
	public void sendGameState(GameState gs) {
		send("game_state");
		send(String.valueOf(gs.getMode()));
		send(String.valueOf(gs.getLives()));
		send(String.valueOf(gs.getScore()));
		send(String.valueOf(gs.getLastRestart()));
		send(String.valueOf(gs.hasRestarted()));
	}
	public void sendHandPosition(InfoPoint ip) {
		send("hand_position");
		for (int i=0; i<5; ++i)
			for (int j=0; j<4; ++j)
				for (int k=0; k<2; ++k)
					send(String.valueOf(ip.fpts[i][j][k].x)+' '+String.valueOf(ip.fpts[i][j][k].y)+' '+String.valueOf(ip.fpts[i][j][k].z));
		send(String.valueOf(ip.elbow.x)+' '+String.valueOf(ip.elbow.y)+' '+String.valueOf(ip.elbow.z));
		send(String.valueOf(ip.wrist.x)+' '+String.valueOf(ip.wrist.y)+' '+String.valueOf(ip.wrist.z));
		send(String.valueOf(ip.palm.x)+' '+String.valueOf(ip.palm.y)+' '+String.valueOf(ip.palm.z));
		send(String.valueOf(ip.armdir.x)+' '+String.valueOf(ip.armdir.y)+' '+String.valueOf(ip.armdir.z));
	}
	public void sendRectanglePosition(Rectangle rect) {
		send("rect_position");
		send(String.valueOf(rect.x));
		send(String.valueOf(rect.y));
		send(String.valueOf(rect.width));
		send(String.valueOf(rect.height));
	}
	public void sendNewCircle(int x, int y) {
		send("new_circle");
		send(String.valueOf(x));
		send(String.valueOf(y));
	}
	public void sendCircleGone(int x, int y) {
		send("circle_gone");
		send(String.valueOf(x));
		send(String.valueOf(y));
	}
	public Point getCircle() {
		int x = Integer.valueOf(recv());
		int y = Integer.valueOf(recv());
		return new Point(x,y);
	}
	public Rectangle getRect() {
		int x = Integer.valueOf(recv()), y = Integer.valueOf(recv()), width = Integer.valueOf(recv()), height = Integer.valueOf(recv());
		return new Rectangle(x,y,width,height);
	}
	public GameState getGameState() {
		int mode = Integer.valueOf(recv());
		int lives = Integer.valueOf(recv());
		int score = Integer.valueOf(recv());
		long lastRestart = Long.valueOf(recv());
		boolean restarted = Boolean.valueOf(recv());
		GameState gs = new GameState(mode, lives);
		gs.score(score);
		gs.restarted = restarted;
		gs.lastRestart = lastRestart;
		return gs;
	}
	public String recv() {
		return cin.getLine();
	}
	public Pt getPoint() {
		String[] s = recv().split(" ");
		return new Pt(Double.valueOf(s[0]), Double.valueOf(s[1]), Double.valueOf(s[2]));
	}
	public InfoPoint getHandPosition() {
		InfoPoint ip = new InfoPoint();
		for (int i=0; i<5; i++)
			for (int j=0; j<4; j++)
				for (int k=0; k<2; k++)
					ip.fpts[i][j][k] = getPoint();
		ip.elbow = getPoint();
		ip.wrist = getPoint();
		ip.palm = getPoint();
		ip.armdir = getPoint();
		return ip;
	}
}
