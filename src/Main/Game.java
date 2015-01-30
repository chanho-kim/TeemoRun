package Main;

public class Game implements Runnable{

	public static int width = 900;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	
	private Thread thread;
	private boolean running = false;
	
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
		while(running) {
			
		}
	}
}
