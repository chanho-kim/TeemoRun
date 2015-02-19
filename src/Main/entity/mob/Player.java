package Main.entity.mob;

import java.awt.Rectangle;

import Main.graphics.Screen;
import Main.graphics.Sprite;
import Main.input.Keyboard;
import Main.level.Level;

public class Player extends Mob {
	
	private Keyboard input;
	private int anim = 0;
	private boolean walking = false;
	
	
	public Player(Keyboard input, Level level) {
		x = 576;
		y = 384;
		this.input = input;
		this.level = level;
		sprite = Sprite.teemoDown;
		hitbox = new Rectangle(588,396,40,40);
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.teemoDown;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if(anim < 7500) anim+=1;
		else anim = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else walking = false;
	}
	
	public void render(Screen screen) {
		if(dir==0) {
			sprite = Sprite.DeemoUp;
			if (walking && anim % 20 > 10) {
				sprite = Sprite.DeemoUp2;
			}
		}
		else if(dir==1) {
			sprite = Sprite.DeemoRight;
			if (walking && anim % 40 > 30) {
				sprite = Sprite.DeemoRight2;
			}
			else if (walking && anim % 40 > 20) {
				sprite = Sprite.DeemoRight3;
			}
			else if (walking && anim % 40 > 10) {
				sprite = Sprite.DeemoRight4;
			}
		}
		else if(dir==2) {
			sprite = Sprite.DeemoDown;
			if (walking && anim % 20 > 10) {
				sprite = Sprite.DeemoDown2;
			}
		}
		else if(dir==3) {
			sprite = Sprite.DeemoLeft;
			if (walking && anim % 40 > 30) {
				sprite = Sprite.DeemoLeft2;
			}
			else if (walking && anim % 40 > 20) {
				sprite = Sprite.DeemoLeft3;
			}
			else if (walking && anim % 40 > 10) {
				sprite = Sprite.DeemoLeft4;
			}
		}
		screen.renderPlayer(x, y, this);
	}
}
