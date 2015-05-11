package better.main;

import javax.swing.JFrame;

public class Game {
	
	public static final String GAME_NAME = "Plataformas";
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame(GAME_NAME);
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		// Será del tamaño de sus componentes.
		window.pack();
		window.setVisible(true);
	}
}
