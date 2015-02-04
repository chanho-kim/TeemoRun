package Main.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite teemoUp = new Sprite(64, 0, 0, SpriteSheet.chara);
	public static Sprite teemoUp2 = new Sprite(64, 1, 0, SpriteSheet.chara);
	public static Sprite teemoLeft = new Sprite(64, 0, 1, SpriteSheet.chara);
	public static Sprite teemoLeft2 = new Sprite(64, 1, 1, SpriteSheet.chara);
	public static Sprite teemoRight = new Sprite(64, 0, 2, SpriteSheet.chara);
	public static Sprite teemoRight2 = new Sprite(64, 1, 2, SpriteSheet.chara);
	public static Sprite teemoDown = new Sprite(64, 0, 3, SpriteSheet.chara);
	public static Sprite teemoDown2 = new Sprite(64, 1, 3, SpriteSheet.chara);
	public static Sprite tile0 = new Sprite(64, 0, 0, SpriteSheet.characters);
	public static Sprite tile1 = new Sprite(64, 0, 1, SpriteSheet.characters);
	public static Sprite cookie = new Sprite(64, 1, 0, SpriteSheet.characters);
		
	public Sprite (int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[size * size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y+=1) {
			for (int x = 0; x < SIZE; x+=1) {
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
			}
		}
	}
	
}
