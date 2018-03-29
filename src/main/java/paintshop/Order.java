package paintshop;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Represent an order for multiple {@link Customer}s.
 */
public class Order {

    /**
     * Logger.
     */
    private final static Logger LOG = LoggerFactory.getLogger(Order.class);

    /**
     * Number of finishes for this order.
     */
    private final int paletteSize;

    /**
     * List of customers.
     */
    private final List<Customer> customerList;

    /**
     * Create a new order.
     *
     * @param paletteSize  Number of finishes for this order.
     *                     Can't be negative.
     * @param customerList List of customers.
     *                     Can be empty.
     * @throws IllegalArgumentException If the {@code paletteSize} is negative.
     */
    public Order(final int paletteSize, final List<Customer> customerList) {
        if (paletteSize < 0)
            throw new IllegalArgumentException("Palette size can't be negative.");
        this.paletteSize = paletteSize;
        this.customerList = customerList;
    }

    /**
     * Create a new order based on the instructions in {@code}file,
     * the file must be formatted according the problem statement.
     *
     * @param file The file having order instructions.
     * @return A new order.
     * Never {@code null}.
     * @throws IOException    If the {@code file} doesn't exist or is not readable.
     * @throws ParseException If the content of the file is not formatted according the problem statement.
     */
    public static Order parse(final File file) throws IOException, ParseException {
        if (!file.exists())
            throw new IOException(String.format("File %s doesn't exist.", file.toString()));
        int palettesize ;
        LOG.debug("FILE {}", file);
        final ImmutableList.Builder<Customer> customerListBuilder = ImmutableList.builder();
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            line = bufferedReader.readLine();
            LOG.debug(line);
            palettesize = Integer.parseInt(line);
            while ((line = bufferedReader.readLine()) != null) {
                LOG.debug(line);
                customerListBuilder.add(Customer.parse(line));
            }
        }
        return new Order(palettesize, customerListBuilder.build());
    }

    /**
     * Get the list of customers.
     *
     * @return The list of customers.
     * Never {@code}.
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Get the size of finish palette.
     *
     * @return The size of finish palette.
     */
    public int getPaletteSize() {
        return paletteSize;
    }

    /**
     * Return a textual representation of this {@link Order}.
     *
     * @return Return a string of multiple lines, one line per {@link Customer}.
     * Every line is formatted as described in the {@link Customer#toString()}.
     * Never {@code null}.
     */
    @Override
    public String toString() {
        final List<String> textList = Lists.newArrayListWithCapacity(customerList.size());
        for (final Customer customer : customerList) {
            textList.add(customer.toString());
        }
        return Joiner.on('\n').join(textList);
    }
}
