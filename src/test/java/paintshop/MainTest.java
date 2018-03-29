package paintshop;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class MainTest {

    @Test(expected = IllegalArgumentException.class)
    public void main_no_arguments() throws IOException, ParseException {
        Main.main(new String[0]);
    }

    @Test
    public void main_GGGGM() {
        try {
            final String[] args = {
                    new File(getClass()
                            .getClassLoader()
                            .getResource("../resources/GGGGM.txt")
                            .getFile()
                    ).toString()
            };
            Main.main(args);
        } catch (final Exception e) {
            fail();
        }
    }
}