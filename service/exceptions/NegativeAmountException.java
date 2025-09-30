package gr.aueb.cf.ch18.bankapp.service.exceptions;

/**
 * Custom exception που δηλώνει ότι το ποσό που παρέχεται για κατάθεση ή ανάληψη
 * είναι αρνητικό.
 *
 * Χρησιμοποιείται στο service layer κατά την εκτέλεση των λειτουργιών deposit και withdraw.
 */
 public class NegativeAmountException extends Exception{
    /** Serial version UID για serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με προκαθορισμένο μήνυμα.
     */
    public NegativeAmountException() {
        super("Amount negative");
    }


    /**
     * Δημιουργεί εξαίρεση με βάση το αρνητικό ποσό που παρέχεται.
     *
     * @param amount το αρνητικό ποσό
     */
    public NegativeAmountException(double amount) {
        super("Amount: " + amount + " is negative.");
    }
}
