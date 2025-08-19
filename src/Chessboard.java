import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

//Η κλάση Chessboard επεκτείνει την κλάση JPanel και κάνει Override την paintComponent
class Chessboard extends JPanel {
    int size;
    int[] positions;

    //Κατασκευαστής που δέχεται μια λίστα που αντιπροσωπεύει μία λύση του προβλήματος και το μέγεθος (Ν) της σκακιέρας και τα αποθηκεύει στις δικές του μεταβλητές
    public Chessboard(int [] positions, int size)
    {
        this.size=size;
        this.positions=positions;
    }

    //Η μέθοδος paintComponent σχεδιάζει μια σκακιέρα με μια λύση του προβλήματος (οι θέσεις των βασιλισσών σχεδιάζονται με κόκκινο χρώμα)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / size;
        int height = getHeight() / size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (((i + j) % 2) == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * width, i * height, width, height);

                for(int k=0; k<positions.length; k++) {
                    if (i  == k && j  == positions[k]) {
                        g.setColor(Color.RED);
                        g.fillRect(j * width, i * height, width, height);
                    }
                }
            }
        }
    }
}

