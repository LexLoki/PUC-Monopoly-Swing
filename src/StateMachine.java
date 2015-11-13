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
	private States stateToGo;
	
	static void startGame(int nPlayers, Board gameBoard, SpaceVisualizer space, ActionPanel ap){
		sharedInstance = new StateMachine(nPlayers, gameBoard, space, ap);
	}
	
	private StateMachine(int nPlayers, Board gameBoard, SpaceVisualizer space, ActionPanel ap){
		this.nPlayers = nPlayers;
		this.actualState = States.DiceRoll;
		this.gameBoard = gameBoard;
		spaceVisualizer = space;
		actionPanel = ap;
	}
	
	static void diceRolled(int value){
		sharedInstance.handleDiceRoll(value);
	}
	static void endAction(){
		sharedInstance.handleEndAction();
	}
	static void buySpace(PlaceSpace p){
		sharedInstance.handleBuySpace(p);
	}
	static void passSpace(){
		sharedInstance.handlePassSpace();
	}
	static void mouseOver(BoardSpace b){
		sharedInstance.handleMouseOver(b);
	}
	
	private void handlePassSpace(){
		nextPlayer();
	}
	
	private void handleMouseOver(BoardSpace b){
		if(actualState == States.DiceRoll && !isBusy){
			spaceVisualizer.setBoardSpace(b);
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
				else{
					spaceVisualizer.setBoardSpace(b);
					actionPanel.activate(b);
				}
			}
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
	
	public void handleBuySpace(PlaceSpace p){
		GamePlayer gp = gameBoard.getPlayer(playerIndex);
		if(p.canBuy()){
			if(gp.canBuy(p)){
				gp.buy(p);
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
	
	public int getPlayerIndex(){
		return playerIndex;
	}
	
	private void nextPlayer(){
		gameBoard.showPlayersBalance();
		playerIndex = (playerIndex+1)%nPlayers;
		actionPanel.desactivate();
		sharedInstance.transitionToState(States.DiceRoll);
	}
}
