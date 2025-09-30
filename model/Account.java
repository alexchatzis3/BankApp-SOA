package gr.aueb.cf.ch18.bankapp.model;

import java.util.Objects;
import java.util.Optional;

/**
 * Κλάση που αναπαριστά έναν τραπεζικό λογαριασμό (Account).
 *
 * Κληρονομεί από την {@link AbstractEntity}, αρα κάθε λογαριασμός
 * έχει μοναδικό UUID.
 * Περιέχει IBAN, τον κάτοχο του λογαριασμού ({@link User}) και
 * υπόλοιπο.
 *
 * Παρέχει copy constructor για ασφαλή αντιγραφή αντικειμένων.
 */
public class Account extends AbstractEntity{
    private String iban;
    private User holder;
    private double balance;

    /**
     * Default Constructor.
     */
    public Account() {

    }

    /**
     * Overloaded Constructor.
     * @param iban  ο ΙΒΑΝ του λογαριασμού
     * @param holder    ο κάτοχος του λογαριασμού
     * @param balance   το αρχικό υπόλοιπο
     */
    public Account(String iban, User holder, double balance) {
        this.iban = iban;
        this.holder = new User(holder);
        this.balance = balance;
    }

    /**
     * Copy constructor.
     * Δημιουργεί ένα νέο Account αντιγράφοντας τα πεδία ενός άλλου Account.
     *
     * @param account ο λογαριασμός προς αντιγραφή
     */
    public Account(Account account) {
        this.setUuid(account.getUuid());
        this.iban = account.iban;
        this.holder = new User(account.holder);
        this.balance = account.balance;
    }

    /** Επιστρέφει τον IBAN του λογαριασμού. */
    public String getIban() {
        return iban;
    }

    /** Θέτει τον IBAN του λογαριασμού. */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /** Επιστρέφει τον κάτοχο του λογαριασμού. */
    public User getHolder() {
        return new User(holder);
    }

    /** Θέτει τον κάτοχο του λογαριασμού. */
    public void setHolder(User holder) {
        this.holder = new User(holder);
    }

    /** Επιστρέφει το υπόλοιπο του λογαριασμού. */
    public double getBalance() {
        return balance;
    }

    /** Θέτει το υπόλοιπο του λογαριασμού. */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Ελέγχει αν δύο αντικείμενα Account είναι ίσα.
     * Σύγκριση γίνεται βάσει IBAN, holder και balance.
     *
     * @param o το αντικείμενο προς σύγκριση
     * @return true αν τα αντικείμενα είναι ίσα, false αλλιώς
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && Objects.equals(iban, account.iban) && Objects.equals(holder, account.holder);
    }

    /**
     * Υπολογίζει τον hashCode βάσει IBAN, holder και balance.
     *
     * @return ο hash code του λογαριασμού
     */
    @Override
    public int hashCode() {
        return Objects.hash(iban, holder, balance);
    }

    /**
     * Επιστρέφει αναπαράσταση του λογαριασμού σε μορφή String.
     * Περιλαμβάνει UUID, IBAN, holder και balance.
     */
    @Override
    public String toString() {
        return "Account{" +
                "uuid=" + getUuid() +
                ". iban='" + iban + '\'' +
                ", holder=" + holder +
                ", balance=" + balance +
                '}';
    }

}
