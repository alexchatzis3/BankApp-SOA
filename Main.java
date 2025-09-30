package gr.aueb.cf.ch18.bankapp;

import gr.aueb.cf.ch18.bankapp.dao.AccountDAOImpl;
import gr.aueb.cf.ch18.bankapp.dao.IAccountDAO;
import gr.aueb.cf.ch18.bankapp.dto.*;
import gr.aueb.cf.ch18.bankapp.model.Account;
import gr.aueb.cf.ch18.bankapp.model.User;
import gr.aueb.cf.ch18.bankapp.service.AccountServiceImpl;
import gr.aueb.cf.ch18.bankapp.service.IAccountService;
import gr.aueb.cf.ch18.bankapp.service.exceptions.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Κύρια κλάση της εφαρμογής BankApp.
 *
 * Παρέχει ένα console-based interface για CRUD operations,
 * καθώς και καταθέσεις και αναλήψεις χρημάτων.
 *
 * Χρησιμοποιεί το Service Layer για επιχειρησιακή λογική.
 */
public class Main {
    /** Scanner για είσοδο χρήστη */
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * DAO layer για διαχείριση δεδομένων λογαριασμών.
     * Δημιουργείται το AccountDAOImpl και "wired" στο Service.
     */
    private final static IAccountDAO dao = new AccountDAOImpl();

    /**
     * Service layer που περιέχει επιχειρησιακή λογική.
     * Το DAO περνάει μέσω constructor injection.
     */
    private final static IAccountService service = new AccountServiceImpl(dao);

    /**
     * Κύρια μέθοδος εκτέλεσης εφαρμογής.
     *
     * Παρέχει μενού επιλογών και χειρίζεται επαναλαμβανόμενες εισόδους.
     */
    public static void main(String[] args) {
        String choice = "";

        while (true) {
            printMenu();
            System.out.println("Παρακαλώ εισάγετε επιλογή");
            choice = getChoice();

            if (choice.matches("[qQ]")) {
                System.out.println("Goodbye. Thanks for using our app.");
                break;
            }

            if (!choice.matches("[1-7]")) {
                System.out.println("Wrong choice.");
                continue;
            }

            processWithChoice(choice);
        }

    }

    /**
     * Επεξεργάζεται την επιλογή του χρήστη.
     *
     * @param choice επιλογή χρήστη
     */
    public static void processWithChoice(String choice) {
        Account account;
        String uuid;
        String iban;

        switch (choice) {
            case "1": //Εισαγωγή λογαριασμού
                account = getAccount();
                insertAccount(account);
                break;
            case "2": // Ενημέρωση λογαριασμού
                System.out.println("Παρακαλώ δώστε το uuid του λογαριασμού προς ενημέρωση");
                uuid = scanner.nextLine();
                System.out.println("Παρακαλώ εισάγετε τα στοιχεία του νέου λογαριασμού");
                account = getAccount();
                updateAccount(uuid, account);
                break;
            case "3": // Ενημέρωση λογαριασμού
                System.out.println("Παρακαλώ δώστε το uuid του λογαριασμού προς διαγραφή");
                uuid = scanner.nextLine();
                deleteAccount(uuid);
                break;
            case "4": // Αναζήτηση λογαριασμού με IBAN
                System.out.println("Παρακαλώ δώστε το iban του λογαριασμού");
                iban = scanner.nextLine().trim();
                AccountReadOnlyDTO dto = getAccountByIban(iban);
                if (dto == null) {
                    System.out.println("Ο λογαριασμός δε βρέθηκε");
                } else {
                    System.out.println(dto);
                }
                break;
            case "5": // Εκτύπωση όλων των λογαριασμών
                List<AccountReadOnlyDTO> accounts = getAccounts();
                printAccounts(accounts);
                break;
            case "6": // Κατάθεση
                System.out.println("Δώστε UUID λογαριασμού για κατάθεση");
                uuid = scanner.nextLine().trim();
                System.out.println("Δώστε ποσό κατάθεσης");
                double depositAmount = Double.parseDouble(scanner.nextLine());
                deposit(uuid, depositAmount);
                break;
            case "7": // Ανάληψη
                System.out.println("Δώστε IBAN λογαριασμού για ανάληψη");
                iban = scanner.nextLine().trim();
                System.out.println("Δώστε SSN κατόχου");
                String ssn = scanner.nextLine().trim();
                System.out.println("Δώστε ποσό ανάληψης");
                double withdrawAmount = Double.parseDouble(scanner.nextLine());
                withdraw(iban, ssn, withdrawAmount);
                break;
            default:
                System.out.println("Generic error.");
                break;

        }

    }

