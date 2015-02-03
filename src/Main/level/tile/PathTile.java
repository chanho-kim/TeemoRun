package Main.level.tile;

import Main.graphics.Screen;
import Main.graphics.Sprite;

public class PathTile extends Tile{

	public PathTile(Sprite sprite) {
		super(sprite);
	}

	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 6, y << 6, this);
	}
	
}
