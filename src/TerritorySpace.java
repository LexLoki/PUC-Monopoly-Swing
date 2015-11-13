
import java.awt.Dimension;
import Model.TerritoryModel;

public class TerritorySpace extends PlaceSpace{
	
	private TerritoryModel model;
	
	public TerritorySpace(Dimension d, TerritoryModel model, int local){
		super(d, model, local);
		this.model = model;
	}
	
	public int getTax(){
		return model.getTax();
	}
}
