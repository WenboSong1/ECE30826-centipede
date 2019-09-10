package dev.codenmore.tilegame.states;

//import java.awt.Color;
import java.awt.Graphics;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.Bullets;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.creatures.Spider;
import dev.codenmore.tilegame.entities.creatures.centi;
import dev.codenmore.tilegame.worlds.World;
import java.io.*;


public class GameState extends State {
	
	
	
	private Player player;
	public World world;
	private centi Centi1;
	private centi Centi2;
	private centi Centi3;
	private centi Centi4;
	private centi Centi5;
	private centi Centi6;
	private centi Centi7;
	private Spider spider;
	private Bullets[] bullet;
	private int i = 0;
	private int press = 0;
	private int full = 0;
	private int bulletx, bullety;
	private  int[][] ne;
	//private int health = 3;

	
	public GameState(Game game){
		super(game);
		player = new Player(game, 125, 275);
		Centi1 = new centi(game, 105, 0);
		Centi2 = new centi(game, 90, 0);
		Centi3 = new centi(game, 75, 0);
		Centi4 = new centi(game, 60, 0);
		Centi5 = new centi(game, 45, 0);
		Centi6 = new centi(game, 30, 0);
		Centi7 = new centi(game, 15, 0);
		spider = new Spider(game, 0, 200);
		bullet = new Bullets[20];
		if(Player.dead == 1) {
			ne = new int[17][20];
			//Player.dead = 0;
			for(int x = 0; x < 17; x++) {
				for(int y = 0; y < 20; y++) {
					if(World.tiles[x][y] != 0) {
						if(World.tiles[x][y] != 1)
							Game.point += 10;
						World.tiles[x][y] = 1;
						
					}
				}
				ne = World.tiles;
			}

		}
		world = new World("");
		//player = new Player(game, 125, 275);
		if(Player.dead == 1) {
				World.tiles = ne;
				Player.dead = 0;
		}
	}
	
