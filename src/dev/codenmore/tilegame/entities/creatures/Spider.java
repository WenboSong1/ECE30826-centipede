package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Assets;

public class Spider extends Creature {

	public static int dead;
	private int movex, movey;
	
	public Spider(Game game, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		health = 2;
		movex = (int)speed;
		movey = (int)speed;
	}

	@Override
	public void tick() {
		move();
	}
	
	private void move(){
		x += movex;
		y += movey;
		if(x >= 240)
			movex = -(int)speed;
		else if(x <= 15)
			movex = (int)speed;
		if(y <= 15)
			movey = (int)speed;
		else if(y >= 285)
			movey = -(int)speed;
			 
	}

	@Override
	public void render(Graphics g) {
		if(health == 2)
			g.drawImage(Assets.spider1, (int)x, (int)y, width, height, null);
		if(health == 1)
			g.drawImage(Assets.spider2, (int)x, (int)y, width, height, null);
	}

}
