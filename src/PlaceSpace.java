
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.PlaceModel;
import Model.TerritoryModel;

public class PlaceSpace extends BoardSpace implements MouseListener {

	private Image img;
	private GamePlayer owner = null;
	private Point rectLoc;
	private PlaceModel m;
	
	public PlaceSpace(Dimension d, PlaceModel model, int local){
		super(d, local);
		m = model;
		try{
			System.out.println(model.getFileName());
			img = ImageIO.read(new File(model.getFileName()));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		this.addMouseListener(this);
		if(local%2 == 0){
			rectLoc = new Point(d.height/2, d.width*3/4);
		}
		else{
			rectLoc = new Point(d.width/2, d.height*3/4);
		}
	}
	
	public Image getImage(){
		return img;
	}
	
	public boolean canBuy(){
		return m.getOwner()==null;
	}
	
	public int getValue(){
		return m.getBuyValue();
	}
	
	public PlaceModel getModel(){
		return m;
	}
	
	public void setOwner(GamePlayer p){
		owner = p;
		m.setOwner(p.getModel());
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(owner != null){
			g.setColor(owner.getColor());
			//g.fillRect(rectLoc.x-5, rectLoc.y-5, 10, 10);
			Dimension d = this.getSize();
			((Graphics2D)g).setStroke(new BasicStroke(2));
			g.drawRect(d.width/20, d.height/20, d.width*18/20, d.height*18/20);
		}
		else{
			//g.setColor(new Color(30,30,30));
			//g.fillRect(rectLoc.x-5, rectLoc.y-5, 10, 10);
		}
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
		StateMachine.mouseOver(this);
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
