package Main.level.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Graph {
	private Vertex[] vertices;
	private boolean[] checked;
	private boolean[] shortestPathCheck;
	private Graph path;
	private int size;
	private int index;
	private Random random = new Random();
	
	public Graph(int size){
		this.index = 0;
		this.size = size;
		vertices = new Vertex[size];
		checked = new boolean[size];
	}
	
	public void addVertex(Vertex v, int index){
		vertices[index] = v;
	}
	
	private void addVertex(Vertex v){
		vertices[v.id] = v;
	}
	
	public Vertex getVertex(int i) {
		if(vertices[i] != null) return vertices[i];
		else return null;
	}
	
	public Direction BFS(int start, int target) {
		Graph path = new Graph(size);
		ArrayDeque<Vertex> queue = new ArrayDeque<Vertex>(size);
		checked[start] = true;
		vertices[start].setLevel(0);
		queue.add(vertices[start]);
		path.addVertex(vertices[start]);
		
		while(!queue.isEmpty()){			
			Vertex parent = queue.remove();
			if(parent.id == target){
				path.addVertex(parent);
				shortestPathCheck = new boolean[size];
				Direction dir = shortestPath(path, parent.id, start);
				
				//cleaning up before returning
				for(int i=0; i < checked.length; i+=1){
					checked[i] = false;
				}
				return dir;
			}
						
			int up = parent.getUp();
			if(up != -1 && !checked[up]){
				checked[up] = true;
				Vertex temp = new Vertex(vertices[up]);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
			int down = parent.getDown();
			if(down != -1 && !checked[down]){
				checked[down] = true;
				Vertex temp = new Vertex(vertices[down]);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
			int left = parent.getLeft();
			if(left != -1 && !checked[left]){
				checked[left] = true;
				Vertex temp = new Vertex(vertices[left]);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
			int right = parent.getRight();
			if(right != -1 && !checked[right]){
				checked[right] = true;
				Vertex temp = new Vertex(vertices[right]);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
		}
		return null;
	}

	private Direction shortestPath(Graph path, int parentID, int target){		
		Direction left,right,up,down;
		left = right = up = down = null;
		Vertex start = path.getVertex(parentID);
		int endLevel = start.getLevel();
		
		if(start.getLeft() != -1 && path.getVertex(start.getLeft()) != null && path.getVertex(start.getLeft()).getLevel() < endLevel){
			if(start.getLeft() == target) return new Direction(true, 1);
			left = shortestPath(path, start.getLeft(), target);
		}
		
		if(start.getRight() != -1 && path.getVertex(start.getRight()) != null && path.getVertex(start.getRight()).getLevel() < endLevel){
			if(start.getRight() == target) return new Direction(true, 3);
			right = shortestPath(path, start.getRight(), target);
		}		
		
		if(start.getUp() != -1 && path.getVertex(start.getUp()) != null && path.getVertex(start.getUp()).getLevel() < endLevel){
			if(start.getUp() == target) return new Direction(true, 2);
			up = shortestPath(path, start.getUp(), target);
		}
		
		if(start.getDown() != -1 && path.getVertex(start.getDown()) != null && path.getVertex(start.getDown()).getLevel() < endLevel){
			if(start.getDown() == target) return new Direction(true, 0);
			down = shortestPath(path, start.getDown(), target);
		}
		
		int possible = 0;
		boolean pathExists = false;
		int[] dir = new int[4];
		if(left != null && left.correctPath){
			dir[possible] = left.dir;
			possible+=1;
			pathExists = true;
		}
		if(right != null && right.correctPath){
			dir[possible] = right.dir;
			possible+=1;
			pathExists = true;
		}
		if(up != null && up.correctPath){
			dir[possible] = up.dir;
			possible+=1;
			pathExists = true;
		}
		if(down != null && down.correctPath){
			dir[possible] = down.dir;
			possible+=1;
			pathExists = true;
		}

		if(pathExists){
			return new Direction(true, dir[random.nextInt(possible)]);
		}
		else return new Direction(false, -1);		
	}
}
