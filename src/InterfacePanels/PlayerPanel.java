package InterfacePanels;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Board.GamePlayer;
import Board.PlaceSpace;
import Controller.StateMachine;

public class PlayerPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private BoardSpace actualBoardSpace;
	private final int playerIndex;
	private PlaceSpace[] ownedSpaces;
	private int imgSize;
	
	public PlayerPanel(Dimension d, int playerIndex){
		this.setSize(d);
		this.setLayout(null);
		this.playerIndex = playerIndex;
		this.addMouseListener(this);
	}
	
	private void updateSpaces(){
		GamePlayer p = StateMachine.getPlayer(playerIndex);
		PlaceSpace[] spaces = p.getOwnedSpaces();
		ownedSpaces = spaces;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(Color.black);
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
		//g.drawImage(((PlaceSpace)actualBoardSpace).getImage(), 0, 0, d.width, d.height, null);
		updateSpaces();
		int ew = getWidth()/20;
		if(ownedSpaces.length > 0){
			Image img = ownedSpaces[0].getImage();
			int h = getHeight();
			int w = img.getWidth(null) * h / img.getHeight(null);
			imgSize = w;
			g.setColor(StateMachine.getPlayer(playerIndex).getColor());
			g.fillRect(0, 0, ew, h);
			for(int i=0; i<ownedSpaces.length; i++){
				img = ownedSpaces[i].getImage();
				g.drawImage(img, (i+1)*ew, 0, w, h, null);
			}
		}
		else if(StateMachine.getPlayer(playerIndex).isBroke())
			g.fillRect(ew, 0, getWidth()-ew, getHeight());
	}
	
	private PlaceSpace getClickedSpace(Point p){
		int x = p.x;
		int ew = getWidth()/20;
		int quant = ownedSpaces.length;
		if(x<ew || quant==0 || x>(ew*(quant)+imgSize))
			return null;
		x -= ew;
		if(x>(ew*quant))
			x = ew*quant - ew/2;
		System.out.println("clicou "+x/ew);
		return ownedSpaces[x/ew];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		PlaceSpace p = getClickedSpace(e.getPoint());
		
		if(p!=null)
			StateMachine.mouseOverOwned(p, playerIndex);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
