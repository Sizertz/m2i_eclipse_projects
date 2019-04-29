package m2i.formation.java;

public interface IMouvement {
	final static int VitesseLimite = 20;

	public abstract void freiner();

	void accelerer(); // public et abstract sont sous-entendus

}
