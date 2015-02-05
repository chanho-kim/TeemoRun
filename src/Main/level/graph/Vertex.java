package Main.level.graph;

public class Vertex {
	public final int id;
	private Vertex up;
	private Vertex down;
	private Vertex left;
	private Vertex right;
	
	public Vertex(int id) {
		this.id = id;
	}
	
	public Vertex(Vertex v) {
		this.id = v.id;
		this.up = v.up;
		this.down = v.down;
		this.left = v.left;
		this.right = v.right;
	}
	
	void link(Vertex up, Vertex down, Vertex left, Vertex right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	public void setUp(Vertex v) {
		up = v;
	}
	
	public void setDown(Vertex v) {
		down = v;
	}
	
	public void setLeft(Vertex v) {
		left = v;
	}
	
	public void setRight(Vertex v) {
		right = v;
	}
	
	public Vertex getUp() {
		return up;
	}

	public Vertex getDown() {
		return down;
	}
	
	public Vertex getLeft() {
		return left;
	}
	
	public Vertex getRight() {
		return right;
	}
	
	
}
