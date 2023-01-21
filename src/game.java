import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Cards.CardsStorage;

public class game {

	public static void main(String[] args) {		
		CardsStorage a = new CardsStorage();
		
		
		Scanner userName = new Scanner(System.in);  
	    System.out.println("Enter username");
	    String usN = userName.nextLine(); 
		Player player = new Player(usN);
		
		
		player.randomSets();
		player.chooseSet(0);
		player.displayGamingSets();
		player.startSetting(player);
		player.displayGamingSets();
		
		System.out.println("_____________________________________________");
		System.out.println("Game Starts!!!!!!");
		
		gameBoard board = new gameBoard(player);
		
		board.boardDisplay();
		System.out.println("_____________________________________________");
		board.boardDisplay();
		
		
		
		while (!board.isGameEnd()) {
			while (board.getRoundTurn() == 0) {
				board.playersTurn();
			}
			board.enemiesTurn();
		}
		
		
		
/*
 * 		long startTime = System.nanoTime();

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Program run time: " + totalTime/1000000 + " ms!");
*/
	}
}
