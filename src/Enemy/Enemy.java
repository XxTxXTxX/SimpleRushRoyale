package Enemy;

public abstract class Enemy {

	protected int health;
	protected int refreshTime;
	protected int armor;
	protected boolean isDead;
	
	public abstract void attack();
	public abstract int getRefreshtime();
	public abstract int getHealth();
	public abstract void takeDamage(int damage);
	public abstract boolean isDead();
	
	
}

