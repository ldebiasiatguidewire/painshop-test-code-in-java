package paintshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Entry point for the application.
 */
public class Main {

    /**
     * Logger.
     */
    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * Process the files, the path of those are in {@code args}, parsing them as instructions according the format
     * explained in 'README.md' file to create an {@link Order} and from the order it tries to creates a
     * {@link Batch} minimizing the number of matte finishes; it prints the {@Batch} result in console
     * according the format required by the problem statement as explained in 'README.md'.
     *
     * @param args Paths of files to process. Each file is formatted according the problem stated in 'README.md'.
     * @throws IOException    If files at {@code args} paths are not available or readable.
     * @throws ParseException If files at {@code args} are not formatted according instructions in 'README.md'.
     */
    public static void main(String[] args) throws IOException, ParseException {
        LOG.debug("Start...");
        if (args.length < 1)
            throw new IllegalArgumentException("No file in argument array.");
        for (int i = 0; i < args.length; i++) {
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
