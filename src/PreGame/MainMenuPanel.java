package PreGame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MainMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path = "assets/mainMenu.jpg";
	private Image bgImg;
	
	MainMenuPanel(Dimension size){
		super();
		setSize(size);
		setLayout(null);
		setVisible(true);
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
		g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
