package gr.aueb.cf.ch18.bankapp.dto;

import gr.aueb.cf.ch18.bankapp.model.User;

/**
 * Data Transfer Object (DTO) για ενημέρωση (update) στοιχείων χρήστη ({@link User}).
 *
 * Περιέχει το UUID του χρήστη, καθώς και τα πεδία firstname, lastname και SSN.
 * Παρέχει getters και setters για όλα τα πεδία.
 *
 * Χρησιμοποιείται από το service layer κατά την ενημέρωση ενός χρήστη.
 */
 public class UserUpdateDTO extends BaseDTO{
    private String firstname;
    private String lastname;
    private String ssn;

    /**
     * Default Constructor.
     */
    public UserUpdateDTO() {

    }

    /**
     * Overloaded Constructor.
     * @param uuid  το UUID του χρήστη
     * @param firstname το όνομα του χρήστη
     * @param lastname  το επώνυμο του χρήστη
     * @param ssn   ο αριθμός κοινωνικής ασφάλισης του χρήστη
     */
    public UserUpdateDTO(String uuid,String firstname, String lastname, String ssn) {
        setUuid(uuid);
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
