package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.entities.Entity;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 3;
	public static final float DEFAULT_SPEED = 1.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 15,
							DEFAULT_CREATURE_HEIGHT = 15;
	
	public int health;
	protected int centihealth;
	public static int playerhealth;
	protected float speed, centispeed;
	protected float xMove, yMove;

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		health = DEFAULT_HEALTH;
		playerhealth = DEFAULT_HEALTH;
		centihealth = DEFAULT_HEALTH - 1;
		speed = DEFAULT_SPEED;
		centispeed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	
	public void movecenti() {
		x += centispeed;
	}
	
	//GETTERS SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
