package Model;

public class PlayerModel {
	
	private int id;
	private float balance;
	private boolean isOnJail = false;
	
	
	public PlayerModel(int id, float balance){
		this.id = id;
		this.balance = balance;
	}
	
	public int getId(){
		return id;
	}
	
	public float getBalance(){
		return balance;
	}
	
	public void spendMoney(float value){
		balance -= value;
	}
	
	public void earnMoney(float value){
		balance += value;
	}
	
}
