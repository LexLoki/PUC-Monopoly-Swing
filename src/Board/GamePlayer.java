package Board;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import Model.PlayerModel;
import Model.TerritoryModel.TerritoryType;

public class GamePlayer extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerModel model;
	private final Color color;
	private BoardSpace space;
	private ArrayList<PlaceSpace> ownedSpaces = new ArrayList<PlaceSpace>();
	private int prisonTurns = 0;
	
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
	
	public boolean canBuy(int value){
		return model.canAcquire(value);
	}
	
	public void goToPrison(){
		prisonTurns = 0;
		model.goToPrison();
	}
	public void prisonDice(int v1, int v2){
		if(v1==v2)
			leavePrison();
		else if((++prisonTurns)==4){
			this.buy(50);
			leavePrison();
		}
	}
	public boolean isOnPrison(){
		return model.isOnPrison();
	}
	public void leavePrison(){
		model.leavePrison();
	}
	
	public void buy(PlaceSpace ps){
		model.acquire(ps.getModel());
		ownedSpaces.add(ps);
		ps.setOwner(this);
	}
	
	public void sell(PlaceSpace ps){
		ownedSpaces.remove(ps);
		ps.setOwner(null);
		this.earn(ps.getValue());
	}
	
	public PlayerModel getModel(){
		return model;
	}
	
	public void buy(int value){
		model.spendMoney(value);
	}
	
	public void earn(int value){
		model.earnMoney(value);
	}
	
	public int getBalance(){
		return model.getBalance();
	}
	
	public Boolean isBroke(){
		return model.getBalance()<0;
	}
	
	public void sellEverything(){
		while(!ownedSpaces.isEmpty()){
			PlaceSpace ps = ownedSpaces.remove(0);
			ps.setOwner(null);
		}
	}
	
	public PlaceSpace[] getOwnedSpaces(){
		return (PlaceSpace[])ownedSpaces.toArray(new PlaceSpace[0]);
	}
	
	public Boolean canBuyHouseFor(TerritorySpace t){
		int hq = t.getHouseQuant();
		if(hq>4 || getBalance()<t.getTax()) return false;
		int quant=0;
		int less = 10;
		TerritoryType typ = t.getType();
		for(PlaceSpace ps:ownedSpaces)
			if(ps instanceof TerritorySpace){
				TerritorySpace tt = (TerritorySpace)ps;
				if(tt.getType()==typ){
					quant++;
					int h = tt.getHouseQuant();
					if(h<less) less = h;
				}
			}
		return quant==3 && hq==less;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		//Dimension d = getSize();
		//g.drawOval(0, 0, d.width, d.height);
	}
	
}