	@Override
	public void tick(){
		world.tick();
		player.tick();
		if(Centi1.health == 0 && Centi2.health == 0 && Centi3.health == 0 && Centi4.health == 0 && Centi5.health == 0 && Centi6.health == 0 && Centi7.health == 0) {
			Centi1 = new centi(game, 105, 0);
			Centi2 = new centi(game, 90, 0);
			Centi3 = new centi(game, 75, 0);
			Centi4 = new centi(game, 60, 0);
			Centi5 = new centi(game, 45, 0);
			Centi6 = new centi(game, 30, 0);
			Centi7 = new centi(game, 15, 0);
			Game.point += 600;
		}
		Centi1.tick();
		Centi2.tick();
		Centi3.tick();
		Centi4.tick();
		Centi5.tick();
		Centi6.tick();
		Centi7.tick();
		spider.tick();
		if((int) Centi1.getY() == 240 || (int) Centi2.getY() == 240 || (int) Centi3.getY() == 240 || (int) Centi4.getY() == 240 || (int) Centi5.getY() == 240 || (int) Centi6.getY() == 240 || (int) Centi7.getY() == 240) {
			if((int) player.getY() < 255) {
				if((int) player.getX() >= (int) Centi1.getX() && (int) player.getX() <= ((int) Centi1.getX() + 15) && Centi1.health != 0)
					Player.dead = 1;
				if((int) player.getX() >= (int) Centi2.getX() && (int) player.getX() <= ((int) Centi2.getX() + 15) && Centi2.health != 0)
					Player.dead = 1;
				if((int) player.getX() >= (int) Centi3.getX() && (int) player.getX() <= ((int) Centi3.getX() + 15) && Centi3.health != 0)
					Player.dead = 1;
				if((int) player.getX() >= (int) Centi4.getX() && (int) player.getX() <= ((int) Centi4.getX() + 15) && Centi4.health != 0)
					Player.dead = 1;
				if((int) player.getX() >= (int) Centi5.getX() && (int) player.getX() <= ((int) Centi5.getX() + 15) && Centi5.health != 0)
					Player.dead = 1;
				if((int) player.getX() >= (int) Centi6.getX() && (int) player.getX() <= ((int) Centi6.getX() + 15) && Centi6.health != 0)
					Player.dead = 1;
				if((int) player.getX() >= (int) Centi7.getX() && (int) player.getX() <= ((int) Centi7.getX() + 15) && Centi7.health != 0)
					Player.dead = 1;
			}
		}
		if((int) player.getY() >= (int) spider.getY() && (int) player.getY() <= ((int) spider.getY() + 15) && spider.health != 0) {
			if((int) player.getX() >= (int) spider.getX() && (int) player.getX() <= ((int) spider.getX() + 15) && spider.health != 0) {
				Player.dead = 1;
			}
		}
		
		for(int y = 0; y < 19; y++) {
			if(bullet[y] != null) {
			bulletx = (int) (bullet[y].getX() / 15);
			bulletx = bulletx * 15;
			bullety = (int) (bullet[y].getY() / 15);
			bullety = bullety * 15;
			}
			if(bullet[y] != null) {
				if(bullety >= (int) Centi1.getY() && bullety <= (int) Centi1.getY() + 10) {
					if(bulletx >= (int) Centi1.getX() && bulletx <= (int) Centi1.getX() + 10) {
						if(Centi1.health != 0) {
						bullet[y] = null;
						Centi1.health--;
						if(Centi1.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
					}
				}
				if(bullety >= (int) Centi2.getY() && bullety <= (int) Centi2.getY() + 10) {
					if(bulletx >= (int) Centi2.getX() && bulletx <= (int) Centi2.getX() + 10) {
						if(Centi2.health != 0) {
						bullet[y] = null;
						Centi2.health--;
						if(Centi2.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
					}
				}
				if(bullety >= (int) Centi3.getY() && bullety <= (int) Centi3.getY() + 10) {
					if(bulletx >= (int) Centi3.getX() && bulletx <= (int) Centi3.getX() + 10) {
						if(Centi3.health != 0) {
						bullet[y] = null;
						Centi3.health--;
						if(Centi3.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
					}
				}
				if(bullety >= (int) Centi4.getY() && bullety <= (int) Centi4.getY() + 10) {
					if(bulletx >= (int) Centi4.getX() && bulletx <= (int) Centi4.getX() + 10) {
						if(Centi4.health != 0) {
						bullet[y] = null;
						Centi4.health--;
						if(Centi4.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
					}
				}
				if(bullety >= (int) Centi5.getY() && bullety <= (int) Centi5.getY() + 10) {
					if(bulletx >= (int) Centi5.getX() && bulletx <= (int) Centi5.getX() + 10) {
						if(Centi5.health != 0) {
						bullet[y] = null;
						Centi5.health--;
						if(Centi5.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
					}
				}
				if(bullety >= (int) Centi6.getY() && bullety <= (int) Centi6.getY() + 10) {
					if(bulletx >= (int) Centi6.getX() && bulletx <= (int) Centi6.getX() + 10) {
						if(Centi6.health != 0) {
						bullet[y] = null;
						Centi6.health--;
						if(Centi6.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
					}
				}
				if(bullety >= (int) Centi7.getY() && bullety <= (int) Centi7.getY() + 10) {
					if(bulletx >= (int) Centi7.getX() && bulletx <= (int) Centi7.getX() + 10) {
						if(Centi7.health != 0) {
						bullet[y] = null;
						Centi7.health--;
						if(Centi7.health == 0)
							Game.point += 5;
						else
							Game.point += 2;
						}
						}
				}
				if(bullety >= (int) spider.getY() && bullety <= (int) spider.getY() + 10) {
					if(bulletx >= (int) spider.getX() && bulletx <= (int) spider.getX() + 10) {
						if(spider.health != 0) {
						bullet[y] = null;
						spider.health--;
						if(spider.health == 0)
							Game.point += 600;
						else
							Game.point += 100;
						}
					}
				}
			}
		}
		
		if(game.getMouseManager().isLeftPressed())
			press = 1;
		if(press == 1 && !game.getMouseManager().isLeftPressed()) {
			bullet[i] = new Bullets(game,(int) player.getX(),(int) player.getY());
			i++;
			press = 0;
			if(i >= 19) {
				i = 0;
				full = 1;
			}
			play_sound();
		}
		if(full == 0) {
			for(int x = 0; x < i;x++) {
				if(bullet[x] != null) {
					bullet[x].tick();
					bulletx = (int) (bullet[x].getX()/15);
					bullety = (int) (bullet[x].getY()/15);
					if(bullety >= 0) {
						if(World.tiles[bulletx][bullety] != 0) {
							World.tiles[bulletx][bullety]++;
							if(World.tiles[bulletx][bullety] == 4)
								Game.point += 5;
							else
								Game.point++;
							bullet[x] = null;
						}
						if(World.tiles[bulletx][bullety] > 3)
							World.tiles[bulletx][bullety] = 0;
							//Game.point += 5;
					}
				}
			}
		}
		else {
			for(int x = 0; x < 19;x++) {
				if(bullet[x] != null) {
					bullet[x].tick();
					bulletx = (int)bullet[x].getX()/15;
					bullety = (int)bullet[x].getY()/15;
					if(bullety >= 0) {
						if(World.tiles[bulletx][bullety] != 0) {
							World.tiles[bulletx][bullety]++;
							bullet[x] = null;
							if(World.tiles[bulletx][bullety] == 4)
								Game.point += 5;
							else
								Game.point++;
						}
						if(World.tiles[bulletx][bullety] > 3)
							World.tiles[bulletx][bullety] = 0;
					}
				}
			}
		}
			

	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
		player.render(g);
		if(Centi1 != null)
			Centi1.render(g);
		if(Centi2 != null)
			Centi2.render(g);
		if(Centi3 != null)
			Centi3.render(g);
		if(Centi4 != null)
			Centi4.render(g);
		if(Centi5 != null)
			Centi5.render(g);
		if(Centi6 != null)
			Centi6.render(g);
		if(Centi7 != null)
			Centi7.render(g);
		if(full == 0) {
			for(int x = 0; x < i;x++) {
				if(bullet[x] != null)
				bullet[x].render(g);
			}
		}
		else {
			for(int x = 0; x < 19;x++) {
				if(bullet[x] != null)
				bullet[x].render(g);
			}
		}
		spider.render(g);
	}
	
    public void play_sound() {
        try {
            // Open an audio input stream.
            File soundFile = new File("C:\\Users\\swb97\\Downloads\\centipede-song391\\res\\sound\\fire_shot.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
