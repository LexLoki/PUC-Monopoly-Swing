import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel {

	//private Image bgImg = new ImageIcon("tabuleiroRJ.jpg").getImage();
	private Image bgImg;
	private final String path = "assets/tabuleiroRJ.jpg";
	
	public Board(Dimension size){
		super();
		System.out.println(size);
		
		//Dimension sSize = GlobalData.getInstance().getScreenSize();
		this.setSize(size);
		//this.setBounds((sSize.width-size.width)/2, (sSize.height-size.height)/2, size.width, size.height);
		try{
			bgImg = ImageIO.read(new File(path));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//int hue = this.getWidth();
		g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
		//g.drawImage(bgImg, 0, 0, this);
		//JLabel l = new JLabel("bla");
		//l.setIcon(icon);
		//g.drawLine(0, 0, 200, 200);
	}
	
	
}
