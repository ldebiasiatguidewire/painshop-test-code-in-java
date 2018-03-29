package paintshop;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Customer {

    private final List<Color> colorList;

    public Customer(final List<Color> colorList) {
        if (!isLegal(colorList))
            throw new IllegalArgumentException(
                    String.format("List %s can't have multiple [M]atte elements.", colorList)
            );
        this.colorList = colorList;
    }

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

    public List<Color> getColorList() {
        return colorList;
    }


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
