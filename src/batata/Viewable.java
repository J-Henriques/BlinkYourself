package batata;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Viewable extends JPanel implements Runnable, KeyListener {

	// quit flag
	private boolean quit = false;
	private Background bg1 = new Background(800, 0);
	private Background bg2 = new Background(0, 0);
	private Image background;
	private Image image;
	private Graphics second;
	private Level blocks[];
	private Player cube;
	private Thread cuber;

	private ArrayList<Image> move = new ArrayList<Image>();
	private ArrayList<Image> jump = new ArrayList<Image>();
	private ArrayList<Image> blink = new ArrayList<Image>();

	public Viewable() {
		this.starter();	
	}
	
	public Viewable(JFrame parent) {
		this.starter();
	}
	
	private void starter() {
		cube = new Player();
		cuber = new Thread(cube);
		// Load move img
		move.add(new ImageIcon("res/player/move1.png").getImage());
		move.add(new ImageIcon("res/player/move2.png").getImage());
		move.add(new ImageIcon("res/player/move3.png").getImage());
		move.add(new ImageIcon("res/player/move4.png").getImage());

		// Load jump img
		jump.add(new ImageIcon("res/player/jump1.png").getImage());
		jump.add(new ImageIcon("res/player/jump2.png").getImage());
		jump.add(new ImageIcon("res/player/jump3.png").getImage());
		jump.add(new ImageIcon("res/player/jump4.png").getImage());

		// Load Blink
		blink.add(new ImageIcon("res/player/BlinkInit0.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkInit1.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkInit2.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkInit3.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkInit4.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkInit5.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd6.png").getImage());

		blink.add(new ImageIcon("res/player/BlinkEnd0.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd1.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd2.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd3.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd4.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd5.png").getImage());
		blink.add(new ImageIcon("res/player/BlinkEnd6.png").getImage());
		Thread thread = new Thread(this);
		thread.start();
		cuber.start();
		this.initResources();
		
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override
	public void run() {
		while (!this.quit) {
			bg1.update();
			bg2.update();
			cube.update();
			
			this.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		for (int i = 0; i < this.blocks.length; i++) {
			blocks[i].update();
			g.drawImage(new ImageIcon("res/textures/brick1.jpg").getImage(),
					blocks[i].getPosX(), 500, this);

			// System.out.println("" + blocks[i].getPosX());
		}
		if(cube.isMoving()) {
			g.drawImage( move.get(Player.movement),cube.getPosX(), cube.getPosY(), this);
		}
		
		if(cube.isJumped() ){
			g.drawImage( jump.get(Player.jumping),cube.getPosX(), cube.getPosY(), this);
		}
		

		if(cube.isBlinked() ){
			g.drawImage( blink.get(Player.blinking),cube.getPosX(), cube.getPosY(), this);
		}
	}

	private void initResources() {
		background = new ImageIcon("res/background/background.png").getImage();
		this.blocks = new Level[22];
		int temp = 0;
		for (int i = 0; i < blocks.length; i++) {
			temp = i * 40;
			this.blocks[i] = new Level(temp, 0);
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			cube.blink();
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("Jump");
			cube.jump();
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;

		case KeyEvent.VK_DOWN:
			break;

		case KeyEvent.VK_LEFT:
			break;

		case KeyEvent.VK_RIGHT:
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Stop jumping");
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
}
