

import java.io.*;

public class Songliste {
	// Pfad zu den Csvdateien
	private static final String quelle = "C:\\Users\\Massimiliano\\Documents\\Work\\Java_Schule\\Objekte\\src\\song\\tracklist.csv";
	private static final String ziel = "C:\\Users\\Massimiliano\\Documents\\Work\\Java_Schule\\Objekte\\src\\song\\sortlist.csv";

	public static void main(String[] args) {
		// anzahl der zeilen
		int lines = getLines(quelle);
		// ungeordnete liste
		Song songs[] = liesSongs(quelle, lines);
		// geordnete liste
		Song sortedSongs[] = sort(songs);

		// ausgabe der geordneten liste in der csv-datei
		write(sortedSongs, ziel);

	}

	/**
	 * 
	 * 
	 * @param s    text
	 * @param ziel zieldatei
	 */
	public static void write(Song[] s, String ziel) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ziel));
			for (Song song : s) {
				writer.write(song + "\n");
			}
			// writer.write("\n\n\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht angelegt");
		}
	}

	/**
	 * Sortiert ein Songarray
	 * 
	 * @param s songarray
	 * @return geordnetes Songarray
	 */
	public static Song[] sort(Song[] s) {

		for (int i = 1; i < s.length; i++) {
			while (s[i].compareTo(s[i - 1]) < 0) {
				s = swapSongs(s, i);
				if (i > 1)
					i--;
			}
		}

		return s;
	}

	public static Song[] swapSongs(Song[] s, int index) {
		if (index - 1 >= 0) {
			Song tmp = s[index].clone();
			s[index] = s[index - 1];
			s[index - 1] = tmp.clone();
		}
		return s;
	}

	public static int getLines(String quelle) {
		int ret = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quelle));
			while (reader.readLine() != null)
				ret++;
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		return ret;
	}

	// Zeilenweises Lesen aus einer Datei
	public static Song[] liesSongs(String quelle, int lines) {
		Song[] ret = new Song[lines - 1];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quelle));
			int i = 0;
			String zeile = reader.readLine();
			while (true) {
				zeile = reader.readLine();
				if (zeile == null)
					// Dateiende erkannt
					break;
				else {
					ret[i] = new Song();
					ret[i].setSong(zeile);
				}
				i++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		return ret;
	}

}
