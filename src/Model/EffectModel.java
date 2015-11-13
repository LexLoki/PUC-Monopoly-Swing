package Model;

public class EffectModel extends BoardUnit {
	
	private int value;
	
	public EffectModel(String name, int id, int value){
		super(name, id);
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
}
