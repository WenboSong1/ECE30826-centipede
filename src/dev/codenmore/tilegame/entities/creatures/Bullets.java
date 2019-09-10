package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Assets;
//import dev.codenmore.tilegame.worlds.World;


public class Bullets extends Creature{

	
	public Bullets(Game game, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	}

	@Override
	public void tick() {
		move();
	}
	
	private void move(){
		y -= speed * 2;	
		


	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.shot, (int) x + 7, (int) y, 2, 15, null);
	}
}
