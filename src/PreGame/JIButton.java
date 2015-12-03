package PreGame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JIButton extends JButton {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path;
	private Image bgImg;

	JIButton(String filename){
		path = filename;
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
