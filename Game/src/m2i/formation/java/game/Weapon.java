package m2i.formation.java.game;

public class Weapon extends Item {
	protected int damage;
	protected String name;

	public Weapon(int damage, String name) {
		super();
		this.damage = damage;
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}
