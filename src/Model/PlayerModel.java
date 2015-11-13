package Model;

import java.util.ArrayList;

public class PlayerModel {
	
	private int id;
	private int balance;
	private boolean isOnJail = false;
	private ArrayList<TerritoryModel> ownedTerritories = new ArrayList<TerritoryModel>();
	private ArrayList<CompanyModel> ownedCompanies = new ArrayList<CompanyModel>();
	
	
	public PlayerModel(int id, int balance){
		this.id = id;
		this.balance = balance;
	}
	
	public int getId(){
		return id;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void spendMoney(int value){
		balance -= value;
	}
	
	public void earnMoney(int value){
		balance += value;
	}
	public boolean canAcquire(int value){
		return balance >= value;
	}
//	public boolean canAcquire(PlaceModel p){
//		return balance >= p.getBuyValue();
//	}
	public void acquire(TerritoryModel p){
		if(!ownedTerritories.contains(p)){
			ownedTerritories.add(p);
		}
	}
	public void acquire(CompanyModel p){
		if(!ownedCompanies.contains(p)){
			ownedCompanies.add(p);
		}
	}
	public void acquire(PlaceModel p){
		if(p instanceof CompanyModel){
			acquire((CompanyModel)p);
		}
		else if(p instanceof TerritoryModel){
			acquire((TerritoryModel)p);
		}
		else{
			System.out.println("INVALID PLACE");
		}
		this.spendMoney(p.getBuyValue());
	}
}
