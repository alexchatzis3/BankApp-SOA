package gr.aueb.cf.ch18.bankapp.service.exceptions;

/**
 * Custom exception που δηλώνει ότι ένας λογαριασμός με συγκεκριμένο IBAN υπάρχει ήδη.
 *
 * Χρησιμοποιείται στο service layer κατά την εισαγωγή ή ενημέρωση λογαριασμών,
 * για να αποτραπεί η δημιουργία διπλών IBANs.
 */
 public class IbanAlreadyExistsException extends Exception{
    /** Serial version UID για serialization. */
    private final static long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με βάση τον IBAN που υπάρχει ήδη.
     *
     * @param iban ο IBAN που υπάρχει ήδη
     */
    public IbanAlreadyExistsException(String iban) {
        super("Account with iban: " + iban + " already exists.");
    }

    /**
     * Δημιουργεί εξαίρεση με προκαθορισμένο μήνυμα.
     */
    public IbanAlreadyExistsException() {
        super("Account with that certain iban already exists.");
    }

}
