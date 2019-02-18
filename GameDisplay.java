import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
public class GameDisplay extends JPanel implements Runnable, KeyListener{

	final long serialVersionUID = 1l;
	final int WIDTH = 500, HEIGHT = 500;
	private Thread t;
	private boolean running;
	private boolean left=false, right=true, up=false, down=false;
	private Body b;
	private ArrayList<Body> snake;
	private Food food;
	private ArrayList<Food> foods;
	private Random r;
	private int x = 10, y = 10, size = 5;
	int ticks = 0;

	public GameDisplay(){
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		snake = new ArrayList<Body>();
		foods = new ArrayList<Food>();
		r = new Random();
		start();
	}

	public void start(){
		running = true;
		t= new Thread(this);
		t.start();
	}
	public void stop(){
		running = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i = 0; i<WIDTH/10;i++){
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i = 0; i<WIDTH/10;i++){
			g.drawLine(0, i*10, HEIGHT, i*10);
		}
		for(int i = 0; i<snake.size();i++){
			snake.get(i).draw(g);
		}
		for(int i = 0; i < foods.size(); i++){
			foods.get(i).draw(g);
		}
	}

	public void tick(){
		if(snake.size()==0){
			b = new Body(x,y,10);
			snake.add(b);
		}
		ticks++;
		if(ticks>250000){
			if(right) x++;
			if(left) x--;
			if(up) y--;
			if(down) y++;

			ticks=0;
			b=new Body(x,y,10);
			snake.add(b);
			if(snake.size() > size){
				snake.remove(0);
			}
		}
		if(foods.size() == 0){
			int x = r.nextInt(49);
			int y = r.nextInt(49);
			food = new Food(x,y,10);
			foods.add(food);
		}
		for(int i = 0; i<foods.size();i++){
			if(x == foods.get(i).getX() && y == foods.get(i).getY()){
				size++;
				foods.remove(i);
				i++;
			}
		}
		//if you go off the map game over
		if(x < 0 || x > 49 || y > 49 || y < 0){
			System.out.println("Game Over");
			stop();
		}
		//test for collision
		for(int i = 0; i < snake.size(); i++){
			if( x == snake.get(i).getX() && y == snake.get(i).getY()){
				if(i != snake.size()-1){
					System.out.println("Game Over");
					stop();
				}
			}
		}
	}

	@Override
	public void run() {
		while(running){
			tick();
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left){
			right=true;
			up=false;
			down=false;
		}
		if(key == KeyEvent.VK_LEFT && !right){
			left=true;
			up=false;
			down=false;
		}
		if(key == KeyEvent.VK_UP && !down){
			up=true;
			right=false;
			left=false;
		}
		if(key == KeyEvent.VK_DOWN && !up){
			down=true;
			right=false;
			left=false;
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
}
