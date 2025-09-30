package gr.aueb.cf.ch18.bankapp.dto;

import gr.aueb.cf.ch18.bankapp.model.User;

/**
 * Data Transfer Object (DTO) για την εισαγωγή νέου
 * χρήστη ({@link User}) στο σύστημα.
 *
 * Περιέχει τα βασικά στοιχεία που απαιτούνται για δημιουργία νέου χρήστη:
 * firstname, lastname και SSN.
 * Χρησιμοποιείται κατά τη δημιουργία λογαριασμού (AccountInsertDTO).
 */
public class UserInsertDTO{
    private String firstname;
    private String lastname;
    private String ssn;

    /**
     * Default Constructor
     */
    public UserInsertDTO() {

    }

    /**
     * Overloaded Constructor
     * @param firstname το όνομα του χρήστη
     * @param lastname  το επώνυμο του χρήστη
     * @param ssn   ο αριθμός κοινωνικής ασφάλισης του χρήστη
     */
    public UserInsertDTO(String firstname, String lastname, String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    /** Επιστρέφει το όνομα του χρήστη. */
    public String getFirstname() {
        return firstname;
    }

    /** Θέτει το όνομα του χρήστη. */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /** Επιστρέφει το επώνυμο του χρήστη. */
    public String getLastname() {
        return lastname;
    }

    /** Θέτει το επώνυμο του χρήστη. */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /** Επιστρέφει τον αριθμό κοινωνικής ασφάλισης (SSN). */
    public String getSsn() {
        return ssn;
    }

    /** Θέτει τον αριθμό κοινωνικής ασφάλισης (SSN). */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
