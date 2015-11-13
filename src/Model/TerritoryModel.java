package Model;

public class TerritoryModel extends PlaceModel {
	private final int priceOfHouse;
	private final int priceOfHotel;
	private final int[] taxes;

	//from 0 to 6
	private int numberOfHouses = 0;
	
	public TerritoryModel(String fileName, int id, String name, int buyValue, int priceOfHouse,
			int priceOfHotel, int[] taxes){
		super(fileName,id,name,buyValue);
		this.priceOfHouse = priceOfHouse;
		this.priceOfHotel = priceOfHotel;
		this.taxes = taxes;
	}
	
	public int getTax(){
		return taxes[numberOfHouses];
	}
	
	public int getNextInvestment(){
		return numberOfHouses < 4 ? priceOfHouse : numberOfHouses == 4 ? priceOfHotel : -1;
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
