import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
	
	public GameFrame(){
		super("Game");
		setSize(200, 200);
		getContentPane().add(new Board(getSize()));
		setVisible(true);
	}
	
}
