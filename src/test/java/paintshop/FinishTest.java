package paintshop;

import org.junit.Test;

import static org.junit.Assert.*;

public class FinishTest {

    @Test
    public void toString_gloss() {
        assertEquals("G", Finish.G.toString());
    }

    @Test
    public void toString_matte() {
        assertEquals("M", Finish.M.toString());
    }
}