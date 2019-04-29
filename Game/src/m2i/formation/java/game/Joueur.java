package m2i.formation.java.game;

import java.util.Scanner;

public class Joueur {
	private String username;
	private int health;
	private int maxHealth;
	private int mana;
	private int maxMana;
	private int xp;
	private String weapon;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public Joueur(String weapon, String username) {
		super();
		this.setUsername(username);
		this.health = this.maxHealth = 100;
		this.mana = this.maxMana = 100;
		this.weapon = weapon;
		this.xp = 0;
	}

	public void levelUp() {
		this.maxHealth += 10;
		this.maxMana += 10;
		this.health = this.maxHealth;
		this.mana = this.maxMana;
		System.out.println(this.username + " leveled Up!");
		this.xp -= 100;
	}

	public void attack(Joueur j2) {
		System.out.println(this.username + " attacks with " + this.weapon);
		xp += 10;
		j2.takeDamage(10);
	}

	public void takeDamage(int dmg) {
		health -= dmg;
		System.out.println(this.username + " took 10 damage");
	}

	public void spell(Joueur j2) {
		if (mana >= 30) {
			System.out.println(this.username + " used spell");
			j2.takeDamage(30);
			mana -= 30;
			xp += 20;
		} else {
			System.out.println(this.username + " failed to use spell : not enough mana");
		}
	}

	public String toString() {
		return getUsername() + " HP : " + this.health + "/" + this.maxHealth + " - Mana : " + this.mana + "/"
				+ this.maxMana + " - XP : " + this.xp + "/100";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void playAgainst(Joueur j2) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while (input != "attack" && input != "spell") {
			input = sc.nextLine();
		}
		if (input == "attack")
			this.attack(j2);
		else if (input == "spell")
			this.spell(j2);
	}
}
