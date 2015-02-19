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
	
	public static Sprite DeemoUp = new Sprite(64,0,0,SpriteSheet.DeemoUp0);
	public static Sprite DeemoUp2 = new Sprite(64,0,0,SpriteSheet.DeemoUp1);
	public static Sprite DeemoDown = new Sprite(64,0,0,SpriteSheet.DeemoDown0);
	public static Sprite DeemoDown2 = new Sprite(64,0,0,SpriteSheet.DeemoDown1);
	public static Sprite DeemoLeft = new Sprite(64,0,0,SpriteSheet.DeemoLeft0);
	public static Sprite DeemoLeft2 = new Sprite(64,0,0,SpriteSheet.DeemoLeft1);
	public static Sprite DeemoLeft3 = new Sprite(64,0,0,SpriteSheet.DeemoLeft2);
	public static Sprite DeemoLeft4 = new Sprite(64,0,0,SpriteSheet.DeemoLeft3);
	public static Sprite DeemoRight = new Sprite(64,0,0,SpriteSheet.DeemoRight0);
	public static Sprite DeemoRight2 = new Sprite(64,0,0,SpriteSheet.DeemoRight1);
	public static Sprite DeemoRight3 = new Sprite(64,0,0,SpriteSheet.DeemoRight2);
	public static Sprite DeemoRight4 = new Sprite(64,0,0,SpriteSheet.DeemoRight3);
	
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
