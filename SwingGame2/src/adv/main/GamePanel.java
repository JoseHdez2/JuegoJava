package adv.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import adv.level.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
	implements Runnable, KeyListener{

	// Dimensiones del panel.
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	// Escala de
	public static final int SCALE = 2;
	
	// Game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;

	// Game state manager
	private GameStateManager gsm;
	
	public GamePanel() {
		super();
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	/*
	 * JPanel is done loading and can create a new thread.
	 */
	public void addNotify() {
		super.addNotify();
		if (thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init () {
		image = new BufferedImage (
					WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB
				);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}
	
	public void run(){
		//Initialize everything
		init();
		
		//Time-stamps
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			//Time-stamp
			start = System.nanoTime();
			
			//Do these 3 things:
			update();
			draw();
			drawToScreen();
			
			//Time-stamp
			elapsed = System.nanoTime() - start;
			
			//Time-stamp
			wait = targetTime - elapsed / 1000000;
			
			//avoid negative wait times
			if (wait < 0) wait = 5;
			
			//wait that amount of time
			try {
				Thread.sleep(wait);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		gsm.update();
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
	
	
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
}
