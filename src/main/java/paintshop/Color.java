package paintshop;

import com.google.common.base.Joiner;

public class Color {

    private final Finish finish;
    private final int index;

    public Color(final int index, final Finish finish) {
        if (index < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        this.finish = finish;
        this.index = index;
    }

    public Finish getFinish() {
        return finish;
    }

    public int getIndex() {
        return index;
    }

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
