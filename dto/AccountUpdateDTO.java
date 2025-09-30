package gr.aueb.cf.ch18.bankapp.dto;

import gr.aueb.cf.ch18.bankapp.model.Account;

/**
 * Data Transfer Object (DTO) για ενημέρωση (update) στοιχείων λογαριασμού ({@link Account}).
 *
 * Περιέχει το UUID του λογαριασμού, τον IBAN, τα στοιχεία του κατόχου ως {@link UserUpdateDTO}
 * και το υπόλοιπο του λογαριασμού. Παρέχει getters και setters για όλα τα πεδία.
 *
 * Χρησιμοποιείται από το service layer κατά την ενημέρωση ενός λογαριασμού.
 */
 public class AccountUpdateDTO extends BaseDTO{
    private String iban;
    private UserUpdateDTO userUpdateDTO;
    private double balance;

    /**
     * Default Constructor.
     */
    public AccountUpdateDTO() {

    }

    /**
     * Overloaded Constructor.
     * @param uuid  το UUID του λογαριασμού
     * @param iban  ο IBAN του λογαριαμού
     * @param userUpdateDTO τα στοιχεία του κατόχου του λογαριασμού
     * @param balance   το υπόλοιπο του λογαριασμού
     */
    public AccountUpdateDTO(String uuid, String iban, UserUpdateDTO userUpdateDTO, double balance) {
        setUuid(uuid);
        this.iban = iban;
        this.userUpdateDTO = userUpdateDTO;
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
    public UserUpdateDTO getUserUpdateDTO() {
        return userUpdateDTO;
    }

    /** Θέτει τα στοιχεία του κατόχου του λογαριασμού. */
    public void setUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        this.userUpdateDTO = userUpdateDTO;
    }

    /** Επιστρέφει το υπόλοιπο του λογαριασμού. */
    public double getBalance() {
        return balance;
    }

    /** Θέτει το υπόλοιπο του λογαριασμού. */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
