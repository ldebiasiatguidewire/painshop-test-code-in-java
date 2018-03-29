package paintshop;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Represent a production batch of finishes.
 */
public class Batch {

    /**
     * List of finishes in production.
     */
    private final List<Finish> finishList;

    /**
     * Create the batch of finishes.
     *
     * @param finishList List of finishes in production.
     */
    protected Batch(final List<Finish> finishList) {
        this.finishList = finishList;
    }

    /**
     * Create a batch of finishes according the {@link Order}.
     * <p/>
     * The algorithm use an array of palette size to represent the constrains of the order.
     * Only a customer expressing a single choice is a constrain:
     * if the there is no constrain for the chosen color set it,
     * else check the set constrain is equal to the wanted one.
     * Every customer expressing more than one preference can be satisfied by a glosse finish,
     * hence do not consider them for constrains,
     * this means 2 finishes palette a '1 G 2 M' order is solved as 'G G'.
     * Note the algorithm in all cases produces the number of finishes expressed in the
     * {@link Order#getPaletteSize()} property.
     * If a constrain differs forom a previous set constrain for the same finish there is no solution.
     * The algorithm set each finish not subject to constrains as glosse.
     *
     * @param order The order to process.
     * @return A new batch.
     * Never {@code null}.
     * The {@link Batch#finishList} is empty if the problem has no solution
     * or the {@link Order#getCustomerList()} is empty,
     * or the {@link Order#getPaletteSize()} is zero.
     * Never {@code null}.
     */
    public static Batch of(final Order order) {
        final Finish[] finishArray = new Finish[order.getPaletteSize()];
        if (order.getPaletteSize() > 0) {
            for (final Customer customer : order.getCustomerList()) {
                if (customer.getColorList().size() == 1) {
                    final Color color = customer.getColorList().get(0);
                    if (finishArray[color.getIndex()] != null && finishArray[color.getIndex()] != color.getFinish()) {
                        return new Batch(ImmutableList.of());
                    }
                    finishArray[color.getIndex()] = color.getFinish();
                }
            }
            for (int i = 0; i < order.getPaletteSize(); i++) {
                if (finishArray[i] == null) {
                    finishArray[i] = Finish.G;
                }
            }
        }
        return new Batch(ImmutableList.copyOf(finishArray));
    }

    /**
     * Return a textual representation of this {@link Batch} according the problem statement.
     *
     * @return Return a textual representation of this {@link Batch} according the problem statement.
     * If {@link Batch#finishList} is empty, return "No solution exists".
     * Code {@code null}.
     */
    @Override
    public String toString() {
        if (finishList.isEmpty())
            return "No solution exists";
        else
            return Joiner.on(' ').join(finishList);
    }
}
