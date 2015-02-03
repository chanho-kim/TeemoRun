package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Main.graphics.Screen;
import Main.input.Keyboard;
import Main.level.Level;
import Main.level.RandomLevel;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static int width = 1216;
	public static int height = 832;
	public static int scale = 1;
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		
		key = new Keyboard();
		addKeyListener(key);
		
		level = new RandomLevel(19, 13);
	}
	
	public synchronized void start() {
		thread = new Thread(this, "TeemoRun");
		running = true;
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		final double ns = 1000000000.0 / 60.0;
		long lastTime = System.nanoTime();
		double delta = 0;
		
		int frames = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		
		requestFocus();
		
		//update happens 60 times a second, fps is not limited
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates += 1;
				delta -= 1;
			}
			render();
			frames+=1;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("TeemoRun | " + updates + " updates per sec, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void render() {
		//smooth out the transition
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		level.render(screen);
		
		for (int i = 0; i< pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		//swapping buffers = blitting
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	private void update() {
		key.update();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("TeemoRun");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
