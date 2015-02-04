package Main.level.tile;

import Main.graphics.Screen;
import Main.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile path = new PathTile(Sprite.tile0);
	public static Tile wallTile = new WallTile(Sprite.tile1);
	
	public Tile (Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render (int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
	
}
