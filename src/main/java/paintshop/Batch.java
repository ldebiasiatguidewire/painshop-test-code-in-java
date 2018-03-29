package paintshop;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class Batch {

    private final List<Finish> finishList;

    protected Batch(final List<Finish> finishList) {
        this.finishList = finishList;
    }

    public static Batch of(final Order order) {
        final Finish[] finishArray = new Finish[order.getPaletteSize()];
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
        return new Batch(ImmutableList.copyOf(finishArray));
    }

    @Override
    public String toString() {
        if (finishList.isEmpty())
            return "No solution exists";
        else
            return Joiner.on(' ').join(finishList);
    }
}
