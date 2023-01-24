package Enemy;


public class Minion extends Enemy{

	public Minion(int round) {
		this.health = 255 * (round + 1);
		this.refreshTime = 2;
		this.armor = 15 ; //It should be percentage so 15%, when calculating = 15/100 = 0.15 = 15%.
		this.isDead = false;
	}
	
	public int getRefreshtime() {
		return this.refreshTime;
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	public int getHealth() {
		return this.health;
		
	}


	@Override
	public void takeDamage(int damage) {
		System.out.println( "Minion is taking damage: " + "-" + (damage * (1 - this.armor / 100)) );
		this.health = health - damage * (1 - this.armor / 100);
		System.out.println( "Health left: " + this.health);
		if (this.health <= 0) {
			System.out.println("Minion is dead!");
			this.isDead = true;
		}
		
	}

	@Override
	public boolean isDead() {
		return this.isDead;
	}
	
}
