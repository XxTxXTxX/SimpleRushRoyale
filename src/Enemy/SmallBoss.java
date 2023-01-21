package Enemy;

public class SmallBoss extends Enemy{
	
	public SmallBoss(int round) {
		this.health = 2042 * round;
		this.refreshTime = 15;
		this.armor = 30; //It should be percentage so 30%, when calculating = 30/100 = 0.3 = 30%.
		this.isDead = false;

	}
	
	public int getRefreshtime() {
		return this.refreshTime;
	}

	
	public int getHealth() {
		return this.health;
		
	}


	@Override
	public void takeDamage(int damage) {
		System.out.println("smallBoss is taking damage: " + "-" + (damage * (1 - this.armor / 100)) );
		this.health = health - damage * (1 - this.armor / 100);
		if (this.health <= 0) {
			System.out.println("SmallBoss is dead!");
			this.isDead = true;
		}		
	}

	@Override
	public boolean isDead() {
		return this.isDead;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
