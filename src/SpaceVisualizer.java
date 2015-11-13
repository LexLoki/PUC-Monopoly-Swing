import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SpaceVisualizer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoardSpace actualBoardSpace;
	
	public SpaceVisualizer(Dimension d){
		this.setSize(d);
		//this.setBackground(Color.black);
		this.setOpaque(false);
		this.setLayout(null);
	}
	
	public void setBoardSpace(BoardSpace space){
		actualBoardSpace = space;
		this.repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(actualBoardSpace != null){
			if(actualBoardSpace instanceof PlaceSpace){
				Dimension d = getSize();
				g.drawImage(((PlaceSpace)actualBoardSpace).getImage(), 0, 0, d.width, d.height, null);
			}
		}
	}
}
