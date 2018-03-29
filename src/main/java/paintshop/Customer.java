package paintshop;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represent the colors wished by a customer in a {@link Order}.
 */
public class Customer {

    /**
     * Color list wished by this customer.
     */
    private final List<Color> colorList;

    /**
     * Create a new customer.
     *
     * @param colorList Color list wished by this customer.
     *                  Can be empty.
     */
    public Customer(final List<Color> colorList) {
        if (!isLegal(colorList))
            throw new IllegalArgumentException(
                    String.format("List %s can't have multiple [M]atte elements.", colorList)
            );
        this.colorList = colorList;
    }

    /**
     * Check the customer doesn't wish more than one matte color.
     *
     * @param colorList Color list wished by this customer.
     *                  Can be empty.
     * @return {@code false} if the customer wishes more than a single color as matte, else {@code true}.
     */
    private static boolean isLegal(final List<Color> colorList) {
        int matteNumber = 0;
        for (final Color color : colorList) {
            if (color.getFinish() == Finish.M) {
                matteNumber++;
                if (matteNumber > 1)
                    return false;
            }
        }
        return true;
    }

    /**
     * Parse a text representing the wishes of a customer formatted as explained in the problem statement in the
     * 'README.md' file.
     *
     * @param text The wishes of a customer expressed a couples of integer value and "M" for matte or "G" for glosse.
     *             Words of the text must be spaced.
     *             Note: integer values start from 1,
     *             this differs from internal representation of {@link Color#getIndex()} where the first color has
     *             index zero.
     * @return A new {@link Customer}.
     * @throws IllegalArgumentException If {@code text} is {@code null} or empty
     *                                  or if the even words are not "M" or "G".
     * @throws ParseException           If the {@code text} expressing the wishes of this customer hasn't an even
     *                                  number of words or if the odd words are not integer > 1.
     */
    public static Customer parse(final String text) throws ParseException {
        if (Strings.isNullOrEmpty(text))
            throw new IllegalArgumentException("Text can't be null or empty.");
        final ImmutableList<String> textList = ImmutableList.copyOf(Splitter.on(CharMatcher.breakingWhitespace())
                .split(text));
        if (textList.size() % 2 != 0) {
            throw new ParseException(
                    String.format("Text '%s' can't have odd number of words.", text),
                    textList.size()
            );
        }
        final ImmutableList.Builder<Color> colorListBuilder = ImmutableList.builder();
        for (int i = 0; i < textList.size(); i += 2) {
            colorListBuilder.add(new Color(
                    Integer.parseUnsignedInt(textList.get(i)) - 1,
                    Finish.valueOf(textList.get(i + 1))
            ));
        }
        return new Customer(colorListBuilder.build());
    }

    /**
     * Get the color list wished by this customer.
     *
     * @return Color list wished by this customer.
     * Never {@code null}.
     */
    public List<Color> getColorList() {
        return colorList;
    }


    /**
     * Return a textual representation for the finishes wished by this customer.
     *
     * @return A string with "M" or "G" for every finish wished by the customers in order of colors.
     * Note: this representation differs from the format of the text processed by {@link Customer#parse(String)}.
     * Never {@code null}.
     */
    @Override
    public String toString() {
        int maxIndex = 0;
        for (final Color color : colorList) {
            maxIndex = Math.max(maxIndex, color.getIndex());
        }
        final String[] textArray = new String[maxIndex + 1];
        Arrays.fill(textArray, ".");
        for (final Color color : colorList) {
            textArray[color.getIndex()] = color.getFinish().toString();
        }
        return Joiner.on(' ').join(textArray);
    }
}
