package Main.entity.mob;

import java.awt.Rectangle;

import Main.entity.Entity;
import Main.graphics.Sprite;

public abstract class Mob extends Entity {
	
	public Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	public Rectangle hitbox;
	
	
	public void move(int xm, int ym) {
		if (xm > 0) dir = 1;
		else if (xm < 0) dir = 3;
		else if (ym > 0) dir = 2;
		else if (ym < 0) dir = 0;
		
		if(!collision(xm, ym)) {
			x += xm;
			y += ym;
			hitbox.setLocation(x+12, y+12);
		}
	}
	
	public void update() {}
	
	private boolean collision(int xm, int ym) {
		Rectangle temp = new Rectangle(x+xm+12,y+ym+12,40,40);
		
		for(int i=0; i<level.collisionbox.length; i+=1) {
			if(level.collisionbox[i] != null && level.collisionbox[i].intersects(temp)) return true;
		}
		
		return false;
	}
	
	public void render() {
	}
}
