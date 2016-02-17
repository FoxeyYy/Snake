package hecdelc.snake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hector Del Campo Pando
 *
 */
public class Snake{
	
	public static final int SCALE = 10;
	
	private static final int INITIAL_X = 0;
	
	private static final int INITIAL_Y = 0;
	
	private int tailLength;
	
	private List<Point> parts;
	
	private Direction direction;
	
	public Snake(){
		
		tailLength = 14;
		direction = Direction.DOWN;
		
		int y = Snake.INITIAL_Y;
		parts = new ArrayList<Point>();
		for( int i = 0; i < tailLength; i++){
			parts.add(new Point(INITIAL_X, y));
			y--;
		}
		
		
	}

	public Direction getDirection() {
		return direction;
	}


	public int getHeadX(){
		return parts.get(0).x;
	}

	public int getHeadY(){
		return parts.get(0).y;
	}

	public List<Point> getParts() {
		return parts;
	}

	public int getTailLength() {
		return tailLength;
	}
	
	public void grow(){
		tailLength++;
	}

	public boolean headAt(int x, int y) {
		
		final int headX = parts.get(0).x,
				headY = parts.get(0).y;
		return headX == x && headY == y;
		
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
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
