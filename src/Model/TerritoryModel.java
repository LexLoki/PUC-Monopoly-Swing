package Model;


public class TerritoryModel extends PlaceModel {
	
	public enum TerritoryType{
		orange,red,yellow,purple,blue,green;
	}
	
	private final int priceOfHouse;
	private final int priceOfHotel;
	private final int[] taxes;
	public final TerritoryType type;
	
	//from 0 to 6
	private int numberOfHouses = 0;
	
	public TerritoryModel(String fileName, int id, String name, int buyValue, int priceOfHouse,
			int priceOfHotel, int[] taxes, TerritoryType type){
		super(fileName,id,name,buyValue);
		this.priceOfHouse = priceOfHouse;
		this.priceOfHotel = priceOfHotel;
		this.taxes = taxes;
		this.type = type;
	}
	
	public int getTax(){
		return taxes[numberOfHouses];
	}
	
	public int putHouse(){
		return(++numberOfHouses);
	}
	
	public int sellHouse(){
		numberOfHouses--;
		return getNextInvestment()/2;
	}
	
	public int getNextInvestment(){
		return numberOfHouses < 4 ? priceOfHouse : numberOfHouses == 4 ? priceOfHotel : -1;
	}
	
	public int getHousesQuant(){
		return numberOfHouses;
	}
	
	public boolean doInvestment(PlayerModel p){
		int v = getNextInvestment();
		if(v != -1 && p.getBalance() >= v){
			numberOfHouses++;
			p.spendMoney(v);
			return true;
		}
		return false;
	}
}
