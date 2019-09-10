package dev.codenmore.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.input.MouseManager;
import dev.codenmore.tilegame.states.GameState;

import dev.codenmore.tilegame.states.State;


public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;
	public static int point = 0;
	public int health = 3;
	//public static Player player;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		
		gameState = new GameState(this);
		State.setState(gameState);
	}
	
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		g.setColor(Color.black);
		g.fillRect(0, 0, 255, 300);
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		//player = new Player(this, 125, 275);
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				if( Player.dead == 1)
				{
					display.getFrame().addKeyListener(keyManager);
					display.getFrame().addMouseListener(mouseManager);
					display.getFrame().addMouseMotionListener(mouseManager);
					display.getCanvas().addMouseListener(mouseManager);
					display.getCanvas().addMouseMotionListener(mouseManager);
					Assets.init();

					
					gameState = new GameState(this);
					State.setState(gameState);
					health--;
				}
				if(health == 0) {
					bs = display.getCanvas().getBufferStrategy();
					if(bs == null){
						display.getCanvas().createBufferStrategy(3);
						return;
					}
					g = bs.getDrawGraphics();
					//Clear Screen
					g.clearRect(0, 0, width, height);
			        g.setColor(Color.green);
			        g.fillRect(0, 0, 250 , 300);
			    	if(State.getState() != null)
						State.getState().render(g);
					
					//End Drawing!
					bs.show();
					g.dispose();
			        stop();
				}
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}











