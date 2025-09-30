package gr.aueb.cf.ch18.bankapp.dto;
/**
 * Abstract βασική κλάση για όλα τα Data Transfer Objects (DTOs) που περιέχουν UUID.
 *
 * Παρέχει getter και setter για το UUID, ώστε να μπορεί κάθε DTO να διατηρεί
 * αναγνωριστικό που συνδέεται με ένα entity στο μοντέλο.
 *
 * Υλοποιείται ή επεκτείνεται από πιο συγκεκριμένα DTOs όπως
 * {@link AccountInsertDTO}, {@link AccountUpdateDTO}, {@link UserInsertDTO}, κ.ά.
 */
public abstract class BaseDTO {
    /** Το μοναδικό αναγνωριστικό του DTO, που αντιστοιχεί σε UUID entity. */
    private String uuid;

    /**
     * Επιστρέφει το UUID του DTO.
     *
     * @return το UUID ως String
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Θέτει το UUID του DTO.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
