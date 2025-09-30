\# BankApp - Service-Oriented Architecture (SOA)



Ένα \*\*BankApp\*\* γραμμένο σε Java, χρησιμοποιώντας \*\*Service-Oriented Architecture (SOA)\*\*.  

Η εφαρμογή υποστηρίζει CRUD λειτουργίες για λογαριασμούς, καθώς και κατάθεση και ανάληψη χρημάτων.



\## Αρχιτεκτονική

\- \*\*Main (UI Layer)\*\*: Console-based interface.

\- \*\*Service Layer\*\*: Επιχειρησιακή λογική και validation.

\- \*\*DAO Layer\*\*: Διαχείριση δεδομένων λογαριασμών σε μνήμη.

\- \*\*Model Layer\*\*: Account, User, AbstractEntity, IdentifiableEntity.

\- \*\*DTOs\*\*: Μεταφορά δεδομένων μεταξύ UI και Service/DAO.



\## Exceptions

Η εφαρμογή περιλαμβάνει εξειδικευμένα exceptions για καλύτερη διαχείριση σφαλμάτων:

\- `AccountNotFoundException`: Όταν δεν υπάρχει λογαριασμός με το συγκεκριμένο UUID ή IBAN.

\- `DuplicateAccountException`: Όταν προσπαθεί να δημιουργηθεί λογαριασμός με ήδη υπάρχον UUID ή IBAN.

\- `IbanAlreadyExistsException`: Όταν υπάρχει σύγκρουση IBAN.

\- `InsufficientBalanceException`: Όταν ο χρήστης προσπαθεί να κάνει ανάληψη με υπόλοιπο μικρότερο από το ποσό.

\- `NegativeAmountException`: Όταν δίνεται αρνητικό ποσό σε κατάθεση ή ανάληψη.

\- `SsnNotValidException`: Όταν το SSN δεν αντιστοιχεί στον κάτοχο του λογαριασμού.





\## Λειτουργίες

\- Εισαγωγή νέου λογαριασμού

\- Ενημέρωση λογαριασμού

\- Διαγραφή λογαριασμού

\- Αναζήτηση λογαριασμού με IBAN

\- Εκτύπωση όλων των λογαριασμών

\- Κατάθεση χρημάτων

\- Ανάληψη χρημάτων με έλεγχο SSN



\## Τρέξιμο της εφαρμογής

1\. Κλωνοποίηση του repo:

git clone <URL\_TO\_REPO>

2\. Άνοιγμα του project σε IDE (π.χ IntelliJ, Eclipse)

3\. Τρέξε την κλάση `Main.java`



\## Σημειώσεις

\- Η εφαρμογή χρησιμοποιεί in-memory DAO, οπότε τα δεδομένα χάνονται όταν κλείνει η εφαρμογή.

\- Οι συναλλαγές πραγματοποιούνται με έλεγχο SSN για ασφάλεια.

\- Δεν χρησιμοποιείται βάση δεδομένων. Όλα τα δεδομένα φυλάσσονται σε μνήμη.

\- Το μοντέλο ακολουθεί skeletal implementation: το AbstractEntity υλοποιεί το IdentifiableEntity.



