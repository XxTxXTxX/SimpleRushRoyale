package Cards;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Enemy.Enemy;


public class Card implements Cards{

	private String name;
	private String description;
	private int level;
	private int damage;
	private double attackSpeed;
	private boolean onAttack;
	
	public Card(String name, int damage, double speed) {
		this.name = name;
		this.level = 1;
		this.damage = (int) (damage * level * 1.3);
		this.attackSpeed = speed;
		this.onAttack = false;

	}
	@Override
	public void setDamage(int damage) {
		this.damage = damage;
		
	}
	@Override
	public void setAttackSpeed(double speed) {
		this.attackSpeed = speed;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
		
	}
	@Override
	public void displayCard() {
		System.out.println("This card is " + this.name + ", damage = " + this.damage + ", attackSpeed = " + this.attackSpeed + ", " + this.description);
	}
	@Override
	public int getDamage() {
		return this.damage;
	}
	@Override
	public double getAttackSpeed() {
		return this.attackSpeed;
	}
	@Override
	public String getDescription() {
		return this.description;
	}
	@Override
	public String getName() {
		return this.name;
	}
	
	public Cards getCard() {
		return new Card(this.name, this.damage, this.attackSpeed);
	}
	@Override
	public boolean upperLevel() {
		if (this.level + 1 <= 7) {
			return true;
		}
		return false;
		
	}
	@Override
	public boolean CheckLevelDown() {
		if (this.level - 1 > 1) {
			return true;
		}
		return false;
	}
	@Override
	public int getLevel() {
		return this.level;
	}
	@Override
	public void levelUp() {
		this.level++;
		this.damage *= 1.3;
	}
	@Override
	public void levelDown() {
		this.level--;
		this.damage *= 0.8;
		
	}
	@Override
	public void setLevel(int level) {
		this.level = level;
		this.damage = (int) (damage * level * 1.3);
	}
	
	public boolean isAttack() {
		return this.onAttack;
	}
	
	@Override
	public void attack(Enemy enemy) {
		enemy.takeDamage(this.damage);		
	}
	
	
	
		
}
	
	
	
	
