package PreGame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class MainMenuSelector extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path = "assets/frame.png";
	private Image bgImg;
	
	MainMenuSelector(Dimension size, ActionListener frame){
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
		JLabel jl = new JLabel("Players:", SwingConstants.CENTER);
		jl.setFont(new Font("Sansserif", Font.PLAIN, 24));
		
		int s = size.height/4;
		jl.setBounds(0,s/2,size.width,s/2);
		this.add(jl);
		Point pOff = new Point(size.width/10, size.height/10);
		int startY = jl.getHeight()+pOff.y;
		Dimension butSize = new Dimension(size.width/5, (size.height-s-pOff.y*3)/2);
		for(int i=0; i<3; i++){
			JButton jb = MainMenuSelector.createButton(pOff.x+i*(pOff.x+butSize.width), startY, butSize.width, butSize.height,i+2);
			jb.addActionListener(frame);
			this.add(jb);
		}
		startY = startY+butSize.height+pOff.y;
		int startX = size.width/2 - pOff.x/2 - butSize.width;
		for(int i=0; i<2; i++){
			JButton jb = MainMenuSelector.createButton(startX+i*(pOff.x+butSize.width), startY, butSize.width, butSize.height,i+5);
			jb.addActionListener(frame);
			this.add(jb);
		}
	}
	
	static private JButton createButton(int x, int y, int width, int height, int i){
		String str = Integer.toString(i);
		JButton jb = new JButton(str);
		jb.setName(str);
		jb.setFont(new Font("Sansserif", Font.PLAIN, 24));
		jb.setBounds(x, y, width, height);
		return jb;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
