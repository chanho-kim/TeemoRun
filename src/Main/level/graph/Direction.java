package Main.level.graph;

public class Direction {
	boolean correctPath;
	int dir; // 0 - up, 1 - right, 2 - down, 3 - left
	int distance;
	
	public Direction(){
	}
	
	public Direction(boolean a, int b){
		correctPath = a;
		dir = b;
		distance = Integer.MAX_VALUE;
	}
	
	public Direction(boolean a, int b, int c){
		correctPath = a;
		dir = b;
		distance = c;
	}
}
