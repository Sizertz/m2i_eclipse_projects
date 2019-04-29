package m2i.formation.java.game;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// autoGame(10);
		newGame();
	}

	public static void newGame() {
		Player j1, j2;
		System.out.println("Player 1 is AI? (y/n)");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
			input = sc.nextLine();
		}
		if (input.equalsIgnoreCase("y"))
			j1 = new AIPlayer(new Weapon(10, "spoon of doom"), "AI1");
		else
			j1 = new Player(new Weapon(10, "sword of power"), "Player 1");

		System.out.println(j1);

		System.out.println("Player 2 is AI? (y/n)");
		input = sc.nextLine();
		while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
			input = sc.nextLine();
		}
		if (input.equalsIgnoreCase("y"))
			j2 = new AIPlayer(new Weapon(10, "spoon of doom"), "AI2");
		else
			j2 = new Player(new Weapon(10, "sword of power"), "Player 2");

		System.out.println(j2);

		while (true) {
			j1.playAgainst(j2);
			j2.playAgainst(j1);

			System.out.println(j1);
			System.out.println(j2);

			if (j1.getHealth() <= 0) {
				if (j2.getHealth() > 0) {
					System.out.println(j2.getUsername() + " gagne!");
					return;
				}
				System.out.println("Double K.O.!");
				return;
			}
			if (j2.getHealth() <= 0) {
				System.out.println(j1.getUsername() + " gagne!");
				return;
			}
		}
	}

	public static void autoGame(int nSteps) {
		Player j1 = new Player(new Weapon(10, "sword of power"), "Elfe");
		Player j2 = new Player(new Weapon(10, "spoon of doom"), "Orc");
		Random rng = new Random();

		System.out.println(j1);
		System.out.println(j2);
		for (int i = 0; i < nSteps; i++) {
			// player 1 turn. Random action.
			if (rng.nextInt(2) == 0) {
				j1.attack(j2);
			} else {
				j1.spell(j2);
			}
			// player 2 turn. Random action.
			if (rng.nextInt(2) == 0) {
				j2.attack(j1);
			} else {
				j2.spell(j1);
			}

			System.out.println(j1);
			System.out.println(j2);

			if (j1.getHealth() <= 0) {
				if (j2.getHealth() > 0) {
					System.out.println(j2.getUsername() + " gagne!");
					return;
				}
				System.out.println("Double K.O.!");
				return;
			}
			if (j2.getHealth() <= 0) {
				System.out.println(j1.getUsername() + " gagne!");
				return;
			}

		}
	}

}
