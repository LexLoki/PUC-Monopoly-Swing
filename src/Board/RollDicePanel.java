package Board;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.StateMachine;

public class RollDicePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dice _leftdice;
	private Dice _rightdice;
	private JButton rollButton;
	
	public RollDicePanel (Dimension size) {
		
		int ax = size.width/2;
		int ay = size.height*3/4;
		int a = ax < ay ? ax : ay;
		Dimension diceD = new Dimension(a,a);
		_leftdice = new Dice(diceD);
		_leftdice.setLocation((ax-a)/2, (ay-a)/2);
		_rightdice = new Dice(diceD);
		_rightdice.setLocation(ax+_leftdice.getLocation().x, _leftdice.getLocation().y);
		
		rollButton = new JButton("Roll");
		rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
		rollButton.setBounds(0, ay, size.width, size.height-ay);
		
		rollButton.addActionListener(new RollListener());
		
		this.setLayout(new BorderLayout(5,5));
		this.add(rollButton, BorderLayout.NORTH);
		this.add(_leftdice, BorderLayout.WEST);
		this.add(_rightdice, BorderLayout.EAST);
		this.setSize(size);
		this.setLayout(null);
		
		//this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}
	
	public void activate(){
		rollButton.setVisible(true);
	}
	public void desactivate(){
		rollButton.setVisible(false);
	}

	private class RollListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int v1 = _leftdice.roll();
            int v2 = _rightdice.roll();
            StateMachine.diceRolled(v1+v2);
        }
    }
}
