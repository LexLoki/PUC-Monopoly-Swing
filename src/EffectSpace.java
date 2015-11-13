
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.EffectModel;
import Model.PlaceModel;

public class EffectSpace extends BoardSpace{
	
	private final EffectModel model;
	
	public EffectSpace(Dimension d, EffectModel model, int local){
		super(d, local);
		this.model = model;
	}
	
	public int getTax(){
		return model.getValue();
	}
}