import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class windows extends Canvas{

	private static final long serialVersionUID = -4983751774259039042L;

	public windows(int width, int height, String title, game g) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.add(g);
		frame.setVisible(true);

	}
}
