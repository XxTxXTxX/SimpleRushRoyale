import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Queue;
import java.util.LinkedList;



import Cards.Card;
import Cards.Cards;
import Enemy.Enemy;
import Enemy.EnemyFactory;
import Enemy.Minion;
import Enemy.Spawner;

public class gameBoard {
	private Cards[][] board;
	private Player player1;
	private int[][] boardMarked; 
	private boolean gameEnd = false; //Boolean value shows if game is end or not.
	private int roundCount; //Round counter.
	private boolean enemyOnBoard = false; //Check if enemies are on the board.
	private Queue <Enemy> enemies = new LinkedList<>(); //Where to store enemies.
	private ArrayList <Enemy> enemyStore = new ArrayList();
	private EnemyFactory spawner = new Spawner(); //Which is used to generate random enemies.
	private int roundTurn = 0; // 0 indicates player's round and 1 indicates enemies round!!!!!!!!!!
	private int manaSpent;
	private int enemyCounter;
	private int[] attackable;

	public gameBoard(Player player1) {
		this.board = new Cards[1][5];
		this.boardMarked = new int[1][5];
		this.player1 = player1;
		this.roundCount = 0;
		this.manaSpent = 10;
		this.attackable = new int[5];
		System.out.println("Player " + player1.getName() + " entered game!");
	}
	
	
	public void boardDisplay() {
		for (int j = 0; j < 5; j++) {
			if (board[0][j] == null) {
				String placeHolder = "";
				System.out.print("[ " + placeHolder + " ]");
			}
			else {
				String placeHolder = board[0][j].getName();
				System.out.print("[ " + placeHolder + " ]" + " Level:" + board[0][j].getLevel());
			}
		}
		System.out.println("\n");
	}
	
	
	
	private void removeCardOnBoard(int row, int col) {
		board[row][col] = null;
		boardMarked[row][col] = 0;
	}
	
	
	
	public void combineCards(int firstCol, int secondCol) {
		int tempLevel = board[0][secondCol].getLevel();
		if (board[0][firstCol].getName().equals(board[0][secondCol].getName()) && board[0][firstCol].getLevel() == board[0][secondCol].getLevel()) {
			if (!board[0][secondCol].upperLevel()) {
				System.out.println("Level full, unable to combine two cards");
			}
			else {
				removeCardOnBoard(0, firstCol);
				removeCardOnBoard(0, secondCol);
				combineSpawn(0, secondCol);
				board[0][secondCol].setLevel(tempLevel);
				board[0][secondCol].levelUp();
				attackable[firstCol] = 0;
				attackable[secondCol] = 0;
			}
		}
		else {
			System.out.println("Error when combining two cards!! The level must be same and 2 cards also need to be same!!");
		}
	}
	
