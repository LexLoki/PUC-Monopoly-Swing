import javax.imageio.ImageIO;
import Model.PlaceModel;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JPanel {

	private Image bgImg;
	private final String path = "assets/tabuleiroRJ.jpg";
	private ArrayList<BoardSpace> boardSpaces = new ArrayList<BoardSpace>();
	
	final private int LEFT = 0;
	final private int UP = 1;
	final private int RIGHT = 2;
	final private int DOWN = 3;
	
	public Board(Dimension size){
		super();
		
		this.setSize(size);
		try{
			bgImg = ImageIO.read(new File(path));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		int i;
		int local = 0;
		int height = size.width * 136/1000;
		int width = height * 280 / 350;
		int dx = width, dy = height;
		PlaceModel m = new PlaceModel(0,"teste");
		File[] fs = GlobalData.getInstance().getSpaceFiles();
		BoardSpace space;
		for(i=0; i<6; i++){
			space = new BoardSpace(new Dimension(dx,dy),fs[i],m,0);
			space.setLocation(0, size.height-dy-dx*(i+1));
			this.add(space);
			boardSpaces.add(space);
		}
		for(i=0; i<6; i++){
			space = new BoardSpace(new Dimension(dx,dy),fs[i+6],m,1);
			space.setLocation(dy+dx*i, 0);
			this.add(space);
			boardSpaces.add(space);
		}
		for(i=0; i<6; i++){
			space = new BoardSpace(new Dimension(dx,dy),fs[i+12],m,2);
			space.setLocation(size.width-dy, dy+dx*i);
			this.add(space);
			boardSpaces.add(space);
		}
		for(i=0; i<6; i++){
			space = new BoardSpace(new Dimension(dx,dy),fs[i+18],m,3);
			space.setLocation(size.width-dy-dx*(i+1), size.height-dy);
			this.add(space);
			boardSpaces.add(space);
		}
//		for(i=0; i<36; i++){
//			//FIRST WITH D X D
//			//empty for now
//			for(int j=i++ + 9; i<j; i++){
//				//THEN D X DPLUS
//			}
//			int aux = dx; dx = dy; dy = aux;
//			local++;
//		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	
}
