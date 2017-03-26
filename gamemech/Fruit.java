package gamemech;
import java.awt.*;
import java.util.*;

public class Fruit {
	private int X;
	private int Y;
	private int width;
	private int height;
	private static int acc = 10;
	
	public Fruit(int X, int Y, int width, int height){
		this.X = X;
		this.Y = Y;
		this.width = width;
		this.height = height;
	}
	
	public void enter(Graphics bufwin){
		bufwin.drawOval(X, 480, width, height);
	}
}
