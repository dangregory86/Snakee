package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public JFrame jFrame;
	public RenderPanel renderPanel;
	public static Snake snake;
	public Timer timer = new Timer(20, this);

	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public boolean over = false, paused;

	public int ticks = 0, direction = DOWN, score, tailLength;

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 20;

	public Point head, cherry;

	public Random random;

	public Dimension dim;

	public Snake() {
		

		jFrame = new JFrame("snake");
		jFrame.setVisible(true);
		jFrame.setSize(600, 620);
		jFrame.addKeyListener(this);
		jFrame.setResizable(false);
		jFrame.add(renderPanel = new RenderPanel());
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startGame();

	}

	public void startGame() {

		over = false;
		paused = false;
		score = 0;
		tailLength = 0;
		ticks = 0;
		direction = DOWN;
		head = new Point(0, -1);
		random = new Random();		
		snakeParts.clear();
		cherry = new Point(random.nextInt(600 / SCALE), random.nextInt(560 / SCALE));
		
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();		
		ticks++;

		if (ticks % 2 == 0 && head != null && !paused && !over) {
			
			snakeParts.add(new Point(head.x, head.y));
			
			
			if (direction == UP){ 
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else if (!noTailAt(head.x, head.y - 1)){
					over = true;
				} else {
					paused = true;
				}
			}
			if (direction == DOWN) {
				if (head.y + 1 < 595 / SCALE && noTailAt(head.x, head.y + 1)) {
					
					head = new Point(head.x, head.y + 1);
					
				} else if (!noTailAt(head.x, head.y + 1)){
					over = true;
				} else {
					paused = true;
				}
			}
			if (direction == LEFT) {
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else if (!noTailAt(head.x - 1, head.y)){
					over = true;
				} else {
					paused = true;
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 < 595 / SCALE && noTailAt(head.x + 1, head.y)){
					head = new Point(head.x + 1, head.y);
				} else if (!noTailAt(head.x + 1, head.y)){
					over = true;
				} else {
					paused = true;
				}
			}
		
//			 code to remove end of tail
			if(snakeParts.size() > tailLength){
				snakeParts.remove(0);
			}
			 

			if (cherry != null) {
				if (head.x == cherry.x && head.y == cherry.y) {
					score += 10;
					tailLength++;
					cherry.setLocation(new Point(random.nextInt(595 / SCALE), random.nextInt(595 / SCALE)));
					System.out.println(score + " " + tailLength);
				} 
			}}
		}

	public static void main(String[] args) {

		snake = new Snake();

	}
	
	public boolean noTailAt(int x, int y){
		for (Point point : snakeParts){
			if(point.equals(new Point(x, y))){
				return false;
			}			
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_UP && direction != DOWN){
			direction = UP;
			paused = false;}
		if (i == KeyEvent.VK_DOWN && direction != UP){
			direction = DOWN;
			paused = false;}
		if (i == KeyEvent.VK_LEFT && direction != RIGHT){
			direction = LEFT;
			paused = false;}
		if (i == KeyEvent.VK_RIGHT && direction != LEFT){
			direction = RIGHT;
			paused = false;}
		if (i == KeyEvent.VK_SPACE){
			
			if(over){startGame();
		} else {
			paused = !paused;
		}}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
