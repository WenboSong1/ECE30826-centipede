package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.worlds.World;


public class centi extends Creature{


	
	public centi(Game game, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		health = 2;
	}

	@Override
	public void tick() {
		update();
		movecenti();
	}
	
	private void update(){
		
		
		if(centispeed > 0) {
			if(x <= 215 && World.tiles[(int) (x/15 + 1)][(int) y/15] != 0) {
				centispeed = -centispeed;
				if(y/15 != 16)
					y += 15; 
			}
		}
		else {
			if(x <= 230 && World.tiles[(int) x/15][(int) y/15] != 0) {
				centispeed = -centispeed;
				if(y/15 != 16)
					y += 15; 
			}
		}
		if(x <= 0 || x >= 245) {
			centispeed = -centispeed;
			if(y/15 != 16)
			y += 15; 
		}

	}

	@Override
	public void render(Graphics g) {
		if(health == 2)
			g.drawImage(Assets.centi1, (int) x, (int) y, width, height, null);
		if(health == 1)
			g.drawImage(Assets.centi2, (int) x, (int) y, width, height, null);
	}
}
