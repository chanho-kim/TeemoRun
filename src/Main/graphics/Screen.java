package Main.graphics;

import java.util.Random;

import Main.entity.items.Cookie;
import Main.entity.mob.Player;
import Main.level.tile.Tile;

public class Screen {
	public int width, height;
	public int[] pixels;
	//how big will the tiles be? 64 in each row and column for now
	public int[] tiles = new int[19*13];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < 19 * 13; i += 1) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		for (int y = 0; y < tile.sprite.SIZE; y+=1) {
			for (int x = 0; x < tile.sprite.SIZE; x+=1) {
				pixels[(xp+x) + (yp+y) * width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Player player) {
		for (int y = 0; y < 64; y+=1) {
			for (int x = 0; x < 64; x+=1) {
				//if pixel is NOT certain color, then display the pixels. otherwise just pass
				if(player.sprite.pixels[x+y*player.sprite.SIZE] != 0xffff00ff) {
					pixels[(xp+x) + (yp+y) * width] = player.sprite.pixels[x+y*player.sprite.SIZE];
				}
			}
		}
	}
	
	public void renderCookie(int xp, int yp, Cookie cookie) {
		for (int y = 0; y < 64; y+=1) {
			for (int x = 0; x < 64; x+=1) {
				//if pixel is NOT certain color, then display the pixels. otherwise just pass
				if(cookie.sprite.pixels[x+y*cookie.sprite.SIZE] != 0xffff00ff) {
					pixels[(xp+x) + (yp+y) * width] = cookie.sprite.pixels[x+y*cookie.sprite.SIZE];
				}
			}
		}
	}
}
