package gr.aueb.cf.ch18.bankapp.service.exceptions;

/**
 * Custom exception που δηλώνει ότι ο αριθμός κοινωνικής ασφάλισης (SSN)
 * που παρέχεται δεν είναι έγκυρος.
 *
 * Χρησιμοποιείται κυρίως στο service layer κατά την εκτέλεση της λειτουργίας withdraw,
 * για να διασφαλιστεί ότι η ανάληψη γίνεται από τον σωστό κάτοχο του λογαριασμού.
 */
 public class SsnNotValidException extends Exception{
    /** Serial version UID για serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με βάση τον μη έγκυρο SSN.
     *
     * @param ssn ο μη έγκυρος αριθμός κοινωνικής ασφάλισης
     */
    public SsnNotValidException(String ssn) {
        super("Ssn: " + ssn + " not valid");
    }
}
