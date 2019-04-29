package balmes.m2i.singleton.personne;

public class Test {

	public static void main(String[] args) {
		func();
		func();
		func();
	}

	private static void func() {
		Personne p1 = SinglePersonne.getPersonne("Simon", "Balmès");
		System.out.println("p1: " + p1);
		Personne p2 = new Personne("Simon", "Balmès");
		System.out.println("p2: " + p2);
	}

}
