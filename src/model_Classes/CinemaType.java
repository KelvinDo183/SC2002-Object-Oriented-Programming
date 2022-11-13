package model_Classes;

/**
 * Enumerated type of cinemas for better reference
 */
public enum CinemaType implements PriceChanger {
    STANDARD("Standard"),
    PREMIUM("Premium");

    /**
     * the text of this cinematype
     */
    private final String text;

    /**
     * Creates a cinematype
     * 
     * @param text the type of this cinema
     * 
     */

    private CinemaType(String text) {
        this.text = text;
    }

    /**
     * text to return when the cinematype is being called
     */
    public String toString() {
        return text;
    }
}