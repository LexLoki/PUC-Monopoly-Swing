import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import Model.PlayerModel;

public class GamePlayer extends JPanel{
	
	private PlayerModel model;
	private final Color color;
	
	public GamePlayer(PlayerModel model, Color c){
		this.model = model;
		color = c;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		Dimension d = getSize();
		g.drawOval(0, 0, d.width, d.height);
	}
	
}
