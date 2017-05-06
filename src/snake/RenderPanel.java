package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

	public static int curColor = 0;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.RED);
		int b = 1;
		Snake snake = Snake.snake;
		for (Point point : snake.snakeParts) {
			b += 15;
			g.setColor(new Color(b));
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
			
		}
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.BLACK);
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
	}

}
