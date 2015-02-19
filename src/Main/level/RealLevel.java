package Main.level;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.entity.items.Cookie;
import Main.entity.mob.Monster;
import Main.entity.mob.Player;
import Main.graphics.Sprite;
import Main.level.graph.Direction;
import Main.level.graph.Graph;
import Main.level.graph.Vertex;
import Main.level.tile.Tile;

public class RealLevel extends Level {

	private int[] levelPixels;
	private Random random = new Random();
	
	public RealLevel(String path) {
		super(path);

	}
	
	public void generateLevel() {
		monsters = new Monster[4];
		monsters[0] = new Monster(192,128,Sprite.teemoDown2,this);
		monsters[1] = new Monster(960,128,Sprite.teemoDown2,this);
		monsters[2] = new Monster(192,640,Sprite.teemoDown2,this);
		monsters[3] = new Monster(960,640,Sprite.teemoDown2,this);
		
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
					
				if(i-19 >= 0 && levelPixels[i-19] == 0xffffffff) v.setUp(i-19);
				else v.setUp(-1);
				
				if(i+19 < levelPixels.length && levelPixels[i+19] == 0xffffffff){
					graph.addVertex(new Vertex(i+19), i+19);
					v.setDown(i+19);
				}
				else v.setDown(-1);
				
				if(i-1 >= 0 && levelPixels[i-1] == 0xffffffff) v.setLeft(i-1);
				else v.setLeft(-1);
				
				if(i+1 < levelPixels.length && levelPixels[i+1] == 0xffffffff){
					graph.addVertex(new Vertex(i+1), i+1);
					v.setRight(i+1);
				}
				else v.setRight(-1);
				
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
		
		for(int x = 1; x < 18; x+=1) {
			int xPos = x * 64;
			for(int y = 1; y < 12 ; y+=1) {
				int yPos = y * 64;
				for(int i = 0; i<monsters.length; i+=1){
					if(monsters[i].x == xPos && monsters[i].y == yPos) {
						monsters[i].monsterReady = true;
						monsters[i].monsterPos = x + (y*19);
					}
				}
			}
		}
	
		for(int i = 0; i < monsters.length; i+=1){
			Direction monsterDir;
			int x = monsters[i].x - 128;
			int y = monsters[i].y - 128;
			
			Rectangle range = new Rectangle(x,y,320,320);
			
			if(player.hitbox.intersects(range)){
				monsterDir = graph.BFS(monsters[i].monsterPos, playerPos);
			}
			else{
				int randomDirection = 0;
				boolean goodPath = false;
				while(!goodPath){
					randomDirection = random.nextInt(4);
					if(randomDirection == 0 && levelPixels[monsters[i].monsterPos-19] == 0xffffffff) break; 
					else if(randomDirection == 1 && levelPixels[monsters[i].monsterPos+1] == 0xffffffff) break;
					else if(randomDirection == 2 && levelPixels[monsters[i].monsterPos+19] == 0xffffffff) break;
					else if(randomDirection == 3 && levelPixels[monsters[i].monsterPos-1] == 0xffffffff) break; 
				}
				monsterDir = new Direction(true, randomDirection);
			}
			
			monsters[i].update(monsterDir.getDirection());
		}
				
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

