package hecdelc.snake;

import javax.swing.JFrame;

public class Main {
	
	private static final String GAME_NAME = "Snake";

	public static void main(String[] args) {
		
		JFrame frame = new JFrame(GAME_NAME);
		frame.setSize(800, 800);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Snake snake = new Snake();
		Board board = new Board(snake);
		frame.add(board);
		frame.addKeyListener(snake);
		
	}

}
