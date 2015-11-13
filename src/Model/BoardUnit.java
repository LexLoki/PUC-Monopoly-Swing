package Model;

public class BoardUnit {

	private final String name;
	private final int id;
	
	public BoardUnit(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public void print(){
		System.out.println("Name: " + name + "\nId: " + id);
	}
	
	public int getId(){
		return id;
	}
}
