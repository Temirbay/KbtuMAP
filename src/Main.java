import java.io.*;
import java.util.*;

public class Main {
	final int MAXN = 1000;
	final int INF = 100000000;
	int[][] matrix;
	
	public void main (String[] args) {
		matrix = new int[MAXN][MAXN];
	}
	
	public void readFile (String filename) {
		int index = 0;
		String line = null;
	    try {
	        FileReader fileReader = new FileReader(filename);
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                System.out.println(line);
	                String[] lineArray = line.split(" ");
	                for (int i = 0; i < line.length(); ++i)
	                	matrix[index][i] = Integer.parseInt(lineArray[i]);
	                index++;
	            }   
	            bufferedReader.close();         
	        }
	    catch(FileNotFoundException ex) {
	    	System.out.println("Unable to open file '" + filename + "'");                
	    }
	    catch(IOException ex) {
	    	System.out.println("Error reading file '"  + filename + "'");
	     }
	}
	
	
	
	public ArrayList<Point> findRoad (Point start, Point finish) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		boolean[][] used = new boolean[MAXN][MAXN];
		int [][] dp = new int[MAXN][MAXN];
		ArrayList <Point> answer = new ArrayList<Point>();
		
		for (int i = 0; i < MAXN; ++i)
			for (int j = 0; j < MAXN; ++j)
				dp[i][j] = INF;
		dp[start.X][start.Y] = 0;
		
		Queue <Point> q = new Queue <Point>();
		q.add(start);
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			used[cur.X][cur.Y] = true;
			for (int i = 0; i < 4; ++i) {
				int x = cur.X + dx[i];
				int y = cur.Y + dy[i];
				if (matrix[x][y] == 0) continue;
				if (0 > x || x >= MAXN || 0 > y || y >= MAXN) continue;
				if (used[x][y] == false) q.add(new Point(x, y));
				dp[x][y] = Math.min(dp[x][y], dp[cur.X][cur.Y] + 1);
			}
		}
		
		Point cur = finish;
		while (!cur.equals(start)) {
			answer.add(cur);
			for (int i = 0; i < 4; ++i) {
				int x = cur.X + dx[i];
				int y = cur.Y + dy[i];
				if (dp[x][y] == dp[cur.X][cur.Y]-1) {
					cur = new Point(x, y);
					break;
				}
			}
		}
		
		return answer;
 	}
}
