package InterfacePanels;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayersInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private BoardSpace actualBoardSpace;
	private PlayerPanel[] pp;
	
	public PlayersInfoPanel(Dimension d, int nPlayers){
		this.setSize(d);
		this.setLayout(null);
		
		Dimension labelSize = new Dimension(d.width, d.height/10);
		JLabel label = new JLabel("Players information",SwingConstants.CENTER);
		label.setBackground(Color.blue);
		label.setSize(labelSize);
		label.setFont(new Font("Sansserif", Font.PLAIN, 24));
		this.add(label);
		
		Dimension playersSize = new Dimension(d.width, d.height - labelSize.height);
		Dimension pSize = new Dimension(playersSize.width, playersSize.height/Math.max(nPlayers,4));
		ArrayList<PlayerPanel> pp = new ArrayList<PlayerPanel>();
		for(int i=0; i<nPlayers; i++){
			PlayerPanel p = new PlayerPanel(pSize,i);
			p.setLocation(new Point(0, labelSize.height+i*pSize.height));
			this.add(p);
			pp.add(p);
		}
		this.pp = (PlayerPanel[])pp.toArray(new PlayerPanel[0]);
	}
	
	public void updatePlayer(int index){
		pp[index].repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
		
	}
}
