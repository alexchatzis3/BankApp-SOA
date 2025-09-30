package gr.aueb.cf.ch18.bankapp.dto;

import gr.aueb.cf.ch18.bankapp.model.Account;
import gr.aueb.cf.ch18.bankapp.model.User;

/**
 * Data Transfer Object (DTO) μόνο για ανάγνωση (Read-Only) για λογαριασμό ({@link Account}).
 *
 * Περιέχει τον IBAN, τον κάτοχο ως {@link UserReadOnlyDTO} και το υπόλοιπο του λογαριασμού.
 * Κληρονομεί το UUID από το {@link BaseDTO}.
 * Δεν παρέχει setters ώστε να διατηρείται η αμεταβλητότητα των δεδομένων.
 *
 * Χρησιμοποιείται κυρίως κατά την επιστροφή δεδομένων στον χρήστη ή UI layer.
 */
 public class AccountReadOnlyDTO extends BaseDTO{

    private String iban;
    private UserReadOnlyDTO userReadOnlyDTO;
    private double balance;

    /**
     * Default Constructor.
     */
    public AccountReadOnlyDTO() {

    }

    /**
     * Overloaded constructor.
     * @param uuid  το UUID του λογαριασμού
     * @param iban  ο IBAN του λογαριαμού
     * @param userReadOnlyDTO   τα στοιχεία του κατόχου ως Read-Only DTO
     * @param balance   το υπόλοιπο του λογαριασμού
     */
    public AccountReadOnlyDTO(String uuid,String iban, UserReadOnlyDTO userReadOnlyDTO, double balance) {
        setUuid(uuid);
        this.iban = iban;
        this.userReadOnlyDTO = userReadOnlyDTO;
        this.balance = balance;
    }

    /** Επιστρέφει τον IBAN του λογαριασμού. */
    public String getIban() {
        return iban;
    }

    /** Επιστρέφει τα στοιχεία του κατόχου του λογαριασμού. */
    public UserReadOnlyDTO getUserReadOnlyDTO() {
        return userReadOnlyDTO;
    }


    /** Επιστρέφει το υπόλοιπο του λογαριασμού. */
    public double getBalance() {
        return balance;
    }

    /**
     * Επιστρέφει αναπαράσταση του αντικειμένου ως String.
     *
     * @return {@link String} με iban, userReadOnlyDTO και balance
     */
    @Override
    public String toString() {
        return "Account{" +
                "iban='" + iban + '\'' +
                ", User=" + userReadOnlyDTO +
                ", balance=" + balance +
                '}';
    }
}
