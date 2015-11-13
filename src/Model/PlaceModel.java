package Model;

public abstract class PlaceModel extends BoardUnit {
	
	private final String fileName;
	private final int buyValue;
	
	private PlayerModel owner = null;
	
	protected PlaceModel(String fileName, int id, String name, int buyValue){
		super(name,id);
		this.fileName = fileName;
		this.buyValue = buyValue;
	}
	public final String getFileName(){
		return fileName;
	}
	public final int getBuyValue(){
		return buyValue;
	}
	
	public final void setOwner(PlayerModel player){
		owner = player;
	}
	
	public abstract int getTax();
	
	public PlayerModel getOwner(){
		return owner;
	}
	
}
