package Cards;

import Enemy.Enemy;

public interface Cards {
	void setDamage(int damage);
	void setAttackSpeed(double speed);
	void setDescription(String description);
	int getDamage();
	int getLevel();
	void setLevel(int level);
	String getName();
	double getAttackSpeed();
	String getDescription();
	void displayCard();
	Cards getCard();
	boolean upperLevel();
	boolean CheckLevelDown();
	void levelUp();
	void levelDown();
	void attack(Enemy enemy);
}