    /**
     * Εκτελεί κατάθεση σε λογαριασμό.
     *
     * @param uuid UUID λογαριασμού
     * @param amount ποσό κατάθεσης
     */
    public static void deposit(String uuid, double amount) {
        try {
            Account acc = service.deposit(uuid, amount);
            System.out.println("Επιτυχής κατάθεση. Νέο υπόλοιπο: " + acc.getBalance());
        } catch (AccountNotFoundException | IllegalArgumentException | NegativeAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Εκτελεί ανάληψη από λογαριασμό.
     *
     * @param iban IBAN λογαριασμού
     * @param ssn SSN κατόχου
     * @param amount ποσό ανάληψης
     */

    public static void withdraw(String iban, String ssn, double amount) {
        try {
            Account acc = service.withdraw(iban, ssn, amount);
            System.out.println("Επιτυχής ανάληψη. Νέο υπόλοιπο: " + acc.getBalance());
        } catch (AccountNotFoundException | IllegalArgumentException | NegativeAmountException |
                 InsufficientBalanceException | SsnNotValidException e) {
            System.out.println(e.getMessage());
        }
    }


    /** Εκτυπώνει λίστα λογαριασμών */
    private static void printAccounts(List<AccountReadOnlyDTO> accounts) {
        accounts.forEach(System.out::println);
    }

    /** Χρησιμεύει για μετατροπή λίστας {@link Account } → {@link AccountReadOnlyDTO } */
    public static List<AccountReadOnlyDTO> mapToAccountsReadOnlyDto(List<Account> accounts) {
        List<AccountReadOnlyDTO> accountReadOnlyDTOS = new ArrayList<>();
        for (Account account: accounts) {
            accountReadOnlyDTOS.add(mapToAccountReadOnlyDto(account));
        }
        return accountReadOnlyDTOS;
    }

    /** Επιστρέφει όλους τους λογαριασμούς σε μορφή ReadOnlyDTO */
    public static List<AccountReadOnlyDTO> getAccounts() {
        List<Account> accounts = service.getAllAccounts();
        return mapToAccountsReadOnlyDto(accounts);
    }

    /** Μετατρέπει {@link User} → {@link UserReadOnlyDTO} */
    public static UserReadOnlyDTO mapToUserReadOnlyDto(User user) {
        return new UserReadOnlyDTO(user.getUuid(), user.getFirstname(), user.getLastname(), user.getSsn());
    }

    /** Μετατρέπει {@link Account} → {@link AccountReadOnlyDTO} */
    public static AccountReadOnlyDTO mapToAccountReadOnlyDto(Account account) {
        return new AccountReadOnlyDTO(account.getUuid(), account.getIban(), mapToUserReadOnlyDto(account.getHolder()), account.getBalance());
    }


    /** Επιστρέφει λογαριασμό βάσει IBAN σε μορφή ReadOnlyDTO */
    public static AccountReadOnlyDTO getAccountByIban(String iban) {
        Account acc = null;
        try {
            acc = service.getAccountByIban(iban);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (acc == null) return null;
        return mapToAccountReadOnlyDto(acc);
    }



    /** Διαγράφει λογαριασμό βάσει UUID */
    public static void deleteAccount(String uuid) {
        try {
            service.deleteAccount(uuid);
            System.out.println("Η επαφή διαγράφηκε επιτυχώς.");
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Μετατρέπει {@link User} → {@link UserUpdateDTO} */
    public static UserUpdateDTO mapToUserUpdateDto(User user) {
        return new UserUpdateDTO(user.getUuid(), user.getFirstname(), user.getLastname(), user.getSsn());
    }

    /** Μετατρέπει {@link Account} → {@link AccountUpdateDTO} */
    public static AccountUpdateDTO mapToAccountUpdateDto(Account account) {
        return new AccountUpdateDTO(account.getUuid(), account.getIban(), mapToUserUpdateDto(account.getHolder()), account.getBalance());
    }

    /** Ενημερώνει λογαριασμό βάσει UUID και νέων στοιχείων */
    public static void updateAccount (String uuid, Account account) {
        try {
            AccountUpdateDTO updateDTO = mapToAccountUpdateDto(account);
            Account acc = service.updateAccount(uuid, updateDTO);
        } catch (AccountNotFoundException | IbanAlreadyExistsException | DuplicateAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Μετατρέπει {@link User} → {@link UserInsertDTO} */
    public static UserInsertDTO mapToUserInsertDto(User user) {
        return new UserInsertDTO(user.getFirstname(), user.getLastname(), user.getSsn());
    }

    /** Μετατρέπει {@link Account} → {@link AccountInsertDTO} */
    public static AccountInsertDTO mapToAccountInsertDto(Account account) {
        return new AccountInsertDTO(account.getIban(), mapToUserInsertDto(account.getHolder()), account.getBalance());
    }

    /** Εισαγωγή νέου λογαριασμού */
    public static void insertAccount (Account account) {
        try {
            AccountInsertDTO insertDTO = mapToAccountInsertDto(account);
            Account acc = service.insertAccount(insertDTO);
            System.out.println("Επιτυχής εισαγωγή: " + acc);
        } catch (DuplicateAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Διαβάζει λογαριασμό από το χρήστη (IBAN, balance, User)
     */
    public static Account getAccount() {
        Account account = new Account();

        System.out.println("Παρακαλώ εισάγετε IBAN");
        account.setIban(scanner.nextLine());

        System.out.println("Παρακαλώ εισάγετε υπόλοιπο");
        account.setBalance(Double.parseDouble(scanner.nextLine()));

        User user =  new User();

        System.out.println("Παρακαλώ εισάγετε όνομα");
        user.setFirstname(scanner.nextLine());

        System.out.println("Παρακαλώ εισάγετε επώνυμο");
        user.setLastname(scanner.nextLine());

        System.out.println("Παρακαλω εισάγετε ssn");
        user.setSsn(scanner.nextLine());

        account.setHolder(user);
        return account;
    }

    /** Εκτυπώνει το menu επιλογών στον χρήστη */
    public static void printMenu() {
        System.out.println("Παρακαλώ επιλέξτε ένα από τα παρακάτω:");
        System.out.println("1. Εισαγωγή λογαριασμού");
        System.out.println("2. Ενημέρωση λογαριασμού");
        System.out.println("3. Διαγραφή λογαριασμού");
        System.out.println("4. Αναζήτηση λογαριασμού");
        System.out.println("5. Εκτύπωση λογαριασμών");
        System.out.println("6. Κατάθεση");
        System.out.println("7. Ανάληψη");
        System.out.println("q/Q για έξοδο");
    }

    /** Διαβάζει την επιλογή του χρήστη */
    public static String getChoice() {
        return scanner.nextLine().trim();
    }
}
