package Main.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	public void generateLevel() {
		for (int y = 0; y < height; y+=1) {
			for (int x = 0; x < width; x+=1) {
				tileInt[x+y*width] = random.nextInt(2);
			}
		}
	}

}
