package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;

import dev.codenmore.tilegame.tiles.Tile;

public class World {

	private int width = 255/15;
	private int height = 300/15;
	private int randomx, randomy;
	public static int[][] tiles;

	
	
	public World(String path){
		tiles = new int[width][height];

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y] = 0;
			}
		}
		loadWorld(path);
	
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				if(tiles[x][y] != 0)
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		Tile t = Tile.tiles[tiles[x][y]];
		return t;
	}
	
	private void loadWorld(String path){
		for(int z = 0; z < 20; z++) {
			randomx = (int)( Math.random() * (width - 3) + 2); 
			randomy = (int)( Math.random() * (height - 5) + 1); 
			if(tiles[randomx-1][randomy-1] != 1 && tiles[randomx+1][randomy-1] != 1 && tiles[randomx+1][randomy+1] != 1 &&tiles[randomx-1][randomy+1] != 1)
				tiles[randomx][randomy] = 1;
		}
	}
	
}
