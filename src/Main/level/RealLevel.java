package Main.level;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.entity.items.Cookie;
import Main.entity.mob.Player;
import Main.level.graph.Direction;
import Main.level.graph.Graph;
import Main.level.graph.Vertex;
import Main.level.tile.Tile;

public class RealLevel extends Level {

	private int[] levelPixels;
	
	public RealLevel(String path) {
		super(path);
	}
	
	public void generateLevel() {
		collisionbox = new Rectangle[levelPixels.length];
		cookies = new Cookie[levelPixels.length];
		graph = new Graph(levelPixels.length);
		for (int i = 0; i < levelPixels.length; i+=1) {
			if (levelPixels[i] == 0xff000000) {
				tiles[i] = Tile.wallTile;
				int y = i / 19;
				int x = i % 19;
				collisionbox[i] = new Rectangle(x << 6,y << 6, 64, 64);
			}
			else if (levelPixels[i] == 0xffffffff) {
				Vertex v;
				boolean newVertex = false;
				tiles[i] = Tile.path;
				int y = i / 19;
				int x = i % 19;
				cookies[i] = new Cookie(x << 6,y << 6);
				
				if(graph.getVertex(i) != null) v = graph.getVertex(i);
				else {
					v = new Vertex(i);
					newVertex = true;
				}
					
				if(i-19 >= 0 && levelPixels[i] == 0xffffffff) v.setUp(graph.getVertex(i-19));
				else v.setUp(null);
				
				if(i+19 < levelPixels.length && levelPixels[i+19] == 0xffffffff){
					graph.addVertex(new Vertex(i+19), i+19);
					v.setDown(graph.getVertex(i+19));
				}
				else v.setDown(null);
				
				if(i-1 >= 0 && levelPixels[i-1] == 0xffffffff) v.setLeft(graph.getVertex(i-1));
				else v.setLeft(null);
				
				if(i+1 < levelPixels.length && levelPixels[i+1] == 0xffffffff){
					graph.addVertex(new Vertex(i+1), i+1);
					v.setRight(graph.getVertex(i+1));
				}
				
				if(newVertex) graph.addVertex(v, i);	
			}
		}
	}
		
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(RealLevel.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			tiles = new Tile[w*h];
			levelPixels = new int[w*h];
			height = h;
			width = w;
			image.getRGB(0, 0, w, h, levelPixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Player player) {
		boolean done = true;
		
		playerPos = ((player.x + 31)/64) + (((player.y+31)/64)*19);
		
		Direction dir = graph.BFS(playerPos, 20);
		System.out.println(playerPos + "/" + dir.getDirection());
		
		for (int i = 0; i < cookies.length; i+=1) {
			if(cookies[i] != null) {
				if(cookies[i].hitbox.intersects(player.hitbox)) cookies[i] = null;
				done = false;
			}
		}
		//if we finished collecting cookies, we're done!
		if(done) {
			System.out.println("We're done");
		}
	}
}

