package eu.veldsoft.greedy.rods.cutting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	static class Piece implements Comparable<Piece> {
		String label;
		int length;
		int rod;
		int cut;

		@Override
		public int compareTo(Piece piece) {
			return piece.length - this.length;
		}
	}

	private static void greedy(Vector<Piece> pieces) {
		if (pieces.size() <= 0) {
			return;
		}

		int size = pieces.elementAt(0).rod;
		System.out.println();
		System.out.println(pieces.elementAt(0).label);
		do {
			for (int i = 0; i < pieces.size(); i++) {
				Piece piece = pieces.elementAt(i);
				if (piece.length <= size) {
					System.out.print(piece.length - piece.cut);
					System.out.print(" ");
					size -= piece.length;
					pieces.remove(i);
					i--;
				}
			}

			if (pieces.size() > 0 && size < pieces.lastElement().length) {
				System.out.println();
				size = pieces.lastElement().rod;
			}
		} while (pieces.size() > 0);
		System.out.println();
	}

	public static void main(String[] args) {
		/*
		 * Read input data.
		 */
		Scanner in = new Scanner(System.in);
		Map<String, Vector<Piece>> data = new HashMap<String, Vector<Piece>>();
		while (in.hasNextLine() == true) {
			String label = in.next();
			int number = in.nextInt();
			int length = in.nextInt();
			int rod = in.nextInt();
			int cut = in.nextInt();

			rod += cut;
			length += cut;

			for (int n = 0; n < number; n++) {
				Piece piece = new Piece();
				piece.label = label;
				piece.length = length;
				piece.rod = rod;
				piece.cut = cut;

				if (data.containsKey(label) == false) {
					data.put(label, new Vector<Piece>());
				}

				if (piece.length <= piece.rod) {
					data.get(label).add(piece);
				}
			}
		}
		in.close();

		/*
		 * Sort descending data.
		 */
		for (String label : data.keySet()) {
			Collections.sort((Vector) data.get(label));
		}

		/*
		 * Apply greedy algorithm.
		 */
		for (String label : data.keySet()) {
			greedy((Vector) data.get(label));
		}
	}
}
