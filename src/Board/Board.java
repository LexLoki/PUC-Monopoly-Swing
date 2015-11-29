package Board;
import javax.imageio.ImageIO;
import javax.swing.*;

import Controller.DAO;
import Controller.GlobalData;
import Controller.StateMachine;
import Model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image bgImg;
	private final String path = "assets/tabuleiroRJ.jpg";
	private ArrayList<BoardSpace> boardSpaces = new ArrayList<BoardSpace>();
	
	final private int LEFT = 0;
	final private int UP = 1;
	final private int RIGHT = 2;
	final private int DOWN = 3;
	
	final private int nSpaces = 36;
	
	private RollDicePanel dice;
	
	private ArrayList<GamePlayer> players = new ArrayList<GamePlayer>();
	
	public Board(Dimension size, int nPlayers){
		super();
		
		this.setSize(size);
		try{
			bgImg = ImageIO.read(new File(path));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		int i;
		int height = size.width * 136/1000;
		int width = height * 240 / 350;
		int dx = width, dy = height;
		BoardUnit[] fs = DAO.getBoardSpaces();
		BoardSpace space;
		Dimension d = new Dimension(dx,dy);
		Dimension sqD = new Dimension(dy,dy);
		//0
		space = new BoardSpace(sqD, 0);
		space.setLocation(0, size.height-dy);
		this.add(space);
		boardSpaces.add(space);
		for(i=0; i<8; i++){
			System.out.println(i);
			space = prepareFromModel(d,fs[i+1],0);
			space.setLocation(0, size.height-dy-dx*(i+1));
			this.add(space);
			boardSpaces.add(space);
		}
		space = new BoardSpace(sqD, 1);
		space.setLocation(0, 0);
		this.add(space);
		boardSpaces.add(space);
		//1
		for(i=0; i<8; i++){
			space = prepareFromModel(d,fs[i+10],1);
			space.setLocation(dy+dx*i, 0);
			this.add(space);
			boardSpaces.add(space);
		}
		space = new BoardSpace(sqD, 2);
		space.setLocation(size.width-dy, 0);
		this.add(space);
		boardSpaces.add(space);
		//2
		for(i=0; i<8; i++){
			space = prepareFromModel(d,fs[i+19],2);
			space.setLocation(size.width-dy, dy+dx*i);
			this.add(space);
			boardSpaces.add(space);
		}
		space = new BoardSpace(sqD, 3);
		space.setLocation(size.width-dy, size.height-dy);
		this.add(space);
		boardSpaces.add(space);
		//3
		for(i=0; i<8; i++){
			space = prepareFromModel(d,fs[i+28],3);
			space.setLocation(size.width-dy-dx*(i+1), size.height-dy);
			this.add(space);
			boardSpaces.add(space);
		}
		GlobalData inst = GlobalData.getInstance();
		BoardSpace first = boardSpaces.get(0);
		for(i=0; i<nPlayers; i++){
			GamePlayer p = new GamePlayer(new PlayerModel(i,2458),inst.getPlayerColor(i));
			p.setSpace(first);
			first.setPlayer(p);
			players.add(p);
		}
		
		Dimension diceD = new Dimension(size.width*3/9, size.width*2/9);
		dice = new RollDicePanel(diceD);
		dice.setLocation(size.width-sqD.width-diceD.width-2, size.height-sqD.height-diceD.height-2);
		this.add(dice);
		this.setLayout(null);
	}
	
	public void doMovement(int playerIndex, int value){
		GamePlayer player = players.get(playerIndex);
		BoardSpace currentSpace = player.getSpace();
		int index = boardSpaces.indexOf(currentSpace);
		index = (index+value)%nSpaces;
		currentSpace.removePlayer(player);
		BoardSpace newSpace = boardSpaces.get(index);
		newSpace.setPlayer(player);
		player.setSpace(newSpace);
		newSpace.repaint();
		currentSpace.repaint();
		StateMachine.endAction();
	}
	
	public void showPlayersBalance(){
		for (GamePlayer p:players){
			
			System.out.println(p.getId() + ": " + p.getBalance());
		}
	}
	
	public BoardSpace getPlayerSpace(int playerIndex){
		return getPlayer(playerIndex).getSpace();
	}
	
	public GamePlayer getPlayer(int playerIndex){
		for(GamePlayer p : players){
			if(p.getId() == playerIndex)
				return p;
		}
		return null;
	}
	
	public GamePlayer[] getPlayers(){
		return (GamePlayer[])players.toArray(new GamePlayer[0]);
	}
	
	private BoardSpace prepareFromModel(Dimension d, BoardUnit model, int local){
		BoardSpace s;
		if(model instanceof TerritoryModel){
			s = new TerritorySpace(d,(TerritoryModel)model,local);
		}
		else if(model instanceof CompanyModel){
			s = new CompanySpace(d,(CompanyModel)model,local);
		}
		else if(model instanceof SorteRevesModel){
			s = new SorteRevesSpace(d,(SorteRevesModel)model,local);
		}
		else{
			//effect
			s = new EffectSpace(d,(EffectModel)model,local);
		}
		return s;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void activateDice(){
		dice.activate();
	}
	public void desactivateDice(){
		dice.desactivate();
	}
	
}
