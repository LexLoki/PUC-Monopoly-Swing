
import java.awt.Dimension;
import Model.SorteRevesModel;

public class SorteRevesSpace extends BoardSpace {
	
	private final SorteRevesModel model;
	
	public SorteRevesSpace(Dimension d, SorteRevesModel model, int local){
		super(d, local);
		this.model = model;
	}
	
}