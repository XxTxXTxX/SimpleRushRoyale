package Cards;
import java.util.ArrayList;

public class CardsStorage{

	private ArrayList<Cards> data = new ArrayList<Cards>();
	
	public CardsStorage() {
		Cards hammer = new Card("锤", 237);
		Cards qishi = new Card("骑士", 0);
		Cards frog = new Card("蛤蟆", 35);
		Cards green = new Card("小绿人", 18);
		Cards joker = new Card("红小丑", 22);
		Cards yun = new Card("晕", 110);
		data.add(joker);
		data.add(yun);
		data.add(green);
		data.add(frog);
		data.add(qishi);
		data.add(hammer);
	}
	
	
	public Cards getCard(String name) {
		for (int i = 0; i < data.size(); i++) {
		      if (data.get(i).getName().equals(name)) {
		    	  return data.get(i);
		      }
		}
		return null;
	}
	
	public void displayCards() {
		for (int i = 0; i < data.size(); i++) {
		      System.out.println(data.get(i).getName());
		    }
	}

}
