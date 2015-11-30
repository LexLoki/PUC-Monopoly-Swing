package Board;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.StateMachine;
import Model.PlaceModel;

public class PlaceSpace extends BoardSpace implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//HOW COULD I PUT FINAL IN PRIVATE FINAL IMAGE IMG
	private Image img;
	private GamePlayer owner = null;
	private final Rectangle rectMark;
	private final PlaceModel m;
	
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
		Dimension dd = this.getSize();
		this.rectMark = new Rectangle(dd.width/20, dd.height/20, dd.width*18/20, dd.height*18/20);
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
	
	public GamePlayer getOwner(){
		return owner;
	}
	
	public void setOwner(GamePlayer p){
		owner = p;
		if(p != null)
			m.setOwner(p.getModel());
		else
			m.setOwner(null);
		this.repaint();
	}
	
	public int getTax(){
		return 100000;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(owner != null){
			g.setColor(owner.getColor());
			((Graphics2D)g).setStroke(new BasicStroke(2));
			g.drawRect(rectMark.x, rectMark.y, rectMark.width, rectMark.height);
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
