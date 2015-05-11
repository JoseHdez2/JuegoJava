package better.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class TestPanel extends JPanel{
	EntityVisible se;
	
	public TestPanel(){
		se = new EntityVisible(new Rectangle(100,100,100,100));
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		se.draw(g);
	}
}
