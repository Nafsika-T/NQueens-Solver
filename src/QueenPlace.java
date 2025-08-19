
//Η κλάση QueenPlace επεκτείνει τη Thread και κάνει override την run

class QueenPlace extends Thread {
    NQueens nQueens;
    int[] positions;        //λίστα που περιέχει τις τοποθετήσεις των βασιλισσών μιας λύσης που έχουν βρεθεί μέχρι στιγμής  (των προηγούμενων γραμμών δηλαδή)
    int row;

    public QueenPlace(NQueens nQueens, int[] positions, int row) {
        this.nQueens = nQueens;
        this.positions = positions;
        this.row = row;
    }

    @Override
    public void run() {
        if (row == nQueens.size) {                   // Αν όλες οι βασίλισσες έχουν τοποθετηθεί τότε
            nQueens.addSolution(positions);          // η λύση προστίθεται στο σύνολο των λύσεων
            return;
        }
                                                                //αλλιώς
        for (int i = 0; i < nQueens.size; i++) {                //δοκιμάζονται όλες οι θέσεις στη γραμμή
            int[] newPositions = positions.clone();             //δημιουργείται νέος πίνακας με τις τοποθετήσεις των βασιλισσών των προηγούμενων γραμμών
            newPositions[row] = i;                              //και μια τοποθέτηση της βασίλισσας της γραμμής που θέλουμε να βρούμε μια έγκυρη τοποθέτησή της
            if (nQueens.isValid(newPositions, row)) {           //και αν η τοποθέτηση είναι έγκυρη τότε
                QueenPlace nextStep = new QueenPlace(nQueens, newPositions, row + 1);         //καλεί αναδρομικά τη run για να τοποθετήσει την επόμενη βασίλισσα
                nextStep.run();
            }
        }
    }
}

