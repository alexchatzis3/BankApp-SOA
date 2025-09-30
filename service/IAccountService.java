package gr.aueb.cf.ch18.bankapp.service;

import gr.aueb.cf.ch18.bankapp.dto.AccountInsertDTO;
import gr.aueb.cf.ch18.bankapp.dto.AccountUpdateDTO;
import gr.aueb.cf.ch18.bankapp.model.Account;
import gr.aueb.cf.ch18.bankapp.service.exceptions.*;

import java.util.List;

/**
 * Interface για τις υπηρεσίες διαχείρισης λογαριασμών (Accounts) στο BankApp.
 *
 * Παρέχει επιχειρησιακή λογική για CRUD λειτουργίες, καθώς και για καταθέσεις και αναλήψεις.
 * Οι μέθοδοι χειρίζονται κατάλληλα exceptions όπως:
 * <ul>
 *   <li>{@link DuplicateAccountException}</li>
 *   <li>{@link AccountNotFoundException}</li>
 *   <li>{@link IbanAlreadyExistsException}</li>
 *   <li>{@link NegativeAmountException}</li>
 *   <li>{@link InsufficientBalanceException}</li>
 *   <li>{@link SsnNotValidException}</li>
 * </ul>
 */
public interface IAccountService {

    /**
     * Εισάγει νέο λογαριασμό με βάση τα δεδομένα του {@link AccountInsertDTO}.
     *
     * @param dto τα δεδομένα του λογαριασμού προς εισαγωγή
     * @return ο εισαχθείς λογαριασμός
     * @throws DuplicateAccountException αν ο λογαριασμός υπάρχει ήδη
     */
    Account insertAccount(AccountInsertDTO dto)
        throws DuplicateAccountException;

    /**
     * Ενημερώνει έναν λογαριασμό με βάση το UUID του και τα δεδομένα του {@link AccountUpdateDTO}.
     *
     * @param uuid το UUID του λογαριασμού προς ενημέρωση
     * @param dto τα νέα δεδομένα του λογαριασμού
     * @return ο ενημερωμένος λογαριασμός
     * @throws AccountNotFoundException αν δεν βρεθεί ο λογαριασμός
     * @throws IbanAlreadyExistsException αν ο νέος IBAN υπάρχει ήδη σε άλλο λογαριασμό
     * @throws DuplicateAccountException αν υπάρξει διπλό UUID
     */
    Account updateAccount(String uuid, AccountUpdateDTO dto)
        throws AccountNotFoundException, IbanAlreadyExistsException, DuplicateAccountException;

    /**
     * Διαγράφει έναν λογαριασμό με βάση το UUID.
     *
     * @param uuid το UUID του λογαριασμού προς διαγραφή
     * @throws AccountNotFoundException αν δεν βρεθεί ο λογαριασμός
     */
    void deleteAccount(String uuid)
        throws AccountNotFoundException;

    /**
     * Αναζητά έναν λογαριασμό με βάση το IBAN.
     *
     * @param iban ο IBAN του λογαριασμού
     * @return ο αντίστοιχος λογαριασμός
     * @throws AccountNotFoundException αν δεν βρεθεί ο λογαριασμός
     */
    Account getAccountByIban(String iban)
        throws AccountNotFoundException;

    /**
     * Επιστρέφει όλους τους λογαριασμούς.
     *
     * @return λίστα με όλους τους λογαριασμούς
     */
    List<Account> getAllAccounts();

    /**
     * Εκτελεί κατάθεση χρημάτων σε λογαριασμό με βάση το UUID.
     *
     * @param uuid το UUID του λογαριασμού
     * @param amount το ποσό προς κατάθεση
     * @return ο ενημερωμένος λογαριασμός
     * @throws AccountNotFoundException αν δεν βρεθεί ο λογαριασμός
     * @throws NegativeAmountException αν το ποσό είναι αρνητικό
     */
    Account deposit(String uuid, double amount)
        throws AccountNotFoundException, NegativeAmountException;

    /**
     * Εκτελεί ανάληψη χρημάτων από λογαριασμό με βάση IBAN και SSN κατόχου.
     *
     * @param iban ο IBAN του λογαριασμού
     * @param ssn το SSN του κατόχου
     * @param amount το ποσό προς ανάληψη
     * @return ο ενημερωμένος λογαριασμός
     * @throws AccountNotFoundException αν δεν βρεθεί ο λογαριασμός
     * @throws NegativeAmountException αν το ποσό είναι αρνητικό
     * @throws InsufficientBalanceException αν το υπόλοιπο είναι ανεπαρκές
     * @throws SsnNotValidException αν το SSN δεν ταιριάζει με τον κάτοχο
     */
    Account withdraw(String iban, String ssn, double amount)
        throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException, SsnNotValidException;
}
