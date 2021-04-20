import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SonglisteGUI extends JFrame {
    Songliste liste = new Songliste(20);
    Song song = new Song();
    boolean neuModus;

    // Labels
    private JLabel lTitel = null;
    private JLabel lInterpret = null;
    private JLabel lAlbum = null;
    private JLabel lJahr = null;

    // TextFields
    private JTextField tTitel = null;
    private JTextField tInterpret = null;
    private JTextField tAlbum = null;
    private JTextField tJahr = null;

    // Buttons
    private JButton bErster = null;
    private JButton bVoriger = null;
    private JButton bNaechster = null;
    private JButton bLetzter = null;
    private JButton bNeu = null;
    private JButton bLoeschen = null;
    private JButton bAlleLoeschen = null;

    public SonglisteGUI() {
        liste.setPfad("/home/massimiliano/git/Temarbeit_Songliste/TeamarbeitSongliste/src/tracklist.csv");
        liste.lesenSongs();

        setTitle("Songliste");
        setBounds(10, 50, 550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Komponenten anlegen

        // Länge Label
        int width = 100;
        // Größe Label
        int height = 25;
        // xPosition für Labels
        int x = 15;

        // Labels
        lTitel = new JLabel("Titel: ");
        lTitel.setBounds(x, 30, width, height);
        lInterpret = new JLabel("Interpret: ");
        lInterpret.setBounds(x, 60, width, height);
        lAlbum = new JLabel("Album: ");
        lAlbum.setBounds(x, 90, width, height);
        lJahr = new JLabel("Jahr: ");
        lJahr.setBounds(x, 120, width, height);

        // Länge TextField
        width = 400;
        // xPosition für TextFields
        x = 115;

        // TextFields
        tTitel = new JTextField();
        tTitel.setBounds(x, 30, width, height);
        tInterpret = new JTextField();
        tInterpret.setBounds(x, 60, width, height);
        tAlbum = new JTextField();
        tAlbum.setBounds(x, 90, width, height);

        // Länge TextField Jahr
        width = 70;

        tJahr = new JTextField();
        tJahr.setBounds(x, 120, width, height);

        // Länge Buttons obere Reihe
        width = 125;
        // Größe Buttons obere Reihe
        height = 35;
        // yPostion Buttons obere Reihe
        int y = 155;

        // Buttons obere Reihe
        bErster = new JButton();
        bErster.setText("Erster");
        bErster.setBounds(15, y, width, height);

        bVoriger = new JButton();
        bVoriger.setText("Voriger");
        bVoriger.setBounds(15 + width, y, width, height);

        bNaechster = new JButton();
        bNaechster.setText("Nächster");
        bNaechster.setBounds(15 + width * 2, y, width, height);

        bLetzter = new JButton();
        bLetzter.setText("Letzter");
        bLetzter.setBounds(15 + width * 3, y, width, height);

        // Länge Buttons untere Reihe
        width = 167;
        // yPosition Buttons untere Reihe
        y = 195;

        // Buttons untere Reihe
        bNeu = new JButton();
        bNeu.setText("Neu");
        bNeu.setBounds(15, y, width, height);

        bLoeschen = new JButton();
        bLoeschen.setText("Löschen");
        bLoeschen.setBounds(15 + width, y, width, height);

        bAlleLoeschen = new JButton();
        bAlleLoeschen.setText("Alle Löschen");
        bAlleLoeschen.setBounds(15 + width * 2, y, width, height);

        // Komponenten zum Fenster fügen
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(lTitel);
        contentPane.add(lInterpret);
        contentPane.add(lAlbum);
        contentPane.add(lJahr);
        contentPane.add(tTitel);
        contentPane.add(tInterpret);
        contentPane.add(tAlbum);
        contentPane.add(tJahr);
        contentPane.add(bErster);
        contentPane.add(bVoriger);
        contentPane.add(bNaechster);
        contentPane.add(bLetzter);
        contentPane.add(bNeu);
        contentPane.add(bLoeschen);
        contentPane.add(bAlleLoeschen);

        setVisible(true);

        // Ereignis: Knopf drücken
        bErster.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    song = liste.getErster();
                    tTitel.setText(song.getTitel());
                    tInterpret.setText(song.getInterpret());
                    tAlbum.setText(song.getAlbum());
                    tJahr.setText(String.valueOf(song.getErscheinungsjahr()));
                } catch (NumberFormatException e) {
                    tTitel.setText("Fehler");
                    tInterpret.setText("Fehler");
                    tAlbum.setText("Fehler");
                    tJahr.setText("Fehler");
                }
            }

        });
    }
}