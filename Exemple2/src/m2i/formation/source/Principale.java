package m2i.formation.source;

import java.util.ListIterator;
import java.util.Vector;

public class Principale {

	public static void main(String[] args) {
		float[] notes = new float[5];// { 12.5f, 1.5f, 32.5f, 105, 12.56f };
//		notes[0] = 12.5f;
//		notes[1] = 1.5f;
//		notes[2] = 32.5f;
//		notes[3] = 105f;
//		notes[4] = 12.56f;
//		float[] notes = new float[]{ 12.5f, 1.5f, 32.5f, 105, 12.56f };

		for (int i = 0; i < notes.length; i++) {
			notes[i] = (float) Math.random() * 20;
		}

		for (int i = 0; i < notes.length; i++) {
			System.out.println("notes[" + i + "] = " + notes[i]);
		}

		// Moyenne
		float somme = 0;
		for (float f : notes)
			somme += f;
		System.out.println("Moyenne : " + somme / notes.length);

		// Cutoff
		float thresh = 14.5f;
		System.out.println("Valeurs > " + thresh + " :");
		for (float f : notes)
			if (f > thresh)
				System.out.print(f + " ");
		System.out.println();

		// Inversion
		System.out.println("Inverion");
		for (int i = 0; i < notes.length; i++) {
			System.out.print(notes[notes.length - i - 1] + " ");
		}
		System.out.println();
		// with iterators
		Vector<Float> arr = new Vector<Float>(notes.length);
		for (float f : notes) {
			arr.add(f);
		}
		for (ListIterator<Float> it = arr.listIterator(arr.size()); it.hasPrevious();) {
			System.out.print(it.previous() + " ");
		}
		System.out.println("\n" + arr);
		MyInt x = new MyInt((int) (Math.random() * 10)), y = new MyInt((int) (Math.random() * 10));
		System.out.println("x=" + x + " y=" + y);
		System.out.println("Permutation:");
		Permute(x, y);
		System.out.println("x=" + x + " y=" + y);
	}

	public static int Somme(int x, int y) {
		return x + y;
	}

	private static class MyInt {
		int val;

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		public MyInt(int x) {
			val = x;
		}

		public String toString() {
			return "" + val;
		}
	}

	public static void Permute(MyInt x, MyInt y) {
		x.setVal(x.getVal() + y.getVal());
		y.setVal(x.getVal() - y.getVal());
		x.setVal(x.getVal() - y.getVal());
		System.out.println("x=" + x + " y=" + y);
	}
}
