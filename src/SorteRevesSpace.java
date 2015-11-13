
import java.awt.Dimension;
import Model.SorteRevesModel;

public class SorteRevesSpace extends BoardSpace {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SorteRevesModel model;
	
	public SorteRevesSpace(Dimension d, SorteRevesModel model, int local){
		super(d, local);
		this.model = model;
	}
	
}