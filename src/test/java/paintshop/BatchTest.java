package paintshop;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class BatchTest {

    @Test
    public void of_GGGGM() {
        final Batch batch = Batch.of(new Order(
                5,
                ImmutableList.of(
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
                )));
        assertEquals("G G G G M", batch.toString());
    }

    @Test
    public void of_GMGMG() {
        final Batch batch = Batch.of(new Order(
                5,
                ImmutableList.of(
                        new Customer(ImmutableList.of(
                                new Color(1, Finish.M)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(4, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(0, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(4, Finish.G),
                                new Color(0, Finish.G),
                                new Color(3, Finish.M)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(2, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(4, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(2, Finish.G),
                                new Color(4, Finish.G),
                                new Color(0, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(2, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(1, Finish.M)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(4, Finish.G),
                                new Color(0, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(1, Finish.M)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(4, Finish.G)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(3, Finish.M)
                        )),
                        new Customer(ImmutableList.of(
                                new Color(4, Finish.G),
                                new Color(3, Finish.M)
                        ))

                )));
        assertEquals("G M G M G", batch.toString());
    }

    @Test
    public void of_MMMMM() {
        final Batch batch = Batch.of(new Order(
                5,
                ImmutableList.of(
                        new Customer(ImmutableList.of(new Color(0, Finish.M))),
                        new Customer(ImmutableList.of(new Color(1, Finish.M))),
                        new Customer(ImmutableList.of(new Color(2, Finish.M))),
                        new Customer(ImmutableList.of(new Color(3, Finish.M))),
                        new Customer(ImmutableList.of(new Color(4, Finish.M)))
                )));
        assertEquals("M M M M M", batch.toString());
    }

    @Test
    public void of_no_solutions() {
        final Batch batch = Batch.of(new Order(
                1,
                ImmutableList.of(
                        new Customer(ImmutableList.of(new Color(0, Finish.G))),
                        new Customer(ImmutableList.of(new Color(0, Finish.M)))
                )));
        assertEquals("No solution exists", batch.toString());
    }

}