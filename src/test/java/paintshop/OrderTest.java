package paintshop;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class OrderTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_negativePalette() {
        new Order(-1, ImmutableList.of());
    }

    @Test
    public void parse_GGGGM() throws IOException, ParseException {
        final File file = new File(getClass().getClassLoader().getResource("../resources/GGGGM.txt").getFile());
        assertEquals(
                "M . G . G\n" +
                        ". G M G\n" +
                        ". . . . M",
                Order.parse(file).toString());
    }

    @Test
    public void parse_GMGMG() throws IOException, ParseException {
        final File file = new File(getClass().getClassLoader().getResource("../resources/GMGMG.txt").getFile());
        final Order order = Order.parse(file);
        assertEquals(
                ". M\n" +
                        ". . . . G\n" +
                        "G\n" +
                        "G . . M G\n" +
                        ". . G\n" +
                        ". . . . G\n" +
                        "G . G . G\n" +
                        ". . G\n" +
                        ". M\n" +
                        "G . . . G\n" +
                        ". M\n" +
                        ". . . . G\n" +
                        ". . . M\n" +
                        ". . . M G",
                order.toString());
    }

    @Test
    public void parse_MMMMM() throws IOException, ParseException {
        final File file = new File(getClass().getClassLoader().getResource("../resources/MMMMM.txt").getFile());
        final Order order = Order.parse(file);
        assertEquals(
                "M\n" +
                        ". M\n" +
                        ". . M\n" +
                        ". . . M\n" +
                        ". . . . M",
                order.toString());
    }

    @Test(expected = IOException.class)
    public void parse_no_file() throws IOException, ParseException {
        final File file = new File("no_file");
        final Order order = Order.parse(file);
    }

    @Test
    public void parse_no_solutions() throws IOException, ParseException {
        final File file = new File(getClass().getClassLoader().getResource("../resources/no.txt").getFile());
        final Order order = Order.parse(file);
        assertEquals(
                "G\n" +
                        "M",
                order.toString());
    }


    @Test
    public void getCustomerList() {
        final ImmutableList<Customer> customerList = ImmutableList.of(
                new Customer(ImmutableList.of(
                        new Color(0, Finish.M),
                        new Color(2, Finish.G),
                        new Color(4, Finish.G)
                )),
                new Customer(ImmutableList.of(
                        new Color(1, Finish.G),
                        new Color(2, Finish.M),
                        new Color(3, Finish.G)
                )),
                new Customer(ImmutableList.of(
                        new Color(4, Finish.M)
                ))
        );
        final Order order = new Order(5, customerList);
        assertEquals(customerList, order.getCustomerList());
    }

    @Test
    public void getPaletteSize() {
        final Order order = new Order(42, ImmutableList.of());
        assertEquals(42, order.getPaletteSize());
    }

}