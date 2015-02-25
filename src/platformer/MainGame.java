package platformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class MainGame extends Canvas implements Runnable{
	
	public static int width = 600;
	public static int height = width /16 * 9;
	public static String title = "GAME";
	
	private Thread thread;
	private JFrame frame;
	
	private boolean running = false;
	
	public MainGame(){
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		frame = new JFrame();
		thread = new Thread();
	}
	
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this, title);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				delta--;
			}
			System.out.println("running...");
			render();
		}
		stop();
	}
	
	public void update(){
		
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.dispose();
		bs.show();
		
		
		
	}
	
	public static void main(String[] args){
		MainGame game = new MainGame();
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setTitle(title);
		game.frame.setVisible(true);
		game.start();
		
	}

}
