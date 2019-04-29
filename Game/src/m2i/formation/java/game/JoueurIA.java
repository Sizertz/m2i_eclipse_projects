package m2i.formation.java.game;

import java.util.Random;

public class JoueurIA extends Joueur {

	public JoueurIA(String weapon, String username) {
		super(weapon, username);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void playAgainst(Joueur j2) {
		Random rng = new Random();
		if (rng.nextInt(2) == 0) {
			this.attack(j2);
		} else {
			this.spell(j2);
		}
	}
}
