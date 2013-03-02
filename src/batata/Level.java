package batata;

import java.awt.Rectangle;

public class Level {
	private int tileSize = 40;
	private int posX, posY;
	private int speedX = 10;
	private int speedY = 10;
	private Rectangle tile;
	private int bottomZero = 0;

	public Level(int x, int y) {
		tile = new Rectangle(x, y, tileSize, 40);
		this.posX = x;
	}
	
	public void update()
	{
		if(posX <= -40){
			posX += 840;
			tile.setLocation(posX, 0);
		}
		else{
			posX-=speedX;
			tile.setLocation(posX, 0);
		}
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public void setPosX( int posX) {
		this.posX = posX;
	}
}
