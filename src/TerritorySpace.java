
import java.awt.Dimension;
import Model.TerritoryModel;

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
	
	public int getTax(){
		return model.getTax();
	}
}
