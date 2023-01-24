
public class manaSystem {
	private int manaProduce;
	private int level;
	
	public manaSystem() {
		this.manaProduce = 50;
		this.level = 1;
	}
	
	public void addLevel() {
		if (this.level < 10) {
			this.level++;
			this.manaProduce += 40;
		}
	}
	
	public boolean is_LevelFull() {
		if (this.level == 10) {
			return true;
		}
		return false;
	}
	
	public int manaAdd() {
		return this.manaProduce;
	}

}
