
import java.awt.Dimension;
import Model.CompanyModel;

public class CompanySpace extends PlaceSpace{
	
	private CompanyModel model;
	
	public CompanySpace(Dimension d, CompanyModel model, int local){
		super(d, model, local);
		this.model = model;
	}
	
	public int getTax(int dice){
		return dice*model.getTax();
	}
}
