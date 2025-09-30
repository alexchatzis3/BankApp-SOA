package gr.aueb.cf.ch18.bankapp.service;

import gr.aueb.cf.ch18.bankapp.dao.IAccountDAO;
import gr.aueb.cf.ch18.bankapp.dto.AccountInsertDTO;
import gr.aueb.cf.ch18.bankapp.dto.AccountUpdateDTO;
import gr.aueb.cf.ch18.bankapp.dto.UserInsertDTO;
import gr.aueb.cf.ch18.bankapp.dto.UserUpdateDTO;
import gr.aueb.cf.ch18.bankapp.model.Account;
import gr.aueb.cf.ch18.bankapp.model.User;
import gr.aueb.cf.ch18.bankapp.service.exceptions.*;

import java.util.List;

/**
 * Υλοποίηση του {@link IAccountService} για διαχείριση λογαριασμών.
 *
 * Παρέχει επιχειρησιακή λογική για:
 * <ul>
 *   <li>Εισαγωγή νέων λογαριασμών</li>
 *   <li>Ενημέρωση υπάρχοντων λογαριασμών</li>
 *   <li>Διαγραφή λογαριασμών</li>
 *   <li>Αναζήτηση λογαριασμών</li>
 *   <li>Καταθέσεις και αναλήψεις</li>
 * </ul>
 *
 * Χρησιμοποιεί {@link AccountInsertDTO}, {@link AccountUpdateDTO},
 * {@link UserInsertDTO} και {@link UserUpdateDTO} για μεταφορά δεδομένων μεταξύ
 * UI και DAO.
 */
public class AccountServiceImpl implements IAccountService{
    /** DAO layer για διαχείριση των λογαριασμών. */
    private final IAccountDAO dao;

    /**
     * Constructor που δέχεται DAO για εξάρτηση (dependency injection).
     *
     * @param dao το DAO για διαχείριση λογαριασμών
     */
    public AccountServiceImpl(IAccountDAO dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account insertAccount(AccountInsertDTO dto)
            throws DuplicateAccountException {
        Account account;

        try {
            account = mapAccountFromInsertDTO(dto);

            if (dao.uuidExists(account.getUuid())) {
                throw new DuplicateAccountException(account);
            }

            return dao.insert(account);
        } catch (DuplicateAccountException e) {
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account updateAccount(String uuid, AccountUpdateDTO newDto)
            throws AccountNotFoundException, DuplicateAccountException, IbanAlreadyExistsException {
        Account account;

        try {
            account = mapAccountFromUpdateDTO(newDto);

            if (!dao.uuidExists(uuid)) {
                throw new AccountNotFoundException(uuid);
            }

            Account oldAccount = dao.get(uuid);

            // Έλεγχος αν το νέο IBAN υπάρχει σε άλλο λογαριασμό
            if (dao.ibanExists(account.getIban())
                && (!oldAccount.getIban().equals(newDto.getIban()))) {
                throw new IbanAlreadyExistsException();
            }

            // Έλεγχος για conflict UUID
            if (dao.uuidExists(account.getUuid())
                && (!oldAccount.getUuid().equals(newDto.getUuid()))) {
                throw new DuplicateAccountException(uuid);
            }

            return dao.update(uuid, account);
        } catch (AccountNotFoundException | DuplicateAccountException | IbanAlreadyExistsException e) {
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(String uuid)
            throws AccountNotFoundException {
        try {
            if (!dao.uuidExists(uuid)) {
                throw new AccountNotFoundException(uuid);
            }

            dao.delete(uuid);
        } catch (AccountNotFoundException e) {
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account getAccountByIban(String iban)
            throws AccountNotFoundException {
        Account account;

        try {
            account = dao.getAll()
                    .stream()
                    .filter(acc -> acc.getIban().equals(iban))
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                throw new AccountNotFoundException(iban);
            }

            return account;
        } catch (AccountNotFoundException e) {
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Account> getAllAccounts() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account deposit(String uuid, double amount)
            throws AccountNotFoundException, NegativeAmountException {
        Account account;
        try {
            if (amount < 0) {
                throw new NegativeAmountException(amount);
            }

            account = dao.get(uuid);

            if (account == null) {
                throw new AccountNotFoundException(uuid);
            }

            account.setBalance(account.getBalance() + amount);

            return account;
        } catch (AccountNotFoundException | NegativeAmountException e) {
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account withdraw(String iban, String ssn, double amount)
            throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException, SsnNotValidException {
        Account account;

        try {
            if (amount < 0) {
                throw new NegativeAmountException(amount);
            }

            account = dao.getAll()
                    .stream()
                    .filter(acc -> acc.getIban().equals(iban))
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                throw new AccountNotFoundException(iban);
            }

            if (!account.getHolder().getSsn().equals(ssn)) {
                throw new SsnNotValidException(ssn);
            }

            if (account.getBalance() < amount) {
                throw new InsufficientBalanceException(account.getBalance(), amount);
            }

            account.setBalance(account.getBalance() - amount);

            return account;
        } catch (NegativeAmountException | AccountNotFoundException | SsnNotValidException | InsufficientBalanceException e) {
            throw e;
        }
    }

    /**
     * Χρησιμεύει για αντιστοίχιση {@link AccountInsertDTO } → {@link Account}.
     */
    private Account mapAccountFromInsertDTO(AccountInsertDTO dto) {
        User user = mapUserFromInsertDTO(dto.getUserInsertDTO());
        return new Account(dto.getIban(), user, dto.getBalance());
    }

    /**
     * Χρησιμεύει για αντιστοίχιση {@link UserInsertDTO } → {@link User}.
     */
    private User mapUserFromInsertDTO(UserInsertDTO dto) {
        return new User(dto.getFirstname(), dto.getLastname(), dto.getSsn());
    }

    /**
     * Χρησιμεύει για αντιστοίχιση {@link AccountUpdateDTO } → {@link Account}.
     */
    private Account mapAccountFromUpdateDTO(AccountUpdateDTO dto) {
        User user = mapUserFromUpdateDTO(dto.getUserUpdateDTO());
        return new Account(dto.getIban(), user, dto.getBalance());
    }

    /**
     * Χρησιμεύει για αντιστοίχιση {@link UserUpdateDTO } → {@link User}.
     */
    private User mapUserFromUpdateDTO(UserUpdateDTO dto) {
        return new User(dto.getUuid() ,dto.getFirstname(), dto.getLastname(), dto.getSsn());
    }
}
