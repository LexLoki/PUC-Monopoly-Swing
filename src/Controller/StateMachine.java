package Controller;
import Board.Board;
import Board.BoardSpace;
import Board.CompanySpace;
import Board.GamePlayer;
import Board.PlaceSpace;
import Board.TerritorySpace;
import InterfacePanels.ActionPanel;
import InterfacePanels.PlayersInfoPanel;
import InterfacePanels.SpaceVisualizer;
import Model.SorteRevesModel;

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
	
	public static void startGame(int nPlayers, Board gameBoard, SpaceVisualizer space, ActionPanel ap, PlayersInfoPanel pp){
		sharedInstance = new StateMachine(nPlayers, gameBoard, space, ap, pp);
	}
	
	private StateMachine(int nPlayers, Board gameBoard, SpaceVisualizer space, ActionPanel ap, PlayersInfoPanel pp){
		this.nPlayers = nPlayers;
		this.actualState = States.DiceRoll;
		this.gameBoard = gameBoard;
		spaceVisualizer = space;
		actionPanel = ap;
		playerPanel = pp;
	}
	
	static public void diceRolled(int value){
		sharedInstance.handleDiceRoll(value);
	}
	static public void endAction(){
		sharedInstance.handleEndAction();
	}
	static public void buySpace(PlaceSpace p){
		sharedInstance.handleBuySpace(p);
	}
	static public void passSpace(){
		sharedInstance.handlePassSpace();
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
	
	private void handlePassSpace(){
		nextPlayer();
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
				Boolean canBuy=false;
				if(p instanceof TerritorySpace){
					TerritorySpace t = (TerritorySpace)p;
					if(gameBoard.getPlayer(playerIndex).canBuyHouseFor(t))
						canBuy = true;
				}
				actionPanel.activateManagement(p, canBuy);
			}
		}
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
			gameBoard.desactivateDice();
			BoardSpace b = gameBoard.getPlayerSpace(playerIndex);
			System.out.println(b.getName());
			if (b instanceof PlaceSpace){
				PlaceSpace ps = (PlaceSpace)b;
				GamePlayer gp = gameBoard.getPlayer(playerIndex);
				if(ps.canBuy() && !gp.canBuy(ps)){
					nextPlayer();
				}
				else if(ps.getOwner() == gp){
					nextPlayer();
				}
				else{
					spaceVisualizer.setBoardSpace(b);
					actionPanel.activate(b);
				}
			}
//			else if (b instanceof SorteRevesSpace){
//				//SORTE OU REVES FAZER TRETA
//				SRm = ((SorteRevesSpace)b).startSorteReves();
//				// ENVIA IMAGEM DO SRm para o visualizer e chama actionPanel
//				//CHAMAR VISUALIZER
//				actionPanel.activate(b);
//			}
			else{
				nextPlayer();
			}
		}
		if(s == States.DiceRoll){
			gameBoard.activateDice();
		}
	}
	
	private void handleDiceRoll(int value){
		if(!isBusy)
			if(actualState == States.DiceRoll){
				lastDice = value;
				isBusy = true;
				stateToGo = States.Action;
				gameBoard.doMovement(playerIndex,value);
			}
	}
	
	private void updatePlayerPanel(int index){
		playerPanel.updatePlayer(index);
	}
	
	public void handleBuySpace(PlaceSpace p){
		if(p != null){
			
		GamePlayer gp = gameBoard.getPlayer(playerIndex);
		if(p.canBuy()){
			if(gp.canBuy(p)){
				gp.buy(p);
				updatePlayerPanel(playerIndex);
				nextPlayer();
			}
		}
		else{
			int value;
			if(p instanceof CompanySpace)
				value = ((CompanySpace)p).getTax(lastDice);
			else
				value = ((TerritorySpace)p).getTax();
			gp.buy(value);
			p.getOwner().earn(value);
			nextPlayer();
		}
		}
		
		else{
			//OK BUTTON DO SORTE REVES
			//EXECUTAR ACAO DO SRM
			//gameBoard.doMovement(playerIndex, value);
		}
	}
	
	public int getPlayerIndex(){
		return playerIndex;
	}
	
	private void nextPlayer(){
		gameBoard.showPlayersBalance();
		
		for(playerIndex = (playerIndex+1)%nPlayers; 
				gameBoard.getPlayer(playerIndex).isBroke();
				playerIndex = (playerIndex+1)%nPlayers);
		actionPanel.desactivate();
		sharedInstance.transitionToState(States.DiceRoll);
	}
}
