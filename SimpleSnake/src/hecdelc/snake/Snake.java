package hecdelc.snake;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Snake implements KeyListener{
	
	public static final int SCALE = 10;
	
	private List<Point> parts;
	
	private Direction direction;
	
	public Snake(){
		
		parts = new ArrayList<Point>();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		switch(keyCode){
			case KeyEvent.VK_A:
				direction = Direction.LEFT;
				break;
			case KeyEvent.VK_D:
				direction = Direction.RIGHT;
				break;
			case KeyEvent.VK_W:
				direction = Direction.UP;
				break;
			case KeyEvent.VK_S:
				direction = Direction.DOWN;
				break;
			case KeyEvent.VK_SPACE:
				//TODO Pause and restart
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public List<Point> getParts() {
		return parts;
	}

}
