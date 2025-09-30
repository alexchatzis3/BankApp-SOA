package gr.aueb.cf.ch18.bankapp.service.exceptions;

/**
 * Custom exception που δηλώνει ότι το υπόλοιπο ενός λογαριασμού δεν επαρκεί
 * για να καλύψει ένα αίτημα ανάληψης χρημάτων.
 *
 * Χρησιμοποιείται κυρίως στο service layer κατά την εκτέλεση της λειτουργίας withdraw.
 */
 public class InsufficientBalanceException  extends Exception{
    /** Serial version UID για serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με βάση το υπόλοιπο του λογαριασμού και το ποσό ανάληψης.
     *
     * @param balance το τρέχον υπόλοιπο του λογαριασμού
     * @param amount το ποσό που ζητείται για ανάληψη
     */
    public InsufficientBalanceException(double balance, double amount) {
        super("Insufficient balance: " + balance + " for amount: " + amount);
    }
}
