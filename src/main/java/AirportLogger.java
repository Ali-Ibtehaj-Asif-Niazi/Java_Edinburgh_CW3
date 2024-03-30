import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Singleton Logger class
public class AirportLogger {
    private static AirportLogger instance;
    private static final String LOG_FILE_PATH = "simulation.log";
    private Logger logger;

    // Private constructor to prevent instantiation from outside
    private AirportLogger() {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH);
            fileHandler.setFormatter(new CustomFormatter());
            logger = Logger.getLogger(AirportLogger.class.getName());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static method to get the singleton instance
    public static synchronized AirportLogger getInstance() {
        if (instance == null) {
            instance = new AirportLogger();
        }
        return instance;
    }

    // Method to log a message
    public void log(String message) {
        logger.info(message);
    }

    // Custom log formatter to remove redundant parts
    static class CustomFormatter extends SimpleFormatter {
        @Override
        public synchronized String format(java.util.logging.LogRecord record) {
            return String.format("%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %2$s%n%4$s%n",
                    record.getMillis(),
                    record.getMessage(),
                    record.getLevel().getName(),
                    record.getThrown() != null ? record.getThrown() : "");
        }
    }
}


