import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
	
	private Board gameBoard;
	private SpaceVisualizer spaceVisualizer;
	private Dimension insideSize;
	private ActionPanel actionPanel;
	
	
	public GameFrame(int nPlayers){
		super("Game");
		this.setLayout(null);
		Dimension size = GameFrame.getBestFrameFor(1000, 750);
		setSize(size);
		setVisible(true);
		int marginTop = getInsets().top;
		Dimension wholeSize = new Dimension(size.width,size.height+marginTop);
		this.setSize(wholeSize);
		int d = (int) (0.65*size.width);
		gameBoard = new Board(new Dimension(d,d), nPlayers);
		GameFrame.setPositionBottomLeft(gameBoard, 0, size.height);
		getContentPane().add(gameBoard);
		//this.repaint();
		insideSize = size;
		//(0.225x, 0.28125x)
		Dimension vD = new Dimension((int)(0.225*size.width),(int)(0.28125*size.width));
		spaceVisualizer = new SpaceVisualizer(vD);
		GameFrame.setPositionBottomLeft(spaceVisualizer, d, size.height);
		getContentPane().add(spaceVisualizer);
		//(0.125x, 0.28125x)
		Dimension vA = new Dimension((int)(0.125*size.width),vD.height);
		actionPanel = new ActionPanel(vA);
		GameFrame.setPositionBottomLeft(actionPanel, d+vD.width, size.height);
		getContentPane().add(actionPanel);
		
		StateMachine.startGame(nPlayers, gameBoard, spaceVisualizer, actionPanel);
		
		repaint();
		this.setLayout(null);
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
		size = new Dimension(size.width*9/10, size.height*85/100);
		float sx = (float)(size.width) / width;
		float sy = (float)(size.height) / height;
		float s = sx > sy ? sy : sx;
		return new Dimension((int)(s*width), (int)(s*height));
	}
}
