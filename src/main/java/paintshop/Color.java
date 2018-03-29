package paintshop;

import com.google.common.base.Joiner;

/**
 * Represent the {@link Finish} or a color addressed by {@link Color#index} in the {@link Customer} preferences.
 */
public class Color {

    /**
     * The finish for this color.
     */
    private final Finish finish;

    /**
     * The index of this color in {@link Customer} preferences.
     * First color has index = 0.
     */
    private final int index;

    /**
     * Create a new color.
     *
     * @param index  The finish for this color.
     * @param finish The index of this color in {@link Customer} preferences.
     *               First color has index = 0.
     * @throws IllegalArgumentException If {@code index} is negative.
     */
    public Color(final int index, final Finish finish) {
        if (index < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        this.finish = finish;
        this.index = index;
    }

    /**
     * Get the finish of this color.
     *
     * @return The finish for this color.
     * Never {@code null.}
     */
    public Finish getFinish() {
        return finish;
    }

    /**
     * Get the index of this color in {@link Customer} preferences.
     *
     * @return The index of this color in {@link Customer} preferences.
     * First color has index = 0.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Return a textual representation for this color.
     *
     * @return Return a string ending "G" for glosse and "M" for matte prefixed by ". " for every color of possible
     * palette having index < of this {@link #index}.
     * Never {@link null}.
     */
    @Override
    public String toString() {
        final String[] textArray = new String[index + 1];
        textArray[index] = finish.toString();
        for (int i = index - 1; i >= 0; i--) {
            textArray[i] = ".";
        }
        return Joiner.on(' ').join(textArray);
    }
}
