package hecdelc.snake;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Snake implements KeyListener{
	
	public static final int SCALE = 10;
	
	private static final int INITIAL_X = 0;
	
	private static final int INITIAL_Y = -1;
	
	private int tailLength;
	
	private List<Point> parts;
	
	private Direction direction;
	
	public Snake(){
		
		tailLength = 14;
		direction = Direction.DOWN;
		
		int y = Snake.INITIAL_Y;
		parts = new ArrayList<Point>();
		for( int i = 0; i < tailLength; i++){
			parts.add(new Point(0, y));
			y--;
		}
		
		
	}

	public void move(){
		
		final Point head = parts.get(0);
				
		int headX = head.x,
			headY = head.y;
		
		parts.add(1, new Point(headX, headY));
		if( tailLength < parts.size()){
			parts.remove(parts.size() - 1);
		}
		
		switch(direction){
			case UP:
				headY--;
				break;
				
			case DOWN:
				headY++;
				break;
				
			case LEFT:
				headX--;
				break;
				
			case RIGHT:
				headX++;
				break;
		}
		
		head.setLocation(headX, headY);

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_A && direction != Direction.RIGHT){
			direction = Direction.LEFT;
		}
		else if (keyCode == KeyEvent.VK_D && direction != Direction.LEFT){
			direction = Direction.RIGHT;
		}
		else if (keyCode == KeyEvent.VK_W && direction != Direction.DOWN){
			direction = Direction.UP;
		}
		else if (keyCode == KeyEvent.VK_S && direction != Direction.UP){
			direction = Direction.DOWN;
		}
		else if (keyCode == KeyEvent.VK_SPACE){
			//TODO Pause and restart
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public List<Point> getParts() {
		return parts;
	}
	
	public void grow(){
		tailLength++;
	}

	public boolean headAt(int x, int y) {
		
		final int headX = parts.get(0).x,
				headY = parts.get(0).y;
		return headX == x && headY == y;
		
	}

}
