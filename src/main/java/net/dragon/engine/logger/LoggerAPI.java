package net.dragon.engine.logger;

import net.dragon.engine.api.Utility;

public class LoggerAPI {

    public static void log(LogType logType, String message) {
        if (logType == LogType.SEVERE) {
            Utility.LOGGER.severe(message);
        } else if (logType == LogType.WARNING) {
            Utility.LOGGER.warning(message);
        } else if (logType == LogType.INFO) {
            Utility.LOGGER.info(message);
        } else if (logType == LogType.CONFIG) {
            Utility.LOGGER.config(message);
        } else if (logType == LogType.FINE) {
            Utility.LOGGER.fine(message);
        } else if (logType == LogType.FINER) {
            Utility.LOGGER.finer(message);
        } else if (logType == LogType.FINEST) {
            Utility.LOGGER.finest(message);
        }
    }

}
