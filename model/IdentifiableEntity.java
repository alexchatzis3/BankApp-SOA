package gr.aueb.cf.ch18.bankapp.model;

/**
 * Interface που καθορίζει ότι ένα entity διαθέτει μοναδικό αναγνωριστικό (UUID).
 *
 * Χρήση: Το interface αυτό χρησιμοποιείται για entities όπως User και Account
 * ώστε να διασφαλίζεται ότι κάθε αντικείμενο έχει μοναδικό UUID.
 *
 */
public interface IdentifiableEntity {

    /**
     * Επιστρέφει το μοναδικό αναγνωριστικό (UUID) του entity.
     * @return το UUID ως String
     */
    String getUuid();
}
