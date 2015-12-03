package Controller;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Board.Board;
import Board.BoardSpace;
import Board.CompanySpace;
import Board.EffectSpace;
import Board.GamePlayer;
import Board.PlaceSpace;
import Board.SorteRevesSpace;
import Board.TerritorySpace;
import InterfacePanels.ActionPanel;
import InterfacePanels.PlayersInfoPanel;
import InterfacePanels.SpaceVisualizer;
import InterfacePanels.StatusPanel;
import Model.SorteRevesModel;
import PreGame.GameFrame;

public class StateMachine {
	
	private static StateMachine sharedInstance;
	
	enum States{
		DiceRoll,
		Action;
	}
	private int playerIndex = 0;
	private int nPlayers;
	private int lastDice;
	private States actualState = States.DiceRoll;
	private boolean isBusy = false;
	private Board gameBoard;
	private SpaceVisualizer spaceVisualizer;
	private ActionPanel actionPanel;
	private PlayersInfoPanel playerPanel;
	private States stateToGo;
	private SorteRevesModel SRm;
	private StatusPanel statusPanel;
	
	public static void startGame(int nPlayers, Board gameBoard, SpaceVisualizer space, ActionPanel ap, PlayersInfoPanel pp, StatusPanel sp){
		sharedInstance = new StateMachine(nPlayers, gameBoard, space, ap, pp, sp);
	}
	
	private StateMachine(int nPlayers, Board gameBoard, SpaceVisualizer space, ActionPanel ap, PlayersInfoPanel pp, StatusPanel sp){
		this.nPlayers = nPlayers;
		this.actualState = States.DiceRoll;
		this.gameBoard = gameBoard;
		spaceVisualizer = space;
		actionPanel = ap;
		playerPanel = pp;
		statusPanel = sp;
	}
	
	static public void diceRolled(int value1, int value2){
		sharedInstance.handleDiceRoll(value1, value2);
	}
	static public void endAction(){
		sharedInstance.handleEndAction();
	}
	static public void buySpace(PlaceSpace p){
		sharedInstance.handleBuySpace(p);
	}
	static public void passSpace(PlaceSpace space){
		sharedInstance.handlePassSpace(space);
	}
	static public void mortgageSpace(PlaceSpace space){
		sharedInstance.handleMortgage(space);
	}
	static public void mouseOver(BoardSpace b){
		sharedInstance.handleMouseOver(b);
	}
	static public void mouseOverOwned(PlaceSpace p, int playerIndex){
		sharedInstance.handleMouseOverOwned(p, playerIndex);
	}
	static public GamePlayer getPlayer(int index){
		return sharedInstance.gameBoard.getPlayer(index);
	}
	static public GamePlayer[] getPlayers(){
		return sharedInstance.gameBoard.getPlayers();
	}
	static public int getLastDiceValue(){
		return sharedInstance.getMyLastDiceValue();
	}
	
	private int getMyLastDiceValue(){
		return this.lastDice;
	}
	
	//Handle Pass turn and sell action
	private void handlePassSpace(PlaceSpace space){
		if(space.getOwner()!=null){
			GamePlayer p = gameBoard.getPlayer(playerIndex);
			if(space instanceof TerritorySpace){
				TerritorySpace t = (TerritorySpace)space;
				if(t.getHouseQuant()>0){
					p.earn(t.sellHouse());
					spaceVisualizer.repaint();
				}
				else{
					p.sell(space);
					actionPanel.desactivate();
				}
			}
			else{
				p.sell(space);
				actionPanel.desactivate();
			}
		}
		else
			nextPlayer();
		playerPanel.updatePlayer(playerIndex);
	}
	
	private void handleMouseOver(BoardSpace b){
		if(actualState == States.DiceRoll && !isBusy){
			spaceVisualizer.setBoardSpace(b);
		}
	}
	
	private void handleMouseOverOwned(PlaceSpace p, int playerIndex){
		if(actualState == States.DiceRoll && !isBusy){
			actionPanel.desactivate();
			spaceVisualizer.setBoardSpace(p);
			if(playerIndex == this.playerIndex){
				checkCanBuy(p);
			}
		}
	}
	
	private void checkCanBuy(PlaceSpace p){
		Boolean canBuy=false, mortOk = false;
		GamePlayer pl = gameBoard.getPlayer(playerIndex);
		if(p instanceof TerritorySpace){
			TerritorySpace t = (TerritorySpace)p;
			mortOk = t.getHouseQuant()==0;
			if(pl.canBuyHouseFor(t))
				canBuy = true;
		}
		actionPanel.activateManagement(p, canBuy, pl.canBuy(p.getMortgagePayment())&&mortOk);
	}
	
	private void handleEndAction(){
		if(isBusy){
			isBusy = false;
			transitionToState(stateToGo);
		}
	}
	
