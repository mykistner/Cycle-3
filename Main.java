import javax.swing.JFrame;
public class Main {
	public Main(){
		JFrame frame = new JFrame();
		GameDisplay gd = new GameDisplay();
		frame.add(gd);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		}
	public static void main(String[] args){
		new Main();
	}
}
