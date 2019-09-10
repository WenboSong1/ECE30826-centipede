package dev.codenmore.tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Assets;

public class Player extends Creature {

	private Game game;
	private int z,h;
	public static int dead;
	
	public Player(Game game, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.game = game;
	}

	@Override
	public void tick() {
		getInput();
	}
	
	private void getInput(){
		x = game.getMouseManager().getMouseX();

		y = game.getMouseManager().getMouseY();

		if(y <= 240)
			y = 240;
		if(x >= 235)
			x = 235;
		if(y >= 285)
			y = 285;
		z = (int)x/15;
		h= (int)y/15;
		x = z * 15;
		y = h * 15;
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)x, (int)y, width, height, null);
		if(game.health == 0) {
			g.setColor(Color.blue);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g.drawString("Game over! You get: " + Game.point, 15, 150);
		}
		else {
		g.setColor(Color.red);
		g.drawString("points:" + Game.point +" lives:" + game.health, 0, 285);
		}
	}

}
