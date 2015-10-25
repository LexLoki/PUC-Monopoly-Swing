import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
	
	private Board gameBoard;
	
	public GameFrame(int nPlayers){
		super("Game");
		this.setLayout(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Dimension size = GlobalData.getInstance().getScreenSize();
		Dimension size = GameFrame.getBestFrameFor(1000, 750);
		//this.setSize(size.height*4/5, size.width*4/5);
		this.setSize(size);
		int d = (int) (0.6*size.width);
		gameBoard = new Board(new Dimension(d,d));
		GameFrame.setPositionBottomLeft(gameBoard, 0, size.height);
		getContentPane().add(gameBoard);
		setVisible(true);
		System.out.println(getSize());
		System.out.println(gameBoard.getBounds());
	}
	
	static private void setPositionCenter(JPanel p, int x, int y){
		setPositionAnchor(p, 0.5, 0.5, x, y);
	}
	
	static private void setPositionBottomLeft(JPanel p, int x, int y){
		setPositionAnchor(p, 0., 1., x, y);
	}
	
	static private void setPositionAnchor(JPanel p, double ax, double ay, int x, int y){
		Dimension s = p.getSize();
		p.setLocation(x-(int)(ax*s.width), y-(int)(ay*s.height));
	}
	
	static private Dimension getBestFrameFor(Dimension d){
		return getBestFrameFor(d.width, d.height);
	}
	
	static private Dimension getBestFrameFor(int width, int height){
		Dimension size = GlobalData.getInstance().getScreenSize();
		size = new Dimension(size.width*9/10, size.height*9/10);
		float sx = (float)(size.width) / width;
		float sy = (float)(size.height) / height;
		float s = sx > sy ? sy : sx;
		return new Dimension((int)(s*width), (int)(s*height));
	}
}