	private void transitionToState(States s){
		actualState = s;
		if(s == States.Action){
			actionPanel.desactivate();
			gameBoard.desactivateDice();
			BoardSpace b = gameBoard.getPlayerSpace(playerIndex);
			System.out.println(b.getName());
			if (b instanceof PlaceSpace){
				PlaceSpace ps = (PlaceSpace)b;
				GamePlayer gp = gameBoard.getPlayer(playerIndex);
				GamePlayer owner = ps.getOwner();
				if(ps.canBuy() && !gp.canBuy(ps)){
					nextPlayer();
				}
				else if(owner == gp){
					nextPlayer();
				}
				else{
					spaceVisualizer.setBoardSpace(b);
					actionPanel.activate(b);
				}
			}
			
			else if(b instanceof EffectSpace){
				//do a confirmation phase
				int value = ((EffectSpace)b).getValue();
				GamePlayer pl = gameBoard.getPlayer(playerIndex);
				if(value==0){
					gameBoard.goToPrison(playerIndex);
					pl.goToPrison();
				}else{
					pl.earn(value);
				}
				nextPlayer();
			}
			//else if (b instanceof SorteRevesSpace){
				//SORTE OU REVES FAZER TRETA
				//SRm = ((SorteRevesSpace)b).startSorteReves();
				// ENVIA IMAGEM DO SRm para o visualizer e chama actionPanel
				//CHAMAR VISUALIZER
				//actionPanel.activate(b);
			//}
			else{
				nextPlayer();
			}
		}
		if(s == States.DiceRoll){
			gameBoard.activateDice();
		}
	}
	
	private void handleDiceRoll(int v1, int v2){
		if(!isBusy)
			if(actualState == States.DiceRoll){
				int value = v1+v2;
				lastDice = value;
				GamePlayer gp = gameBoard.getPlayer(playerIndex);
				if(gp.isOnPrison())
					gp.prisonDice(v1, v2);
				if(gp.isOnPrison())
					nextPlayer();
				else{
					isBusy = true;
					stateToGo = States.Action;
					gameBoard.doMovement(playerIndex,value);
				}
			}
	}
	
	private void updatePlayerPanel(int index){
		playerPanel.updatePlayer(index);
	}
	
	public void handleBuySpace(PlaceSpace p){
		if(p != null){
			GamePlayer gp = gameBoard.getPlayer(playerIndex);
			//BUYING HOUSE FOR PROPERTY
			GamePlayer owner = p.getOwner();
			if(owner!=null && owner == gp){
				TerritorySpace t = (TerritorySpace)p;
				spaceVisualizer.repaint();
				t.putHouse();
				gp.buy(t.getNextInvestment());
				if(!gp.canBuyHouseFor(t))
					actionPanel.desactivateBuy();
			}
			//BUYING PROPERTY
			else if(p.canBuy()){
				if(gp.canBuy(p)){
					gp.buy(p);
					updatePlayerPanel(playerIndex);
					nextPlayer();
				}
			}
			//PAYING TAX FOR PROPERTY
			else{
				payment(gp,p);
				nextPlayer();
			}
		}
		
		else{
			//OK BUTTON DO SORTE REVES
			//EXECUTAR ACAO DO SRM
			//gameBoard.doMovement(playerIndex, value);
		}
	}
	
	private void payment(GamePlayer gp, PlaceSpace p){
		int value;
		if(p instanceof CompanySpace)
			value = ((CompanySpace)p).getTax(lastDice);
		else
			value = ((TerritorySpace)p).getTax();
		gp.buy(value);
		if(gp.getBalance()<0){
			gp.sellEverything();
			value += gp.getBalance();
			GamePlayer winner = finish();
			if(winner != null){
				showWinDialog(winner.getId());
			}
		}
		p.getOwner().earn(value);
	}
	
	private void handleMortgage(PlaceSpace p){
		actionPanel.desactivate();
		if(p.isMortgaged()) p.freeMortgage(); else p.mortgage();
		checkCanBuy(p);
	}
	
	private GamePlayer finish(){
		GamePlayer[] players = gameBoard.getPlayers();
		GamePlayer hasFound = null;
		for(GamePlayer pl : players)
			if(!pl.isBroke())
				if(hasFound != null)
					return null;
				else
					hasFound = pl;
		return hasFound;
	}
	
	public int getPlayerIndex(){
		return playerIndex;
	}
	
	private void nextPlayer(){
		gameBoard.showPlayersBalance();
		
		for(playerIndex = (playerIndex+1)%nPlayers; 
				gameBoard.getPlayer(playerIndex).isBroke();
				playerIndex = (playerIndex+1)%nPlayers);
		statusPanel.setTurn(playerIndex);
		actionPanel.desactivate();
		sharedInstance.transitionToState(States.DiceRoll);
	}
	
	private void showWinDialog(int winnerIndex){
		//JOptionPane p = new JOptionPane();
		GameFrame gf = (GameFrame)SwingUtilities.getWindowAncestor(gameBoard);
		int result = JOptionPane.showConfirmDialog(null, 
				"Player "+GlobalData.getInstance().getPlayerColorName(winnerIndex)+
				" won the match, play again?", "Game Finished", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION){
			gf.replay();
		}
		else{
			gf.toMenu();
		}
	}
}
