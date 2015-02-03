package Main.graphics;

import java.util.Random;

import Main.level.tile.Tile;

public class Screen {
	public int width, height;
	public int[] pixels;
	//how big will the tiles be? 64 in each row and column for now
	public int[] tiles = new int[19*13];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < 19 * 13; i += 1) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		for (int y = 0; y < tile.sprite.SIZE; y+=1) {
			for (int x = 0; x < tile.sprite.SIZE; x+=1) {
				pixels[(xp+x) + (yp+y) * width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
			}
		}
	}
}
