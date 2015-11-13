package Model;

public class CompanyModel extends PlaceModel {
	
	private final int multiplier;
	private final int hipoteca;
	
	public CompanyModel(String fileName, int id, String name, int buyValue, int multiplier, int hipoteca){
		super(fileName,id,name,buyValue);
		this.hipoteca = hipoteca;
		this.multiplier = multiplier;
	}
	
	public int getTax(){
		return multiplier;
	}

}
