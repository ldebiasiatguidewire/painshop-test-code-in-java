package paintshop;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test (expected = IllegalArgumentException.class)
    public void constructor_illegal() {
        new Customer(ImmutableList.of(
                new Color(0, Finish.M),
                new Color(2, Finish.M)
        ));
    }

    @Test
    public void getColorList() {
        final List<Color> colorList = ImmutableList.of(
                new Color(0, Finish.M),
                new Color(2, Finish.G),
                new Color(4, Finish.G)
        );
        assertEquals(colorList, new Customer(colorList).getColorList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_empty() throws ParseException {
        Customer.parse("");
    }

    @Test
    public void parse_legal() throws ParseException {
        assertEquals("M . G . G", Customer.parse("1 M 3 G 5 G").toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_null() throws ParseException {
        Customer.parse(null);
    }

    @Test(expected = ParseException.class)
    public void parse_odd() throws ParseException {
        Customer.parse("1 M 3 G 4 G x");
    }

}