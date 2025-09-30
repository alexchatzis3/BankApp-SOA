package gr.aueb.cf.ch18.bankapp.model;

import java.util.Objects;

/**
 * Κλάση που αναπαριστά έναν χρήστη (User) του συστήματος.
 *
 * Κληρονομεί από την {@link AbstractEntity}, αρα κάθε χρήστης
 * έχει μοναδικό UUID.
 * Περιέχει προσωπικά στοιχεία όπως όνομα, επώνυμο και SSN.
 * Παρέχει copy constructor για ασφαλή αντιγραφή αντικειμένων.
 *
 * Υλοποιεί μεθόδους {@link #equals(Object)} και {@link #hashCode()}
 * για σωστή σύγκριση και χρήση σε συλλογές.
 */
public class User extends AbstractEntity {
    private String firstname;
    private String lastname;
    private String ssn;

    /**
     * Default Constructor
     */
    public User() {

    }

    /**
     * Overloaded constructor
     * @param uuid  το UUID του χρήστη
     * @param firstname το όνομα του χρήστη
     * @param lastname το επώνυμο του χρήστη
     * @param ssn   ο αριθμός κοινωνικής ασφάλισης του χρήστη
     */
    public User(String uuid,String firstname, String lastname, String ssn) {
        this.setUuid(getUuid());
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    /**
     * Copy Constructor
     * Δημιουργεί νέο User, αντιγράφοντας τα πεδία ενός άλλου.
     * @param user το User προς αντιγραφή.
     */
    public User(User user) {
        setUuid(user.getUuid());
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.ssn = user.ssn;
    }

    /**
     * Overloaded constructor.
     * Το UUID δημιουργείται αυτόματα.
     *
     * @param firstname το όνομα
     * @param lastname το επώνυμο
     * @param ssn   ο αριθμός κοινωνικής ασφάλισης
     */
    public User(String firstname, String lastname, String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    /**
     * Επιστρέφει τό όνομα του χρήστη.
     * @return  το όνομα.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Θέτει το όνομα του χρήστη.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Επιστρέφει το επώνυμο του χρήστη.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Θέτει το επώνυμο του χρήστη.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Επιστρέφει τον αριθμό κοινωνικής ασφάλισης (SSN).
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Θέτει τον αριθμό κοινωνικής ασφάλισης (SSN).
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Επιστρέφει αναπαράσταση του χρήστη σε μορφή String.
     * Περιλαμβάνει firstname, lastname και ssn.
     */
    @Override
    public String toString() {
        return "User{" +
                ". firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }

    /**
     * Ελέγχει αν δύο αντικείμενα User είναι ίσα.
     * Σύγκριση γίνεται βάσει firstname, lastname και ssn.
     *
     * @param o το αντικείμενο προς σύγκριση
     * @return  true αν τα αντικείμενα είναι ίσα, αλλιώς false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(ssn, user.ssn);
    }

    /**
     * Υπολογίζει τον hash code βασει firstname, lastname και ssn.
     *
     * @return ο hash code του χρήστη
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, ssn);
    }
}
