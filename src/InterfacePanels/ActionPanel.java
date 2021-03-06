package InterfacePanels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Board.BoardSpace;
import Board.PlaceSpace;
import Board.SorteRevesSpace;
import Controller.StateMachine;


public final class ActionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String buyText = "Buy";
	static final String payText = "Pay";
	static final String passText = "Pass";
	static final String okText = "Ok";
	static final String hipText = "Mortgage";
	static final String freeHipText = "Free Mortgage";
	static final String sellText = "Sell";
	static final String houseText = "House";
	
	//private BoardSpace actualBoardSpace;
	private JButton buyButton;
	private JButton passButton;
	private JButton mortgageButton;
	private PlaceSpace actualSpace;
	
	public ActionPanel(Dimension d){
		this.setSize(d);
		//this.setBackground(Color.black);
		//this.setOpaque(false);
		buyButton = new JButton(buyText);
		buyButton.setBackground(Color.RED);
		buyButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
		int butWidth = d.width*9/10;
		int butHeight = d.height*9/40;
		
		buyButton.setBounds((d.width-butWidth)/2, (d.height/2-butHeight)/2, butWidth, butHeight);
		buyButton.addActionListener(new BuyListener());
		this.add(buyButton);
		buyButton.setVisible(false);
		
		passButton = new JButton(passText);
		passButton.setBounds(buyButton.getX(), buyButton.getY()+d.height/2, butWidth, butHeight);
		passButton.addActionListener(new PassListener());
		this.add(passButton);
		passButton.setVisible(false);
		
		mortgageButton = new JButton(hipText);
		mortgageButton.setBounds((d.width-butWidth)/2, (d.height-butHeight)/2, butWidth, butHeight);
		mortgageButton.addActionListener(new MortgageListener());
		this.add(mortgageButton);
		mortgageButton.setVisible(false);
		
		//buyButton.setVisible(true);
		this.setLayout(null);
	}
	
	public void activate(BoardSpace b){
		if(b instanceof PlaceSpace){
			PlaceSpace ps = (PlaceSpace)b;
			actualSpace = ps;
			if(ps.canBuy()){
				buyButton.setText(buyText);
				passButton.setText(passText);
				passButton.setVisible(true);
			}
			else{
				buyButton.setText(payText);
			}
			buyButton.setVisible(true);
		}
		else if(b instanceof SorteRevesSpace){
			buyButton.setText(okText);
			buyButton.setVisible(true);
		}
	}
	
	public void activateManagement(PlaceSpace p, Boolean canBuyHouse, Boolean canFreeMortgage){
		passButton.setText(sellText);
		passButton.setVisible(true);
		actualSpace = p;
		if(canBuyHouse){
			buyButton.setText(houseText);
			buyButton.setVisible(true);
		}
		if(!p.isMortgaged()){
			mortgageButton.setText(hipText);
			mortgageButton.setVisible(true);
		}
		else if(canFreeMortgage){
			mortgageButton.setText(freeHipText);
			mortgageButton.setVisible(true);
		}
	}
	
	public void desactivateBuy(){
		buyButton.setVisible(false);
	}
	
	public void desactivate(){
		buyButton.setVisible(false);
		passButton.setVisible(false);
		mortgageButton.setVisible(false);
	}
	
//	public void setBoardSpace(BoardSpace space){
//		actualBoardSpace = space;
//		System.out.println("aparece carai");
//		this.repaint();
//	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
	
	private class BuyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StateMachine.buySpace(actualSpace);
        }
    }
	private class PassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StateMachine.passSpace(actualSpace);
        }
    }
	private class MortgageListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			StateMachine.mortgageSpace(actualSpace);
		}
	}
}
