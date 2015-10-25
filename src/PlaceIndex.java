public enum PlaceIndex{
	Bottom(3),
	Up(1),
	Left(0),
	Right(2);
	
	private final int id;
	PlaceIndex(int id){this.id=id;}
	public int getValue(){return id;}
}
