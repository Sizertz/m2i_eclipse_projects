package m2i.formation.java;

import java.time.LocalDate;
import java.util.ArrayList;

import balmes.m2i.ecole.dao.DAO_Eleve;
import balmes.m2i.ecole.dao.DAO_Note;

public class Principale {

	public static void main(String[] args) {
		// test1();
		testEleveDAO();
		testNoteDAO();
	}

	public static void testNoteDAO() {
		DAO_Note daon = new DAO_Note();
		Note note4 = new Note(4, 12f, Matiere.MATHS, 2, LocalDate.of(2019, 3, 25));
		daon.create(note4);

		System.out.println("DAO_Note.Retrieve(4) : " + daon.retrieve(4));
		System.out.println("DAO_Note.Retrieve(100) : " + daon.retrieve(100));

	}

	public static void testNoteDAODelete() {
		DAO_Note daon = new DAO_Note();
		daon.delete(4);
		daon.delete(100);
	}

	public static void testEleveDAO() {
		DAO_Eleve daoe = new DAO_Eleve();
		Eleve e1 = new Eleve("Nguyen", "Minh Tuan", new ArrayList<Note>(), new ArrayList<Matiere>(), 1, 1);
		e1.addNote(new Note(3, 12f, Matiere.MATHS, 2, LocalDate.of(2019, 3, 25)));
		daoe.create(e1);

		System.out.println("Retrieve eleve 1 : " + daoe.retrieve(1));

		e1.addNote(new Note(2, 12f, Matiere.MATHS, 2, LocalDate.of(2019, 3, 25)));
		e1.setNom("Dutrou");
		daoe.update(e1);

	}

	public static void testEleveDAODelete() {
		DAO_Eleve daoe = new DAO_Eleve();
		daoe.delete(1);
		daoe.delete(100);
	}

	public static void test1() {

		Ecole ecole1 = new Ecole("St Exupery");
		Classe classe1 = new Classe("S001");
		Eleve eleve1 = new Eleve("Nguyen", "Minh Tuan", new ArrayList<Note>(), new ArrayList<Matiere>(), 1, 1);
		classe1.addEleve(eleve1);

		Note note1 = new Note(1, 15.00f, Matiere.ANGLAIS, 1, LocalDate.of(2019, 3, 25));
		Note note2 = new Note(2, 17.00f, Matiere.ANGLAIS, 1, LocalDate.of(2019, 3, 25));
		Note note3 = new Note(3, 5.00f, Matiere.JAVA, 1, LocalDate.of(2019, 3, 25));

		eleve1.addMatiere(Matiere.ANGLAIS);
		eleve1.addMatiere(Matiere.JAVA);
		eleve1.addNote(note1);
		eleve1.addNote(note2);
		eleve1.addNote(note3);

		System.out.println("la moyenne de l'eleve 1" + eleve1.moyenne());

		System.out.println(classe1);

		ecole1.addClasse(classe1);
		System.out.println(ecole1);

		System.out.println("Moyenne de eleve1 en anglais " + eleve1.moyenneParMatiere(Matiere.ANGLAIS));
		System.out.println("Moyenne de eleve1 en java " + eleve1.moyenneParMatiere(Matiere.JAVA));
		System.out.println("Moyenne de eleve1 en français " + eleve1.moyenneParMatiere(Matiere.FRANCAIS));

		Eleve e2 = new Eleve("Lennon", "Bob", new ArrayList<Note>(), new ArrayList<Matiere>(), 2, 1);
		Note note4, note5, note6;
		note4 = new Note(4, 12f, Matiere.MATHS, 2, LocalDate.of(2019, 3, 25));
		note5 = new Note(5, 10f, Matiere.ANGLAIS, 2, LocalDate.of(2019, 3, 25));
		note6 = new Note(6, 19.5f, Matiere.JAVA, 2, LocalDate.of(2019, 3, 25));
		e2.addNote(note4);
		e2.addNote(note5);
		e2.addNote(note6);

		classe1.addEleve(e2);

		System.out.println("Eleve 2 " + e2);

		System.out.println("Moyenne générale de la classe " + classe1.moyenne());
		System.out.println("Moyenne de la classe en anglais " + classe1.moyenneParMatiere(Matiere.ANGLAIS));

		Classement classement1 = classe1.classementGeneral();

		System.out.println("Classement 1 général");
		System.out.println(classement1);

		e2.addNote(note4);
		e2.addNote(new Note(7, 8.5f, Matiere.JAVA, 2, LocalDate.of(2019, 3, 25)));

		Classement classement2 = classe1.classementGeneral();
		System.out.println("Classement 2 général");
		System.out.println(classement2);
		System.out.println("Classement 1 général");
		System.out.println(classement1);

		Classement classement3 = classe1.classementParMatiere(Matiere.ANGLAIS);
		System.out.println("Classement 3 en Anglais");
		System.out.println(classement3);

		Classement classementFiltre;
		NoteFilter<Note> filter = (Note n) -> n.getMatiere() == Matiere.ANGLAIS || n.getMatiere() == Matiere.JAVA;

		NoteFilter<Note> filter2 = (Note n) -> n.getMatiere() == Matiere.ANGLAIS && n.getValeur() < 0;

		class Args {
			int x;
			Object o;
		}
		Args a = new Args();
		a.x = 0;
		a.o = note1;
		System.out.println("a.o " + a.o + " " + Integer.toHexString((a.o.hashCode())));

		NoteFilter<Args> filter3 = new NoteFilter<Args>() {

			@Override
			public boolean filter(Args t) {
				t.x = 1;
				t.o = 3;
				return t.o.hashCode() == t.x;
			}

		};

		classementFiltre = classe1.classementFiltre(filter);
	}

}
