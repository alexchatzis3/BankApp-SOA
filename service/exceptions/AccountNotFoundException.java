package gr.aueb.cf.ch18.bankapp.service.exceptions;

import gr.aueb.cf.ch18.bankapp.model.Account;

/**
 * Custom exception που δηλώνει ότι ένας λογαριασμός (Account) δεν βρέθηκε.
 *
 * Μπορεί να δημιουργηθεί είτε με βάση τον IBAN είτε με βάση ένα αντικείμενο {@link Account}.
 * Χρησιμοποιείται στο service layer κατά την αναζήτηση ή ενημέρωση λογαριασμών.
 */

 public class AccountNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με βάση τον IBAN του λογαριασμού.
     *
     * @param iban ο IBAN που δεν βρέθηκε
     */
    public AccountNotFoundException(String iban) {
        super("Account with iban: " + iban + " was not found.");
    }

    /**
     * Δημιουργεί εξαίρεση με βάση ένα αντικείμενο {@link Account}.
     *
     * @param account ο λογαριασμός που δεν βρέθηκε
     */
    public AccountNotFoundException(Account account) {
        super("Account with uuid: " + account.getUuid() +
        " and iban: " + account.getIban());
    }
}
