package Board;

import java.awt.Dimension;

import Model.EffectModel;

public final class EffectSpace extends BoardSpace{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EffectModel model;
	
	public EffectSpace(Dimension d, EffectModel model, int local){
		super(d, local);
		this.model = model;
	}
	
	public int getTax(){
		return model.getValue();
	}
}