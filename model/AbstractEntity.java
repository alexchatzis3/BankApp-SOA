package gr.aueb.cf.ch18.bankapp.model;

import java.util.UUID;

/**
 *  Abstract κλάση που παρέχει βασική υλοποίηση του {@link IdentifiableEntity}.
 *
 *  Παρέχει βασικές μεθόδους για την πρόσβαση και τροποποίηση του UUID.
 */
public abstract class AbstractEntity implements IdentifiableEntity {
    private String uuid;

    /**
     *Default Constructor
     */
    public AbstractEntity() {
        uuid = UUID.randomUUID().toString();
    }

    /**
     * Επιστρέφει το μοναδικό αναγνωριστικό (UUID) του entity.
     *
     * @return το UUID ως String
     */
    @Override
    public String getUuid() {
        return uuid;
    }

    /**
     * Θέτει το μοναδικό αναγνωριστικό (UUID) του entity.
     * @param uuid το νεο UUID ως String
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
