import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.PlaceModel;

public class SpaceVisualizer extends JPanel {

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
				g.drawImage(((PlaceSpace)actualBoardSpace).getImage(), 0, 0, d.width, d.height, null);
			}
		}
	}
}
