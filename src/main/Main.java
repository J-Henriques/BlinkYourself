package main;

import java.awt.GridLayout;

import javax.swing.JFrame;

import batata.Viewable;

public class Main extends JFrame{
	
	
	public Main() {
		
		this.init();
	}
	
	public void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 1, 0, 0));
		setSize(800,600);
		Viewable view = new Viewable(this);
		
		Thread viewThread = new Thread(view);
		
		add(view);
		setVisible(true);
		
		viewThread.start();
	}

	public static void main(String[] args) {
		new Main();
	}
}
