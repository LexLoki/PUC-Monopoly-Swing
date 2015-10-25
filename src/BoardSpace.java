import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.PlaceModel;

public class BoardSpace extends JPanel implements MouseListener {

	private Image img;
	private PlaceModel model;
	private int local;
	
	final static private int LEFT = 0;
	final static private int UP = 1;
	final static private int RIGHT = 2;
	final static private int DOWN = 3;
	
	public BoardSpace(Dimension d, File file, PlaceModel model, int local){
		//Transparency
		this.local = local;
		try{
			img = ImageIO.read(file);
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		if(local == DOWN || local == UP){
			System.out.println("normal");
			this.setSize(d);
		}
		else{
			System.out.println("inverted");
			this.setSize(d.height,d.width);
		}
		this.model = model;
		setBackground(new Color(0,0,0,125));
		System.out.println(getSize());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
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
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("you entered me");
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("you exited me");
		// TODO Auto-generated method stub
	}
	
	
}
