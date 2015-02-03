package Main.level;

import Main.graphics.Screen;
import Main.level.tile.Tile;

public class Level {
	public int width;
	public int height;
	public int[] tiles;
	
	public Level (int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];

		generateLevel();
	}
	
	public void generateLevel() {
	}

	public Level (String path) {
		loadLevel(path);
	}

	private void loadLevel(String path) {

	}
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	public void render(Screen screen) {
		for (int y = 0; y < height; y+=1) {
			for (int x = 0; x < width; x+=1) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (tiles[x+y*width] == 0) return Tile.path;
		return Tile.voidTile;
	}
}
