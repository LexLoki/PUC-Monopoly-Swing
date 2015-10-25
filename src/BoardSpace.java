import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardSpace extends JPanel implements MouseListener {

	private Image img;
	
	public BoardSpace(String path){
		//Transparency
		setBackground(new Color(0,0,0,125));
		try{
			img = ImageIO.read(new File(path));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("you entered me");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("you exited me");
		// TODO Auto-generated method stub
	}
	
	
	
}
