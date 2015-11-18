
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BoardSpace extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int local;
	
	final static private int LEFT = 0;
	final static private int UP = 1;
	final static private int RIGHT = 2;
	final static private int DOWN = 3;
	
	private Point[] places;
	private ArrayList<GamePlayer> players;
	
	public BoardSpace(Dimension d, int local){
		//Transparency
		this.local = local;
		if(local == DOWN || local == UP){
			System.out.println("normal");
			this.setSize(d);
		}
		else{
			System.out.println("inverted");
			this.setSize(d.height,d.width);
		}
		setBackground(new Color(0,0,0,0));
		this.setOpaque(false);
		
		float vx = (float)d.width/3;
		float vy = d.height*0.25f;
		int nLin = 2, nCol = 3;
		float px, py = vy*0.5f;
		this.places = new Point[6];
		for(int i=0; i<nLin; i++, py+=vy){
			px = vx*0.5f;
			for(int j=0; j<nCol; j++, px+=vx)
				places[i*nCol+j] = new Point((int)px,(int)py);
		}
		players = new ArrayList<GamePlayer>();
		
	}
	
	public void setPlayer(GamePlayer p){
		if(!players.contains(p)){
			players.add(p);
		}
	}
	
	public void removePlayer(GamePlayer p){
		players.remove(p);
	}

	public void paintComponent(Graphics g){

		super.paintComponent(g);
		int r = 5;
		for (GamePlayer player : players){
			g.setColor(player.getColor());
			Point p = places[player.getId()];
			g.fillOval(p.x-r, p.y-r, r*2, r*2);
		}
		
//		Graphics2D g2d = (Graphics2D)g;
//	     AffineTransform origXform = g2d.getTransform();
//	     AffineTransform newXform = (AffineTransform)(origXform.clone());
//	     //center of rotation is center of the panel
//	     int xRot = this.getWidth()/2;
//	     int yRot = this.getHeight()/2;
//	     newXform.rotate(Math.toRadians(90*placeIndex.getValue()), xRot, yRot);
//	     g2d.setTransform(newXform);
//	     //draw image centered in panel
//	     int x = (getWidth() - img.getWidth(this))/2;
//	     int y = (getHeight() - img.getHeight(this))/2;
//	     g2d.drawImage(img, x, y, this);
//	     g2d.setTransform(origXform);
	}
}
