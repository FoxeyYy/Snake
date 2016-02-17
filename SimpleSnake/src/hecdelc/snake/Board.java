package hecdelc.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Hector Del Campo Pando
 *
 */
public class Board extends JPanel implements ActionListener, KeyListener{
	
	private static final int TIMER_DELAY = 25;

	private static final String STR_GAME_OVER = "Game Over!";

	private final static Color SNAKE_COLOR = Color.BLUE;
	
	private final static Color BOARD_COLOR = Color.BLACK;
	
	private final static Color CHERRY_COLOR = Color.RED;

	private static final long serialVersionUID = 1L;

	private static final String STR_GAME_PAUSED = "Game Paused!";

	private static final Color STR_COLOR = Color.WHITE;
	
	private Snake snake;
	
	private Point cherry;
	
	private Timer timer;
	
	private boolean over;
	
	private boolean paused;
	
	private int time;

	private int score;

	
	public Board(){
		
		timer = new Timer(TIMER_DELAY, this);
		restartGame();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		snake.move();
		time++;
		
		if (hasSnakeCollided()){
			over = true;
			timer.stop();
		}
		else if(snake.headAt(cherry.x, cherry.y)){
			snake.grow();
			spawnCherry();
		}

		
		repaint();
	}
	
	private void drawCherry(Graphics g){
		
		g.setColor(CHERRY_COLOR);
		g.fillRect(cherry.x * Snake.SCALE, cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
	}

	private void drawSnake(Graphics g){
		
		g.setColor(SNAKE_COLOR);
		
		for(Point point : snake.getParts()){
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		
	}

	private void fillBackground(Graphics g){
		
		g.setColor(BOARD_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}

	private boolean hasSnakeCollided() {
		
		return isSnakeOutOfBorders() || isHeadOnTail();
		
	}
	
	private boolean isHeadOnTail() {
		
		final Point head = snake.getParts().get(0);
		
		for (Point part : snake.getParts()){
			if (snake.headAt(part.x, part.y) && part != head) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	private boolean isSnakeOutOfBorders() {
		
		return snake.getHeadX() > getWidth()/10 || 
				snake.getHeadY() > getHeight()/10 ||
				snake.getHeadX() < 0 ||
				snake.getHeadY() < 0;

	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		Direction snakeDirection = snake.getDirection();
		
		if (keyCode == KeyEvent.VK_A && snakeDirection != Direction.RIGHT){
			snakeDirection = Direction.LEFT;
		}
		else if (keyCode == KeyEvent.VK_D && snakeDirection != Direction.LEFT){
			snakeDirection = Direction.RIGHT;
		}
		else if (keyCode == KeyEvent.VK_W && snakeDirection != Direction.DOWN){
			snakeDirection = Direction.UP;
		}
		else if (keyCode == KeyEvent.VK_S && snakeDirection != Direction.UP){
			snakeDirection = Direction.DOWN;
		}
		
		snake.setDirection(snakeDirection);
		
		if (keyCode == KeyEvent.VK_SPACE && !over){
			paused = !paused;
			if (!paused){
				timer.start();
			}
		}
		else if (keyCode == KeyEvent.VK_SPACE && over){
				restartGame();
		}
				
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		fillBackground(g);
		drawSnake(g);
		drawCherry(g);
		
		g.setColor(STR_COLOR);
		
		String gameInfo = "Score: " + score +
				", Length: " + snake.getTailLength() +
				", Time: " + time / 20;
		
		g.drawString(gameInfo,
				getWidth() / 2 - gameInfo.length(),
				10);
		
		if (over){
			g.drawString(STR_GAME_OVER, 
					getWidth() / 2 - STR_GAME_OVER.length(),
					getHeight() / 2 - STR_GAME_OVER.length());
		}
		else if (paused){
			g.drawString(STR_GAME_PAUSED,
					getWidth() / 2 - STR_GAME_PAUSED.length(),
					getHeight() / 2 - STR_GAME_PAUSED.length());
			timer.stop();

		}
		
	}

	private void restartGame() {
		
		over = false;
		paused = false;
		time = 0;
		score = 0;
		this.snake = new Snake();
		spawnCherry();
		timer.restart();
		
	}

	private void spawnCherry(){
		
		score++;
		Random random = new Random();
		cherry = new Point(random.nextInt(79), random.nextInt(79));
		
	}

}
