package hecdelc.snake;

import javax.swing.JFrame;

/**
 *
 * @author Hector Del Campo Pando
 *
 */
public class Main {

	public static final int FRAME_WIDTH = 800;

	public static final int FRAME_HEIGHT = 800;

	private static final String GAME_NAME = "Snake";


	public static void main(String[] args) {

		JFrame frame = new JFrame(GAME_NAME);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Board board = new Board();
		frame.add(board);
		frame.addKeyListener(board);
		frame.setVisible(true);

	}

}
