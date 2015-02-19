package Main.level.graph;

public class Vertex {
	public final int id;
	private int level;
	private int up;
	private int down;
	private int left;
	private int right;
	
	public Vertex(int id) {
		this.id = id;
	}

	public Vertex(int id, int level) {
		this.id = id;
		this.level = level;
	}	
	
	public Vertex(Vertex v) {
		this.id = v.id;
		this.level = v.level;
		this.up = v.up;
		this.down = v.down;
		this.left = v.left;
		this.right = v.right;
	}
	
	
	public void setUp(int v) {
		up = v;
	}
	
	public void setDown(int v) {
		down = v;
	}
	
	public void setLeft(int v) {
		left = v;
	}
	
	public void setRight(int v) {
		right = v;
	}
	
	public int getUp() {
		return up;
	}

	public int getDown() {
		return down;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getRight() {
		return right;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
	
}
