package InterfacePanels;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.w3c.dom.css.Rect;

import Board.GamePlayer;
import Model.PlayerModel;

public class StatusPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JLabel[] labels;
	private final Rectangle[] rects;
	private final Color[] colors;
	
	private int turnPlayer = 0;
	
	public StatusPanel(Dimension d, GamePlayer[] gps){
		this.setSize(d);
		this.setLayout(null);
		this.setBackground(Color.white);
		
		Dimension sectionSize = new Dimension(d.width/3, d.height/2);
		Dimension colorSize = new Dimension(sectionSize.height, sectionSize.height);
		Dimension labelSize = new Dimension(sectionSize.width-colorSize.width, sectionSize.height);
		ArrayList<JLabel> labelsList  = new ArrayList<JLabel>();
		ArrayList<Rectangle> rectsList  = new ArrayList<Rectangle>();
		ArrayList<Color> colorsList  = new ArrayList<Color>();
		for(int i=0; i<gps.length; i++){
			GamePlayer gp = gps[i];
			JLabel label = new JLabel(Integer.toString(gp.getBalance()), SwingConstants.CENTER);
			Point pp = new Point(i/2*sectionSize.width, (i%2)*sectionSize.height);
			label.setBounds(pp.x+colorSize.width, pp.y, labelSize.width, labelSize.height);
			rectsList.add(new Rectangle(pp.x,pp.y,colorSize.width,colorSize.height));
			labelsList.add(label);
			colorsList.add(gp.getColor());
			this.add(label);
			gp.addObserver(this);
		}
		labels = (JLabel[])labelsList.toArray(new JLabel[0]);
		rects = (Rectangle[])rectsList.toArray(new Rectangle[0]);
		colors = (Color[])colorsList.toArray(new Color[0]);
	}
	
	public void setTurn(int index){
		turnPlayer = index;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
		for(int i=0; i<labels.length; i++){
			Rectangle r = rects[i];
			g.setColor(colors[i]);
			g.fillRect(r.x, r.y, r.width, r.height);
			g.drawRect(r.x, r.y, labels[i].getWidth()+r.width, r.height);
		}
		g.setColor(Color.white);
		Rectangle r = rects[turnPlayer];
		Dimension radius = new Dimension(r.width/2,r.height/2);
		g.fillOval(r.x+(r.width-radius.width)/2, 
				r.y+(r.height-radius.height)/2, 
				radius.width, radius.height);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("NOTIFICATION");
		PlayerModel pm = (PlayerModel)o;
		labels[pm.getId()].setText(Integer.toString(pm.getBalance()));
		this.repaint();
	}
}
