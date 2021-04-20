
import java.io.*;

public class Songliste {

	// liste von Songs
	private Song[] songs;
	// nummer vom Aktuellen song
	private int nummerAktueller;
	// anzahl der Songs
	private int anzahl;
	// pfad der csv-Datei
	private String pfad;

	/**
	 * Inizialisiert das Objekt Songliste
	 * 
	 * @param max_anzahl
	 */
	public Songliste(int max_anzahl) {
		this.songs = new Song[max_anzahl];
		this.nummerAktueller = 0;
	}

	/**
	 * 
	 * @return the Song at the current position
	 */
	public Song getAktueller() {
		return this.songs[this.nummerAktueller];
	}

	/**
	 * 
	 * @return the Song at the next position
	 */
	public Song getNaechster() {
		Song ret = this.songs[this.nummerAktueller];
		if (nummerAktueller + 1 < this.songs.length) {
			this.nummerAktueller++;
			ret = this.songs[this.nummerAktueller];
		}
		return ret;
	}

	/**
	 * 
	 * @return the Song at the previus position
	 */
	public Song getVorherigen() {
		Song ret = this.songs[this.nummerAktueller];
		if (this.nummerAktueller - 1 >= 0) {
			this.nummerAktueller--;
			ret = this.songs[this.nummerAktueller];
		}
		return ret;
	}

	/**
	 * 
	 * @return the first Song
	 */
	public Song getErster() {
		this.nummerAktueller = 0;
		return this.songs[0];
	}

	/**
	 * 
	 * @return the last song in the list
	 */
	public Song getLetzter() {
		this.nummerAktueller = anzahl - 1;
		return this.songs[this.anzahl - 1];
	}

	/**
	 * add a new Song at he last position of the list
	 * 
	 * @param s new song`
	 */
	public void anfuegenNeuen(Song s) {
		if (this.anzahl < this.songs.length) {
			this.songs[anzahl] = s;
			this.nummerAktueller = this.anzahl;
			this.anzahl++;
		}
	}

	/**
	 * Changes the number of the current song to the song s
	 * 
	 * @param s new current song
	 */
	public Song aendernAktuellen(Song s) {
		Song ret = songs[nummerAktueller];
		int i = 0;
		while (songs[i].compareTo(s) == 0 || i == anzahl - 1) {
			i++;
		}
		if (i != anzahl - 1) {
			nummerAktueller = i;
			ret = songs[nummerAktueller];
		}
		return ret;
	}

	/**
	 * delite the current song
	 * 
	 * @return the next song
	 */
	public Song loeschenAktuellen() {
		for (int i = nummerAktueller; i < anzahl - 1; i++)
			songs[i] = getNaechster();
		anzahl--;
		songs[anzahl] = null;
		return songs[nummerAktueller];
	}

	/**
	 * removes all songs in the objekt songlist
	 */
	public void loeschenAlle() {
		for (int i = 0; i < anzahl; i++) {
			songs[i] = null;
		}
		anzahl = 0;
	}

	/**
	 * reads songs from a file 
	 */
	public void lesenSongs() {
		songs = readSongs(pfad, getLines(pfad));
	}

	/**
	 * schreibt alle songs in eine csv datei
	 */
	public void schreibenSongs() {
		write(songs, pfad);
	}

	// geters und setters
	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	/**
	 * writes the song in the file with the paht ziel
	 * 
	 * @param s    text
	 * @param ziel zieldatei
	 */
	public static void write(Song[] s, String ziel) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ziel));
			for (Song song : s) {
				writer.write(song.toString() + "\n");
			}
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

	/**
	 * Swap the song at index "index" with the previous song
	 * 
	 * @param s Songlist
	 * @param index 
	 * @return A list of Songs with swapped Songs
	 */
	public static Song[] swapSongs(Song[] s, int index) {
		if (index - 1 >= 0) {
			Song tmp = s[index].clone();
			s[index] = s[index - 1];
			s[index - 1] = tmp.clone();
		}
		return s;
	}

	/**
	 * 
	 * @param quelle path of document
	 * @return the number of lines in a document
	 */
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

	/**
	 * 
	 * @param quelle
	 * @param lines
	 * @return
	 */
	public static Song[] readSongs(String quelle, int lines) {
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
		}
		// Fehler
		catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		return ret;
	}
}