package InterfacePanels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

import Board.BoardSpace;
import Board.PlaceSpace;
import Board.SorteRevesSpace;
import Board.TerritorySpace;

public class SpaceVisualizer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoardSpace actualBoardSpace;
	
	public SpaceVisualizer(Dimension d){
		this.setSize(d);
		//this.setBackground(Color.black);
		this.setOpaque(false);
		this.setLayout(null);
	}
	
	public void setBoardSpace(BoardSpace space){
		actualBoardSpace = space;
		this.repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(actualBoardSpace != null){
			if(actualBoardSpace instanceof PlaceSpace){
				Dimension d = getSize();
				PlaceSpace bSpace = (PlaceSpace)actualBoardSpace;
				g.drawImage((bSpace).getImage(), 0, 0, d.width, d.height, null);
				if(bSpace instanceof TerritorySpace){
					TerritorySpace tSpace = (TerritorySpace)bSpace;
					int ss = d.width/5;
					int quant = tSpace.getHouseQuant();
					for(int i=0;i<quant;i++)
						this.drawHouse(g, i*(ss+2), 0, ss, ss);
				}
			}
			else if(actualBoardSpace instanceof SorteRevesSpace){
				
			}
		}
	}
	
	private void drawHouse(Graphics g, int x, int y, int width, int height){
		Path2D.Double path = new Path2D.Double();
		int p1 = height*2/5+y;
		int p2 = width/2+x;
		int p3 = x+width;
		int p4 = y+height;
		path.moveTo(x, p1);
		path.lineTo(p2, y);
		path.lineTo(p3, p1);
		path.lineTo(p3, p4);
		path.lineTo(x, p4);
		path.closePath();
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.red);
		g2.fill(path);
	}
}
