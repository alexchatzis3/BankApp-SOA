package gr.aueb.cf.ch18.bankapp.dto;

import gr.aueb.cf.ch18.bankapp.model.User;

/**
 * Data Transfer Object (DTO) μόνο για ανάγνωση (Read-Only)
 * για τον χρήστη ({@link User}).
 *
 * Περιέχει τα βασικά στοιχεία του χρήστη: firstname, lastname και SSN,
 * καθώς και το UUID από το {@link BaseDTO}.
 * Δεν παρέχει setters ώστε να διατηρείται η αμεταβλητότητα των δεδομένων.
 *
 * Χρησιμοποιείται κυρίως κατά την επιστροφή δεδομένων
 * στον χρήστη ή UI layer.
 */
public class UserReadOnlyDTO extends BaseDTO{
    private String firstname;
    private String lastname;
    private String ssn;

    /**
     * Default Constructor.
     */
    public UserReadOnlyDTO() {

    }

    /**
     * Overloaded Constructor.
     * @param uuid  το UUID του χρήστη
     * @param firstname το όνομα του χρήστη
     * @param lastname  το επώνυμο του χρήστη
     * @param ssn   ο αριθμός κοινωνικής ασφάλισης του χρήστη
     */
    public UserReadOnlyDTO(String uuid,String firstname, String lastname, String ssn) {
        setUuid(uuid);
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    /** Επιστρέφει το όνομα του χρήστη. */
    public String getFirstname() {
        return firstname;
    }

    /** Επιστρέφει το επώνυμο του χρήστη. */
    public String getLastname() {
        return lastname;
    }

    /** Επιστρέφει τον αριθμό κοινωνικής ασφάλισης (SSN) του χρήστη. */
    public String getSsn() {
        return ssn;
    }

    /**
     * Επιστρέφει αναπαράσταση του αντικειμένου ως String.
     *
     * @return {@link String} με firstname, lastname και ssn
     */
    @Override
    public String toString() {
        return "{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
