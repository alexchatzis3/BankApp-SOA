package gr.aueb.cf.ch18.bankapp.dao;

import gr.aueb.cf.ch18.bankapp.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Υλοποίηση του {@link IAccountDAO} που διαχειρίζεται λογαριασμούς σε μνήμη
 * χρησιμοποιώντας μια {@link List}.
 *
 * Παρέχει CRUD λειτουργίες καθώς και μεθόδους ελέγχου ύπαρξης λογαριασμών
 * βάσει UUID, SSN ή IBAN.
 *
 * Σημείωση: Πρόκειται για in-memory υλοποίηση, δεν χρησιμοποιεί πραγματική βάση δεδομένων.
 */
public class AccountDAOImpl implements IAccountDAO{

    /** Λίστα που αποθηκεύει όλους τους λογαριασμούς. */
    private final static List<Account> accounts = new ArrayList<>();

    /**
     * Εισάγει νέο λογαριασμό στη λίστα.
     *
     * @param account ο λογαριασμός προς εισαγωγή
     * @return ο λογαριασμός που εισήχθη
     */
    @Override
    public Account insert(Account account) {
        accounts.add(account);
        return account;
    }

    /**
     * Ενημερώνει έναν υπάρχοντα λογαριασμό βάσει UUID.
     *
     * @param uuid το UUID του λογαριασμού προς ενημέρωση
     * @param account τα νέα δεδομένα του λογαριασμού
     * @return ο παλιός λογαριασμός πριν την ενημέρωση, ή null αν δεν βρέθηκε
     */
    @Override
    public Account update(String uuid, Account account) {
        Optional<Account> optionalAccount = accounts
                .stream()
                .filter(acc -> Objects.equals(acc.getUuid(), uuid))
                .findFirst();

        Account acc = optionalAccount.orElse(null);
        if (acc == null) return null;

        Account accountToReturn = new Account(acc);
        acc.setUuid(account.getUuid());
        acc.setIban(account.getIban());
        acc.setBalance(account.getBalance());
        acc.setHolder(account.getHolder());

        return accountToReturn;
    }

    /**
     * Διαγράφει έναν λογαριασμό βάσει UUID.
     *
     * @param uuid το UUID του λογαριασμού προς διαγραφή
     */
    @Override
    public void delete(String uuid) {
        accounts.removeIf(acc -> acc.getUuid().equals(uuid));
    }

    /**
     * Επιστρέφει έναν λογαριασμό βάσει UUID.
     *
     * @param uuid το UUID του λογαριασμού
     * @return ο λογαριασμός αν υπάρχει, αλλιώς null
     */
    @Override
    public Account get(String uuid) {
        return accounts
                .stream()
                .filter(acc -> acc.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    /**
     * Επιστρέφει όλους τους λογαριασμούς.
     *
     * @return νέα λίστα με όλους τους λογαριασμούς
     */
    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts);
    }

    /**
     * Ελέγχει αν υπάρχει λογαριασμός με συγκεκριμένο UUID.
     *
     * @param uuid το UUID προς έλεγχο
     * @return true αν υπάρχει, false αλλιώς
     */
    @Override
    public boolean uuidExists(String uuid) {
        return accounts
                .stream()
                .anyMatch(acc -> acc.getUuid().equals(uuid));
    }

    /**
     * Ελέγχει αν υπάρχει λογαριασμός με συγκεκριμένο SSN.
     *
     * @param ssn ο αριθμός κοινωνικής ασφάλισης προς έλεγχο
     * @return true αν υπάρχει, false αλλιώς
     */
    @Override
    public boolean ssnExists(String ssn) {
        return accounts
                .stream()
                .anyMatch(acc -> acc.getHolder().getSsn().equals(ssn));
    }

    /**
     * Ελέγχει αν υπάρχει λογαριασμός με συγκεκριμένο IBAN.
     *
     * @param iban ο IBAN προς έλεγχο
     * @return true αν υπάρχει, false αλλιώς
     */
    public boolean ibanExists(String iban) {
        return accounts
                .stream()
                .anyMatch(acc -> acc.getIban().equals(iban));
    }
}
