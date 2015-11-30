import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public final class MainMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainMenuPanel myPanel;
	private MainMenu me;
	
	public MainMenu(){
		super("Game");
		me = this;
		this.setLayout(null);
		Dimension size = ResizeUtils.getBestFrameFor(1024, 768);
		setSize(size);
		setVisible(true);
		int marginTop = getInsets().top;
		Dimension wholeSize = new Dimension(size.width,size.height+marginTop);
		this.setSize(wholeSize);
		
		myPanel = new MainMenuPanel(size);
		this.getContentPane().add(myPanel);
		
		//newGame (0.37695, 0.46875) (0.25488, 0.08203)
		JButton newGameButton = new JButton();
		newGameButton.setBounds((int)(0.37695*size.width), (int)(0.46875*size.height), 
				(int)(0.25488*size.width), (int)(0.08203*size.height));
		newGameButton.addActionListener(new GameListener());
		this.getContentPane().add(newGameButton);
		
		repaint();
		this.setLayout(null);
	}
	
	private class GameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("touched");
            MainMenu menu = me;
            me = null;
            menu.dispose();
            GameFrame fr = new GameFrame(6);
    		fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }
}
