import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.PlaceModel;
import Model.PlayerModel;

public class GamePlayer extends JPanel{
	
	private PlayerModel model;
	private final Color color;
	private BoardSpace space;
	private ArrayList<PlaceSpace> ownedSpaces = new ArrayList<PlaceSpace>();
	
	
	public GamePlayer(PlayerModel model, Color c){
		this.model = model;
		color = c;
	}
	
	public int getId(){
		return model.getId();
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setSpace(BoardSpace space){
		this.space = space;
	}
	
	public BoardSpace getSpace(){
		return space;
	}
	
	public boolean canBuy(PlaceSpace ps){
		return model.canAcquire(ps.getValue());
	}
	
	public void buy(PlaceSpace ps){
		model.acquire(ps.getModel());
		ps.setOwner(this);
	}
	
	public PlayerModel getModel(){
		return model;
	}
	
	public void buy(int value){
		model.spendMoney(value);
	}
	
	public int getBalance(){
		return model.getBalance();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		//Dimension d = getSize();
		//g.drawOval(0, 0, d.width, d.height);
	}
	
}
