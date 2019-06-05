package jtp2019;

import java.awt.Rectangle;

public class Map {
	protected int[][] table;
	private final int tile = 16;
	
	public Map() {

	}
	
	public int tableActions(Rectangle r) {
		
		for(int i = 0; i < table.length; i++)
			for(int j = 0; j < table[i].length; j++) {

				if(table[i][j] == 1) {
					
					Rectangle obstacle = new Rectangle(j * tile, i * tile, tile, tile);
					if(r.intersects(obstacle)) return 1;
				}
				if(table[i][j] == 2) {
					
					Rectangle obstacle = new Rectangle(j * tile, i * tile, tile, tile);
					if(r.intersects(obstacle)) return 2;
				}
				if(table[i][j] == 3) {
					
					Rectangle obstacle = new Rectangle(j * tile, i * tile, tile, tile);
					if(r.intersects(obstacle)) return 3;
				}
				if(table[i][j] == 4) {
					
					Rectangle obstacle = new Rectangle(j * tile, i * tile, tile, tile);
					if(r.intersects(obstacle)) return 4;
				}
				if(table[i][j] == 5) {
					
					Rectangle obstacle = new Rectangle(j * tile, i * tile, tile, tile);
					if(r.intersects(obstacle)) return 5;
				}
			}
		return 0;
	}
}
