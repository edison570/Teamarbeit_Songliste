

import java.io.*;

public class Songliste {

	private Song[] songs;
	private int nummerAktueller;
	private int anzahl;
	private String pfad;


	public Songliste(int max_anzahl) {
		this.songs = new Song[max_anzahl];
		this.nummerAktueller = 0;
	}

}
