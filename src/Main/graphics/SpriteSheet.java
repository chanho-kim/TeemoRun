package Main.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet characters = new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet chara = new SpriteSheet("/textures/spritesheet2.png",256);
	
	public static SpriteSheet DeemoUp0 = new SpriteSheet("/textures/run up 1.png", 64);
	public static SpriteSheet DeemoUp1 = new SpriteSheet("/textures/run up 2.png", 64);
	public static SpriteSheet DeemoDown0 = new SpriteSheet("/textures/run down 1.png", 64);
	public static SpriteSheet DeemoDown1 = new SpriteSheet("/textures/run down 2.png", 64);
	public static SpriteSheet DeemoLeft0 = new SpriteSheet("/textures/run left 1.png", 64);
	public static SpriteSheet DeemoLeft1 = new SpriteSheet("/textures/run left 2.png", 64);
	public static SpriteSheet DeemoLeft2 = new SpriteSheet("/textures/run left 3.png", 64);
	public static SpriteSheet DeemoLeft3 = new SpriteSheet("/textures/run left 4.png", 64);
	public static SpriteSheet DeemoRight0 = new SpriteSheet("/textures/run right 1.png", 64);
	public static SpriteSheet DeemoRight1 = new SpriteSheet("/textures/run right 2.png", 64);
	public static SpriteSheet DeemoRight2 = new SpriteSheet("/textures/run right 3.png", 64);
	public static SpriteSheet DeemoRight3 = new SpriteSheet("/textures/run right 4.png", 64);
	
	
	
	
	public SpriteSheet (String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
