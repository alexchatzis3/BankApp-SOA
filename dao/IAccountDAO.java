package gr.aueb.cf.ch18.bankapp.dao;

import gr.aueb.cf.ch18.bankapp.model.Account;

import java.util.List;
/**
 * Interface για την πρόσβαση και διαχείριση λογαριασμών ({@link Account})
 * στο DAO layer.
 *
 * Παρέχει βασικές CRUD λειτουργίες καθώς και μεθόδους ελέγχου ύπαρξης
 * λογαριασμών βάσει UUID, SSN ή IBAN.
 *
 * Υλοποιείται από την κλάση {@link AccountDAOImpl}.
 */
public interface IAccountDAO {

    /**
     * Εισάγει νέο λογαριασμό στη βάση δεδομένων ή μνήμη.
     *
     * @param account ο λογαριασμός προς εισαγωγή
     * @return ο εισαχθείς λογαριασμός
     */
    Account insert(Account account);

    /**
     * Ενημερώνει έναν υπάρχοντα λογαριασμό.
     *
     * @param uuid το UUID του λογαριασμού που θα ενημερωθεί
     * @param account τα νέα δεδομένα του λογαριασμού
     * @return ο παλιός λογαριασμός πριν την ενημέρωση
     */
    Account update(String uuid, Account account);


    /**
     * Διαγράφει έναν λογαριασμό βάσει UUID.
     *
     * @param uuid το UUID του λογαριασμού προς διαγραφή
     */
    void delete(String uuid);

    /**
     * Επιστρέφει έναν λογαριασμό βάσει UUID.
     *
     * @param uuid το UUID του λογαριασμού
     * @return ο λογαριασμός αν υπάρχει, αλλιώς null
     */
    Account get(String uuid);

    /**
     * Επιστρέφει όλους τους λογαριασμούς.
     *
     * @return λίστα με όλους τους λογαριασμούς
     */
    List<Account> getAll();

    /**
     * Ελέγχει αν υπάρχει λογαριασμός με συγκεκριμένο UUID.
     *
     * @param uuid το UUID προς έλεγχο
     * @return true αν υπάρχει, false αλλιώς
     */
    boolean uuidExists(String uuid);

    /**
     * Ελέγχει αν υπάρχει λογαριασμός με συγκεκριμένο SSN.
     *
     * @param ssn ο αριθμός κοινωνικής ασφάλισης προς έλεγχο
     * @return true αν υπάρχει, false αλλιώς
     */
    boolean ssnExists(String ssn);

    /**
     * Ελέγχει αν υπάρχει λογαριασμός με συγκεκριμένο IBAN.
     *
     * @param iban ο IBAN προς έλεγχο
     * @return true αν υπάρχει, false αλλιώς
     */
    boolean ibanExists(String iban);

}
