import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

class MainMenuSelector extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path = "assets/frame.png";
	private Image bgImg;
	
	MainMenuSelector(Dimension size){
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
	
	static private JButton createButton(int x, int y, int width, int height){
		JButton jb = new JButton("Roll");
		jb.setFont(new Font("Sansserif", Font.PLAIN, 24));
		jb.setBounds(x, y, width, height);
		return jb;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
