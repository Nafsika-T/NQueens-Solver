import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;


public class Draw {

    //Η μέθοδος menu σχεδιάζει το menu. Ζητείται από το χρήστη να πληκτρολογήσει το μέγεθος της σκακιέρας (μόνο αριθμό γραμμών/στηλών Ν). Σην περίπτωση μη έγκυρης τιμής εμφανίζεται κατάλληλο μύνημα.
    //Σε περίπτωση έγκυρης τιμής εμφανίζεται μύνημα επιβεβαίωσης. Αν ο χρήστης επιβεβαιώσει την επιλογή του τότε καλείτε η μέθοδος DrawChessBoard.
    public void menu() throws InterruptedException{
        JFrame frame = new JFrame();
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel label = new JLabel("Μέγεθος σκακιέρας: ");
        JTextField text = new JTextField(5);
        menu.add(label);
        menu.add(text);
        JButton OK = new JButton("OK");
        menu.add(OK);
        frame.add(menu, BorderLayout.WEST);
        menu.setVisible(true);
        frame.setVisible(true);

        OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value;
                String size = text.getText();
                try {
                    value = Integer.parseInt(size);
                    int result = JOptionPane.showConfirmDialog(null,"Σκακιέρας μεγέθους: "+size+" x "+size+"\nΕπιβεβαίωση?","Επιβαιβέωση",JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            DrawChessBoard(value);
                        } catch (InterruptedException ex) {
                            System.out.println("An error occurred: " + ex.getMessage());
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Μη έγκυρη τιμή, πληκτρολογήστε τον αριθμό των γραμμών");
                }
            }
        });
    }

    //Η μέθοδος DrawChessBoard δέχεται ως είσοδο το μέγεθος της σκακιέρας (Ν) και για κάθε λύση που βρέθηκε (αν οι λύσεις είναι > από 100 τότε μόνο για τις 100 πρώτες) δημιουργεί ένα νήμα
    //και του αναθέτει τη σχεδίαση μιας λύσης δημιουργώντας ένα αντικείμενο της DrawChessboardThread η οποία επεκτείνει τη κλάση Thread
    public void DrawChessBoard(int size) throws InterruptedException
    {

        NQueens nQueens = new NQueens(size);
        nQueens.solve();
        int l=0;
        for (int i=0; i<nQueens.solutions.size(); i++){
            if(l<100) {
                Thread t = new DrawChessboardThread(nQueens.solutions.get(i), size);
                t.start();
                l++;
            }
        }

        System.out.println("Total solutions: " + nQueens.solutions.size());

    }


    //Η κλάση DrawChessboardThread επεκτείνει τη κλάση Thread και κάνει Override τη run
    static class DrawChessboardThread extends Thread {
        protected int [] positions;
        protected int size;

        //ο κατασκευαστής δέχεται και αποθηκεύει μια λύση του προβλήματος και το μέγεθος της σκακιέρας
        public DrawChessboardThread(int [] positions,int size) {
            this.positions=positions;
            this.size=size;
        }

                                                                                //η μέθοδος run:
        @Override
        public void run() {
            JFrame frame = new JFrame();                                       //κατασκευάζει ένα πλαίσιο
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Chessboard c=new Chessboard(positions,size);                       //δημιουργεί ένα αντικείμενο Chessboard για τη σχεδίαση της σκακιέρας μιας λύσης
            frame.add(c);                                                      //και το προσθέτει στο πλαίσιο
            frame.setVisible(true);
        }
    }
}
