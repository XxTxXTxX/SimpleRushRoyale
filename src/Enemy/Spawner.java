package Enemy;

public class Spawner implements EnemyFactory{
	int counter = 0;

	@Override
	public Enemy spawn(int roundCount) {
		if (counter == 10) {
			counter = 0;
			return new SmallBoss(roundCount);
		}
		counter++;
		return new Minion(roundCount);

	}

}
