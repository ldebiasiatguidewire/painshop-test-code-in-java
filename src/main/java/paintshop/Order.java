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

public class Order {

    private final static Logger LOG = LoggerFactory.getLogger(Order.class);

    private final int paletteSize;
    private final List<Customer> customerList;

    public Order(final int paletteSize, final List<Customer> customerList) {
        if (paletteSize < 0)
            throw new IllegalArgumentException("Palette size can't be negative.");
        this.paletteSize = paletteSize;
        this.customerList = customerList;
    }

    public static Order parse(final File file) throws IOException, ParseException {
        if (!file.exists())
            throw new IOException(String.format("File %s doesn't exist.", file.toString()));
        int palettesize = 0;
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

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public int getPaletteSize() {
        return paletteSize;
    }

    @Override
    public String toString() {
        final List<String> textList = Lists.newArrayListWithCapacity(customerList.size());
        for (final Customer customer : customerList) {
            textList.add(customer.toString());
        }
        return Joiner.on('\n').join(textList);
    }
}
