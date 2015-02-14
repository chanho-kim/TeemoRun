package Main.level.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
	private Vertex[] vertices;
	private boolean[] checked;
	private boolean[] shortestPathCheck;
	private Graph path;
	private int size;
	private int index;
	
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
	
	public Direction BFS(int start, Vertex target) {
		Graph path = new Graph(size);
		ArrayDeque<Vertex> queue = new ArrayDeque<Vertex>(size);
		checked[start] = true;
		vertices[start].setLevel(0);
		queue.add(vertices[start]);
		path.addVertex(vertices[start]);
		
		while(!queue.isEmpty()){			
			Vertex parent = queue.remove();
			if(parent.id == target.id){
				path.addVertex(parent);
				shortestPathCheck = new boolean[size];
				Direction dir = shortestPath(path, parent.id, start);
				
				//cleaning up before returning
				for(int i=0; i < checked.length; i+=1){
					checked[i] = false;
				}
				return dir;
			}
						
			Vertex up = parent.getUp();
			if(up != null && !checked[up.id]){
				checked[up.id] = true;
				Vertex temp = new Vertex(up);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
			Vertex down = parent.getDown();
			if(down != null && !checked[down.id]){
				checked[down.id] = true;
				Vertex temp = new Vertex(down);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
			Vertex left = parent.getLeft();
			if(left != null && !checked[left.id]){
				checked[left.id] = true;
				Vertex temp = new Vertex(left);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
			Vertex right = parent.getRight();
			if(right != null && !checked[right.id]){
				checked[right.id] = true;
				Vertex temp = new Vertex(right);
				temp.setLevel(parent.getLevel()+1);
				path.addVertex(temp);
				queue.add(temp);
			}
		}
		return null;
	}


/*	private Direction shortestPath(Graph path, int index, Vertex target, int distance){		
		if(!shortestPathCheck[index]){
			Vertex v = path.getVertex(index);
			if(v == null) return new Direction(false, -1);
			shortestPathCheck[index] = true;
			if(v.id == target.id){
				shortestPathCheck[index] = false;
				return new Direction(true, -1, distance);
			}		
			if(!v.equals(target)){
				Direction down, up, left, right;
				down = up = left = right = new Direction(false, -1);
				
				if(v.getDown() != null) {
					down = shortestPath(path, v.getDown().id, target, distance+1);
				}
				
				if(v.getUp() != null) {
					up = shortestPath(path, v.getUp().id, target, distance+1);		
				}
				
				if(v.getLeft() != null) {
					left = shortestPath(path, v.getLeft().id, target, distance+1);
				}
				
				if(v.getRight() != null) {
					right = shortestPath(path, v.getRight().id, target, distance+1);
				}
				
				if(!down.correctPath && !up.correctPath && !left.correctPath && !right.correctPath) return new Direction(false, -1);
				else{
					int dir = Math.min(Math.min(down.distance, up.distance), Math.min(left.distance, right.distance));
					if(dir == down.distance) return new Direction(true, 2, distance);
					if(dir == up.distance) return new Direction(true, 0, distance);
					if(dir == left.distance) return new Direction(true, 3, distance);
					if(dir == right.distance) return new Direction(true, 1, distance);
				}
			}
		}
		else return new Direction(false, -1);
		return null;
	}*/
	
	private Direction shortestPath(Graph path, int parentID, int target){		
		Direction left,right,up,down;
		left = right = up = down = null;
		Vertex start = path.getVertex(parentID);
		int endLevel = start.getLevel();
		
		if(start.getLeft() != null && path.getVertex(start.getLeft().id).getLevel() < endLevel){
			if(start.getLeft().id == target) return new Direction(true, 1);
			left = shortestPath(path, start.getLeft().id, target);
		}
		
		if(start.getRight() != null && path.getVertex(start.getRight().id).getLevel() < endLevel){
			if(start.getRight().id == target) return new Direction(true, 3);
			right = shortestPath(path, start.getRight().id, target);
		}		
		
		if(start.getUp() != null && path.getVertex(start.getUp().id).getLevel() < endLevel){
			if(start.getUp().id == target) return new Direction(true, 2);
			up = shortestPath(path, start.getUp().id, target);
		}
		
		if(start.getDown() != null && path.getVertex(start.getDown().id).getLevel() < endLevel){
			if(start.getDown().id == target) return new Direction(true, 0);
			down = shortestPath(path, start.getDown().id, target);
		}
		
		int possible = 0;
		int[] dir = new int[4];
		if(left != null && left.correctPath){
			dir[possible] = left.dir;
			possible+=1;
			return left;
		}
		else if(right != null && right.correctPath) return right;
		else if(up != null && up.correctPath) return up;
		else if(down != null && down.correctPath) return down;		
		
		
		
		
		return new Direction(false, -1);		
	}
}
