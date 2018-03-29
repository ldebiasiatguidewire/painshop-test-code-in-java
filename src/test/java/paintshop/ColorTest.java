package paintshop;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ColorTest {

    @Test
    public void getFinish() {
        final int i = new Random().nextInt();
        final Finish finish;
        if (i % 2 == 0) {
            finish = Finish.G;
        } else {
            finish = Finish.M;
        }
        assertEquals(new Color(0, finish).getFinish(), finish);
    }

    @Test
    public void getIndex() {
        final int i = Math.abs(new Random().nextInt());
        assertEquals(new Color(i, Finish.G).getIndex(), i);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeIndex() {
        new Color(-1, Finish.G);
    }

    @Test
    public void toString_onePalette() {
        assertEquals("G", new Color(0, Finish.G).toString());
    }

    @Test
    public void toString_fivePalette() {
        assertEquals(". . . . G", new Color(4, Finish.G).toString());
    }
}