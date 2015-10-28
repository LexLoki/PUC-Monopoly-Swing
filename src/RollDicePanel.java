import java.awt.Container;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RollDicePanel extends JPanel {
	private Die _leftdie;
	private Die _rightdie;
	
	RollDicePanel () {
		_leftdie = new Die();
		_rightdie = new Die();
		
		JButton rollButton = new JButton("New Button");
		rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
		
		rollButton.addActionListener(new RollListener());
		
		this.setLayout(new BorderLayout(5,5));
		this.add(rollButton, BorderLayout.NORTH);
		this.add(_leftdie, BorderLayout.WEST);
		this.add(_rightdie, BorderLayout.EAST);
		
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}

	private class RollListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            _leftdie.roll();
            _rightdie.roll();
        }
    }
}
