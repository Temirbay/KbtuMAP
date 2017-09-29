import java.io.*;
import java.util.*;

public class Point {
	public int X;
	public int Y;
	
	public Point () {}
	public Point (int X, int Y) {
		this.X = X;
		this.Y = Y;
	}
	
	public boolean equals (Object obj) {
		Point newObject = (Point)obj;
		return newObject.X == this.X && newObject.Y == this.Y;
	}
}
