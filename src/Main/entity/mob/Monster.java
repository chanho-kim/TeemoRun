package Main.entity.mob;

import Main.graphics.Screen;
import Main.graphics.Sprite;
import Main.level.Level;

public class Monster extends Mob{
	
	private boolean walking = false;
	private int anim = 0;
	public boolean monsterReady = true;
	public int monsterPos;
	private int walkingDir = -1;
	
	public Monster(int x, int y, Sprite sprite, Level level){
		this.x = x;
		this.y = y;
		this.level = level;
		this.sprite = sprite;
	}
	
	public void update(int dir) {
		int xa, ya;
		xa = ya = 0;
		if(anim < 7500) anim+=1;
		else anim = 0;

		if(monsterReady){
			monsterReady = false;
			walkingDir = dir;
		}
		
		if(walkingDir == 0) y-=1; 
		else if(walkingDir == 1) x+=1;
		else if(walkingDir == 2) y+=1;
		else if(walkingDir == 3) x-=1;
		
	}
	
	public void render(Screen screen) {
		if(dir==0) {
			sprite = Sprite.teemoUp;
			if (walking && anim % 20 > 10) {
				sprite = Sprite.teemoUp2;
			}
		}
		else if(dir==1) {
			sprite = Sprite.teemoRight;
			if (walking && anim % 20 > 10) {
				sprite = Sprite.teemoRight2;
			}
		}
		else if(dir==2) {
			sprite = Sprite.teemoDown;
			if (walking && anim % 20 > 10) {
				sprite = Sprite.teemoDown2;
			}
		}
		else if(dir==3) {
			sprite = Sprite.teemoLeft;
			if (walking && anim % 20 > 10) {
				sprite = Sprite.teemoLeft2;
			}
		}
		screen.renderMonster(x, y, this);
	}
}
