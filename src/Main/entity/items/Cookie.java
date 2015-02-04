package Main.entity.items;

import java.awt.Rectangle;

import Main.entity.Entity;
import Main.graphics.Screen;
import Main.graphics.Sprite;

public class Cookie extends Entity {
	
	public Sprite sprite;
	public Rectangle hitbox;
	
	public Cookie(int x, int y) {
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x+22,y+22,20,20);
		sprite = Sprite.cookie;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		screen.renderCookie(x, y, this);
	}
}
