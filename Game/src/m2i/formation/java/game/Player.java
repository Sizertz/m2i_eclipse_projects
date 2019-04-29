package m2i.formation.java.game;

import java.util.Scanner;

public class Player {
	protected String username;
	protected int health;
	protected int maxHealth;
	protected int mana;
	protected int maxMana;
	protected int xp;
	protected Weapon weapon;
	protected int level = 0;
	protected float dmgMult = 1;
	protected float defMult = 1;

	public int getHealth() {
		return health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return getUsername() + " HP : " + this.health + "/" + this.maxHealth + " - Mana : " + this.mana + "/"
				+ this.maxMana + " - XP : " + this.xp + "/100";
	}

	public Player(Weapon weapon, String username) {
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
		this.level++;
	}

	public void attack(Player j2) {
		System.out.println(this.username + " attacks with " + this.weapon);
		xp += 10;
		j2.takeDamage(10);
	}

	public void takeDamage(int dmg) {
		health -= Math.min((int)(dmg * defMult), 1);
		System.out.println(this.username + " took " + dmg * defMult + " damage");
	}
	
	

	public void spell(Player j2) {
		if (mana >= 30) {
			System.out.println(this.username + " used spell");
			j2.takeDamage(30);
			mana -= 30;
			xp += 20;
		} else {
			System.out.println(this.username + " failed to use spell : not enough mana");
		}
	}

	public void playAgainst(Player j2) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while (!input.equalsIgnoreCase("attack") && !input.equalsIgnoreCase("spell")) {
			input = sc.nextLine();
		}
		if (input.equalsIgnoreCase("attack"))
			this.attack(j2);
		else if (input.equalsIgnoreCase("spell"))
			this.spell(j2);
	}
}
