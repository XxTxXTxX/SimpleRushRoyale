import java.util.Scanner;

import Cards.Cards;
import Cards.CardsStorage;

public class Player {

	private int level = 0;
	private int coin = 0;
	private Cards[][] playerSets;
	private String playerName;
	private CardsStorage availableCards;
	private int currentSet = 0;
	private int mana;
	
	public Player(String name) {
		this.playerName = name;
		this.playerSets = new Cards[2][5];
		this.availableCards = new CardsStorage();
		this.mana = 100;
	}
	
	public void startSetting(Player player) {
		System.out.println("Would you like to change your set? (Yes/No)");
		Scanner yesNo = new Scanner(System.in); 
		String yn = yesNo.nextLine();
		if (yn.equalsIgnoreCase("Yes")) {
			Scanner replacedCard = new Scanner(System.in);  
		    System.out.println("Enter which card you want to abandon in your set: (Enter number 1 to 5)");
		    int rc = replacedCard.nextInt();
		    
		    Scanner card = new Scanner(System.in);  
		    System.out.println("Enter which card you want to replace with abandon card: ");
		    String c = card.nextLine();
		    player.replaceCard(rc, c);
		}
	}
	
	public void randomSets() {
		playerSets[0][0] = availableCards.getCard("锤");
		playerSets[0][1] = availableCards.getCard("晕");
		playerSets[0][2] = availableCards.getCard("小绿人");
		playerSets[0][3] = availableCards.getCard("蛤蟆");
		playerSets[0][4] = availableCards.getCard("骑士");
		playerSets[1][0] = availableCards.getCard("锤");
		playerSets[1][1] = availableCards.getCard("小绿人");
		playerSets[1][2] = availableCards.getCard("晕");
		playerSets[1][3] = availableCards.getCard("红小丑");
		playerSets[1][4] = availableCards.getCard("蛤蟆");
	}
	
	public void chooseSet(int set) {
		this.currentSet = set;
	}
	public int getSet() {
		return this.currentSet;
	}
	
	public void replaceCard(int rc, String card) {
		playerSets[currentSet][rc-1] = availableCards.getCard(card);
	}
	
	public Cards[][] getAvailableCards() {
		return playerSets;
	}
	
	public String getName() {
		return this.playerName;
	}
	
	public int displayMana() {
		return this.mana;
	}
	public void addMana(int mana) {
		this.mana += mana;
	}
	public void spendMana(int mana) {
		this.mana -= mana;
	}
	

	
	public void displayGamingSets() {
		System.out.println("You Bring: ");
		for (int i = 0; i < 5; i++) {
			System.out.println(playerSets[currentSet][i].getName());
		}
	}
}
