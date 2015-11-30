import javax.swing.*;

import Board.Board;
import Controller.GlobalData;
import Controller.StateMachine;
import InterfacePanels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Board gameBoard;
	private final SpaceVisualizer spaceVisualizer;
	private final Dimension insideSize;
	private final ActionPanel actionPanel;
	private final PlayersInfoPanel pInfoPanel;
	private final StatusPanel statusPanel;
	
	
	public GameFrame(int nPlayers){
		super("Game");
		this.setLayout(null);
		Dimension size = ResizeUtils.getBestFrameFor(1000, 750);
		setSize(size);
		setVisible(true);
		int marginTop = getInsets().top;
		Dimension wholeSize = new Dimension(size.width,size.height+marginTop);
		this.setSize(wholeSize);
		int d = (int) (0.65*size.width);
		gameBoard = new Board(new Dimension(d,d), nPlayers);
		ResizeUtils.setPositionBottomLeft(gameBoard, 0, size.height);
		getContentPane().add(gameBoard);
		//this.repaint();
		insideSize = size;
		//(0.225x, 0.28125x)
		Dimension vD = new Dimension((int)(0.225*size.width),(int)(0.28125*size.width));
		spaceVisualizer = new SpaceVisualizer(vD);
		ResizeUtils.setPositionBottomLeft(spaceVisualizer, d, size.height);
		getContentPane().add(spaceVisualizer);
		//(0.125x, 0.28125x)
		Dimension vA = new Dimension((int)(0.125*size.width),vD.height);
		actionPanel = new ActionPanel(vA);
		ResizeUtils.setPositionBottomLeft(actionPanel, d+vD.width, size.height);
		getContentPane().add(actionPanel);
		//(0.35x, 0.46875x)
		Dimension vP = new Dimension((int)(0.35*size.width),(int)(0.46875*size.width));
		pInfoPanel = new PlayersInfoPanel(vP, nPlayers);
		ResizeUtils.setPositionUpperRight(pInfoPanel, size.width, 0);
		getContentPane().add(pInfoPanel);
		//(0.4875x, 0.1x)
		Dimension vS = new Dimension((int)(0.4875*size.width),(int)(0.1*size.width));
		statusPanel = new StatusPanel(vS, gameBoard.getPlayers());
		statusPanel.setLocation(new Point(0,0));
		getContentPane().add(statusPanel);
		
		StateMachine.startGame(nPlayers, gameBoard, spaceVisualizer, actionPanel, pInfoPanel, statusPanel);
		
		repaint();
		this.setLayout(null);
	}
}
