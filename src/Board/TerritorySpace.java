package Board;

import java.awt.Dimension;
import Model.TerritoryModel;
import Model.TerritoryModel.TerritoryType;

public class TerritorySpace extends PlaceSpace{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TerritoryModel model;
	
	public TerritorySpace(Dimension d, TerritoryModel model, int local){
		super(d, model, local);
		this.model = model;
	}
	
	public TerritoryType getType(){
		return model.type;
	}
	
	public int getHouseQuant(){
		return model.getHousesQuant();
	}
	
	public boolean putHouse(){
		return model.putHouse()<5;
	}
	
	public int getTax(){
		return model.getTax();
	}
	
	public int getNextInvestment(){
		return model.getNextInvestment();
	}
	
	public int sellHouse(){
		return model.sellHouse();
	}
}
