package gr.aueb.cf.ch18.bankapp.service.exceptions;

import gr.aueb.cf.ch18.bankapp.model.Account;

/**
 * Custom exception που δηλώνει ότι ένας λογαριασμός (Account) υπάρχει ήδη.
 *
 * Μπορεί να δημιουργηθεί είτε με βάση τον IBAN είτε με βάση ένα αντικείμενο {@link Account}.
 * Χρησιμοποιείται στο service layer κατά την εισαγωγή ή ενημέρωση λογαριασμών,
 * για να αποτραπεί η δημιουργία διπλών λογαριασμών.
 */
 public class DuplicateAccountException extends Exception{
    /** Serial version UID για serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με βάση τον IBAN του λογαριασμού.
     *
     * @param iban ο IBAN που υπάρχει ήδη
     */
    public DuplicateAccountException(String iban) {
        super("Account with iban: " + iban + " already exists.");
    }

    /**
     * Δημιουργεί εξαίρεση με βάση ένα αντικείμενο {@link Account}.
     *
     * @param account ο λογαριασμός που υπάρχει ήδη
     */
    public DuplicateAccountException(Account account) {
        super("Account with uuid: " + account.getUuid() +
                " and iban: " + account.getIban() +
                "already exists.");
    }
}
