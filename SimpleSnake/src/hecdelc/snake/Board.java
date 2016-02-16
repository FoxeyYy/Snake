package hecdelc.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private final static Color SNAKE_COLOR = Color.BLUE;
	
	private final static Color BOARD_COLOR = Color.BLACK;
	
	private final static Color CHERRY_COLOR = Color.RED;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Snake snake;
	
	private Point cherry;
	
	private Timer timer;
	
	public Board(Snake snake){
		
		spawnCherry();
		this.snake = snake;
		timer = new Timer(25, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		snake.move();
		if(snake.headAt(cherry.x, cherry.y)){
			snake.grow();
			spawnCherry();
		}

		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		fillBackground(g);
		drawSnake(g);
		drawCherry(g);
		
	}
	
	
	private void fillBackground(Graphics g){
		
		g.setColor(BOARD_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}
	
	
	private void drawSnake(Graphics g){
		
		g.setColor(SNAKE_COLOR);
		
		for(Point point : snake.getParts()){
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		
	}
	
	/**
	 * Spawns a Cherry inside the board at any random position, without drawing it.
	 */
	private void spawnCherry(){
		
		Random random = new Random();
		cherry = new Point(random.nextInt(79), random.nextInt(79));
		
	}
	
	/**
	 * Draws the existing cherry inside the board, at its position.
	 * @param g
	 */
	private void drawCherry(Graphics g){
		
		g.setColor(CHERRY_COLOR);
		g.fillRect(cherry.x * Snake.SCALE, cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
	}

}
