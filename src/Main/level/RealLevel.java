package Main.level;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.entity.items.Cookie;
import Main.entity.mob.Player;
import Main.level.tile.Tile;

public class RealLevel extends Level {

	private int[] levelPixels;
	
	public RealLevel(String path) {
		super(path);
	}
	
	public void generateLevel() {
		collisionbox = new Rectangle[levelPixels.length];
		cookies = new Cookie[levelPixels.length];
		for (int i = 0; i < levelPixels.length; i+=1) {
			if (levelPixels[i] == 0xff000000) {
				tiles[i] = Tile.wallTile;
				int y = i / 19;
				int x = i % 19;
				collisionbox[i] = new Rectangle(x << 6,y << 6, 64, 64);
			}
			else if (levelPixels[i] == 0xffffffff) {
				tiles[i] = Tile.path;
				int y = i / 19;
				int x = i % 19;
				cookies[i] = new Cookie(x << 6,y << 6);
			}
		}
		System.out.println(levelPixels.length);
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
		for (int i = 0; i < cookies.length; i+=1) {
			if(cookies[i] != null) {
				if(cookies[i].hitbox.intersects(player.hitbox)) cookies[i] = null;
				done = false;
			}
		}
		//if we finished collecting cookies, we're done!
		if(done) {}
	}
}

