package batata;

public class Player implements Runnable{
	private static int GROUND = 420;
	private static int jDistance = -25;
	private int posX, posY, speedY;
	private boolean isJumped, isBlinked, moving;
	public static int movement = 0;
	public static int jumping = 0;
	public static int blinking = 0;
	private boolean blinkFinish = false;
	private int blinkMovment = 10;
	
	public Player(){
		posX = 20;
		posY = 420;
		moving = true;
	}
	
	public static int getGround() {
		return GROUND;
	}

	public static int getjDistance() {
		return jDistance;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isJumped() {
		return isJumped;
	}

	public boolean isBlinked() {
		return isBlinked;
	}

	public static void setGround(int ground) {
		GROUND = ground;
	}

	public static void setjDistance(int jDistance) {
		Player.jDistance = jDistance;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setJumped(boolean isJumped) {
		this.isJumped = isJumped;
	}

	public void setBlinked(boolean isBlinked) {
		this.isBlinked = isBlinked;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void update(){
	     // Updates Y Position
        posY += speedY;
        if (posY + speedY >= GROUND) {
            posY = GROUND;
        }

        // Handles Jumping
        if (isJumped == true) {
            speedY += 1;

            if (posY + speedY >= GROUND) {
                posY = GROUND;
                speedY = 0;
                isJumped = false;
                moving = true;
            }

        }
        
        if( blinkFinish && Player.blinking == 0)
        {
        	isBlinked = false;
        	moving = true;
        	blinkFinish = false;
        	posX -= 100;
        }
	}

	  public void jump() {
		  if (isJumped == false) {
	            speedY = jDistance;
	            isJumped = true;
	            moving = false;
	        }
	  }
	public boolean isMoving(){
		return moving;
	}

	@Override
	public void run() {
		while( true ) {
			
			try {
				Thread.sleep(75);
				if(this.isMoving()) {
					if(!isBlinked() && posX > 30)
						posX-=10;
						Player.movement++;
					if( Player.movement == 4 )
						Player.movement = 0;
				}
				if(this.isJumped()) {
					Player.jumping++;
					if( Player.jumping == 4 )
						Player.jumping = 0;
				}
				if(this.isBlinked()) {
					if(!blinkFinish)
						Player.blinking++;
					if( Player.blinking == 6 )
						blinkFinish = true;
					if(blinkFinish)
						Player.blinking--;
					if(Player.blinking<0)
						Player.blinking = 0;
				}
			} catch (InterruptedException e) {
	
				e.printStackTrace();
			}
		}
		
	}

	public void blink() {
		if( !isBlinked )
		{
			isBlinked = true;
			moving = false;
			this.posX += 200;
		}
		
	}
	
}
