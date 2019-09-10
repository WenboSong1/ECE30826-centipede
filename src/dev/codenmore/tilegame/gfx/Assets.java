package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	
	public static BufferedImage player, mush1, mush2, mush3, centi1, spider1, shot, centi2, spider2;

	public static void init(){
		player = ImageLoader.loadImage("/textures/player.png");
		mush1 = ImageLoader.loadImage("/textures/mushroom0.png");
		mush2 = ImageLoader.loadImage("/textures/mushroom1.png");
		mush3 = ImageLoader.loadImage("/textures/mushroom2.png");
		centi1 = ImageLoader.loadImage("/textures/centipede0.png");
		centi2 = ImageLoader.loadImage("/textures/centipede1.png");
		spider1 = ImageLoader.loadImage("/textures/spider0.png");
		spider2 = ImageLoader.loadImage("/textures/spider1.png");
		shot = ImageLoader.loadImage("/textures/shot.png");
	}
	
}