	private void combineSpawn(int row, int col) {
		Random rand = new Random();
		int playerUpperBond = rand.nextInt(5);
		board[row][col] = player1.getAvailableCards()[player1.getSet()][playerUpperBond].getCard();
	}
	
	
	private boolean is_full() {
		for (int j = 0; j < 5; j++) {
			if (boardMarked[0][j] == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	public void Spawn() {
		Random rand = new Random();
		int playerUpperBond = rand.nextInt(5);
		int boardCol = rand.nextInt(5);
		if (is_full()) {
			System.out.println("Unable to create new card on the board!!!");
			return;
		}
		while (board[0][boardCol] != null) {
			boardCol = rand.nextInt(5);
		}
		boardMarked[0][boardCol] = 1;
		board[0][boardCol] = player1.getAvailableCards()[player1.getSet()][playerUpperBond].getCard();
		player1.spendMana(manaSpent);
		System.out.println("Your Mana: " + player1.displayMana());
		manaSpent += 10;
		
	}
	
	
	public void refreshOnBoard() {
		Enemy enemy = spawner.spawn(roundCount);
		enemies.add(enemy);
	}
	

	public void testGameEnd() {
		this.gameEnd = true;
	}
	
	
	public boolean isGameEnd() {
		return this.gameEnd;
	}
	
	public boolean enemyOnBoard() {
		return this.enemyOnBoard;
	}
	
	public int getRoundTurn() {
		return this.roundTurn;
	}
	
	public void editRoundTurn(int turn) {
		this.roundTurn = turn;
	}
	
	public void playersTurn() {
		
		System.out.println("___________________Players Turn_____________________");
		
		Scanner choice = new Scanner(System.in);  
		
		if (roundCount == 0) {
			System.out.println("What you want to do?");
			System.out.println("Combine:please enter the number of which 2 cards you want to combine, example 'combine(1, 2)', 2 cards must be the same card!");
		    System.out.println("Attack: Please enter the number of enemy you want to attack with the number of card, example 'attack(1, 2), 1 is the enemy number and 2 is your second card'");
		    System.out.println("Spawn: Simple type 'spawn' then board will randomly generate one card on random position");
		    System.out.println("Display: Simple type 'display' to see what you have on board");
		    System.out.println("end: Simple type 'end' to end your turn");
		}
		else {
			System.out.println("Option: combine, attack, spawn, display, end");
		}


	    String c = choice.nextLine(); 
	    
	    if (c.equalsIgnoreCase("combine")) {
	    	Scanner c1 = new Scanner(System.in);
	    	Scanner c2 = new Scanner(System.in);
		    System.out.println("which 2 card you want to combine? Please enter the number of cards' position!");
		    int c3 = c1.nextInt();
		    int c4 = c1.nextInt();
		    combineCards(c3 - 1, c4 - 1);
	    }
	    
	    
	    else if (c.equalsIgnoreCase("attack")) {
	    	Scanner c1 = new Scanner(System.in);
	    	Scanner c2 = new Scanner(System.in);
		    System.out.println("Please Enter which enemy you want to attack: ");
		    int c3 = c1.nextInt();
		    System.out.println("Please Enter which card you want to attack the enemy: ");
		    int c4 = c1.nextInt();
		    
		    if (attackable[c4 - 1] == 1) {
		    	if (board[0][c4 - 1] != null) {
		    		board[0][c4 - 1].attack(enemyStore.get(c3 - 1));
				    attackable[c4 - 1] = 0;
				    if (enemyStore.get(c3 - 1).isDead()) {
				    	player1.addMana(10 * roundCount);
				    	enemyStore.set(c3 - 1, null);
				    	enemyCounter--;
				    }
		    	}
		    	
		    	else {
		    		System.out.println("The enemy is already dead!!!! Choose another enemy to attack!");
		    	}
		    	
		    }
			
		    else {
		    	System.out.println("This card is unavailable for actions in this turn.");
		    }
		    
	    }
	    
	    
	    else if (c.equalsIgnoreCase("spawn")) {
	    	if (player1.displayMana() < manaSpent) {
	    		System.out.println("You are so poor, you cant spawn!!");
	    		return;
	    	}
	    	Spawn();
	    }
	    
	    else if (c.equalsIgnoreCase("display")) {
	    	System.out.println("ROUND: " + roundCount);
			System.out.println("------------------Enemy------------------");
			System.out.println(enemyStore);
			System.out.println(" ");
			System.out.println("------------------You------------------");
	    	boardDisplay();
	    	System.out.println("Your mana: " + player1.displayMana());
	    }
	    
	    else if (c.equalsIgnoreCase("end")) {
	    	for (int i = 0; i < 5; i++) {
	    		if (board[0][i] != null) {
	    			attackable[i] = 1;
	    		}
	    	}
	    	editRoundTurn(1);
	    }
	    
	    
	    
	}
	
	private void enemySpawn() {
		if (!enemyOnBoard()) {
			Random rand = new Random(); 
			int value = rand.nextInt(5); 
			while (value == 0) {
				value = rand.nextInt(5); 
			}
			enemyCounter = value;
			for (int i = 0; i < value; i++) {
				enemies.add(spawner.spawn(roundCount));
			}
			Enemy temp = enemies.poll();
			while (temp != null) {
				enemyStore.add(temp);
				temp = enemies.poll();
			}
			
			enemyOnBoard = true;
		}
		
	}
	
	
	
	public void enemiesTurn() {
		if (enemyCounter == 0) {
			enemyStore.clear();
			enemyOnBoard = false;
		}
		
		System.out.println("___________________Minions Turn_____________________");
		enemySpawn();
		System.out.println(enemyStore);
		editRoundTurn(0);
		roundCount++;
	}
	
	
	
	
	
	
	
	
	
	
}
