package paintshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ParseException {
        LOG.debug("Start...");
        if (args.length < 1)
            throw new IllegalArgumentException("No file in argument array.");
        for(int i = 0; i < args.length; i ++) {
            final File file = new File(args[i]);
            final Order order = Order.parse(file);
            LOG.debug("ORDER\n{}", order.toString());
            final Batch batch = Batch.of(order);
            LOG.debug("BATCH");
            System.out.println(batch.toString());
        }
        LOG.debug("Stop.");
    }
}
