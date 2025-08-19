import java.util.ArrayList;
import java.util.List;

public class NQueens {
    int size;                            //μέγεθος σκακιέρας (Ν)
    List<int[]> solutions;               //λίστα για την αποθήκευση όλων των λύσεων

    public NQueens(int size) {
        this.size = size;
        solutions = new ArrayList<>();
    }

    //Η μέθοδος solve δημιουργεί Ν νήματα και σε κάθε νήμα αναθέτει τη λύση για κάθε πιθανή θέση της βασίλισσας στη πρώτη γραμμή
    public void solve() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();   //λίστα από threads

        for (int i = 0; i < size; i++) {
            int[] positions = new int[size];         //για κάθε νήμα θα δημιουργείται ένας πίνακας για την αποθήκευση των τοποθετήσεων των βασιλισσών μιας λύσης
            positions[0] = i;                        //σε κάθε νήμα θα δωθεί μια λύση της πρώτης σειράς (διαφορετική για κάθε νήμα)
            QueenPlace place = new QueenPlace(this, positions, 1);   // Δημιουργείται ένα αντικείμενο QueenPlace
            Thread t = new Thread(place);      // Δημιουργείται ένα νέο νήμα με το αντικείμενο QueenPlacer
            threads.add(t);                     //προστίθεται στη λίστα των threads
            t.start();                         //ξεκινά την εκτέλεση του νήματος t καλώντας τη run() μέθοδο
        }

        for (Thread t : threads) {
            t.join();                         //καλείται η μέθοδος join για όλα τα νήματα για να διασφαλιστεί ο συγχρονισμός τους
        }
    }

    //Η μέθοδος isValid δέχεται μια λίστα με τις μέχρι τώρα τοποθετήσεις των βασιλισσών και μια τοποθέτηση τις επόμενης γραμμής που πρέπει να τοποθετηθεί βασίλισσα και τη γραμμή αυτή
    //Αν η τοποθέτηση είναι έγκυρη τότε επιστρέφει true αλλιώς επιστρέφει false.
    public boolean isValid(int[] positions, int row) {
        for (int i = 0; i < row; i++) {
            int diff = Math.abs(positions[i] - positions[row]);
            if (diff == 0 || diff == row - i) {
                return false;
            }
        }
        return true;
    }

    //Η μέθοδος addSolution δέχεται μια λίστα (που αποτελεί μια λύση του προβλήματος των βασιλισσών) και τη προσθέτει στο σύνολο των λύσεων (λίστα solutions)
    public synchronized void addSolution(int[] positions)
    {
        solutions.add(positions);
    }
}


