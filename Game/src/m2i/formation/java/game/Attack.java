package m2i.formation.java.game;

public class Attack implements Action {
	
	private Player actor;
	private Player target;
	
	public Attack(Player actor, Player target) {
		this.actor=actor;
		this.target=target;
	}
	
	@Override
	public void execute() {
		target.takeDamage((int)(actor.weapon.damage*actor.dmgMult+1));
	}

}
