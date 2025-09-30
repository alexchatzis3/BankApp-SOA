package gr.aueb.cf.ch18.bankapp.dto;

import gr.aueb.cf.ch18.bankapp.model.Account;
import gr.aueb.cf.ch18.bankapp.model.User;

/**
 * Data Transfer Object (DTO) για την εισαγωγή νέου λογαριασμού ({@link Account}).
 *
 * Περιέχει τον IBAN, τον κάτοχο του λογαριασμού ως {@link UserInsertDTO}
 * και το αρχικό υπόλοιπο του λογαριασμού.
 * Χρησιμοποιείται κατά την εισαγωγή ενός νέου λογαριασμού μέσω του service layer.
 */
public class AccountInsertDTO {
    private String iban;
    private UserInsertDTO userInsertDTO;
    private double balance;

    /**
     * Default Constructor.
     */
    public AccountInsertDTO() {

    }

    /**
     * Overloaded Constructor.
     * @param iban  ο IBAN του λογαριασμού
     * @param userInsertDTO τα στοιχεία του κατόχου
     * @param balance   το αρχικό υπόλοιπο του λογαριαμού
     */
    public AccountInsertDTO(String iban, UserInsertDTO userInsertDTO, double balance) {
        this.iban = iban;
        this.userInsertDTO = userInsertDTO;
        this.balance = balance;
    }

    /** Επιστρέφει τον IBAN του λογαριασμού. */
    public String getIban() {
        return iban;
    }

    /** Θέτει τον IBAN του λογαριασμού. */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /** Επιστρέφει τα στοιχεία του κατόχου του λογαριασμού. */
    public UserInsertDTO getUserInsertDTO() {
        return userInsertDTO;
    }

    /** Θέτει τα στοιχεία του κατόχου του λογαριασμού. */
    public void setUserInsertDTO(UserInsertDTO userInsertDTO) {
        this.userInsertDTO = userInsertDTO;
    }

    /** Επιστρέφει το αρχικό υπόλοιπο του λογαριασμού. */
    public double getBalance() {
        return balance;
    }

    /** Θέτει το αρχικό υπόλοιπο του λογαριασμού. */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
