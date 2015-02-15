package Main.level;

import java.awt.Rectangle;

import Main.entity.items.Cookie;
import Main.entity.mob.Player;
import Main.graphics.Screen;
import Main.level.graph.Graph;
import Main.level.tile.Tile;

public class Level {
	public int width;
	public int height;
	public int[] tileInt;
	protected Tile[] tiles;
	public Rectangle[] collisionbox;
	public Cookie[] cookies;
	public Graph graph;
	public int playerPos;
	
	public Level (int width, int height) {
		this.width = width;
		this.height = height;
		tileInt = new int[width * height];

		generateLevel();
	}
	
	public void generateLevel() {
	}

	public Level (String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {

	}
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	public void render(Screen screen) {
		for (int y = 0; y < height; y+=1) {
			for (int x = 0; x < width; x+=1) {
				//getTile(x, y).render(x, y, screen);
				tiles[x+y*width].render(x, y, screen);
			}
		}
		for (int i = 0; i < cookies.length; i+=1) {
			if(cookies[i] != null) {
				cookies[i].render(screen);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (tileInt[x+y*width] == 0) return Tile.path;
		return Tile.wallTile;
	}

	public void update(Player player) {
		
	}
}
