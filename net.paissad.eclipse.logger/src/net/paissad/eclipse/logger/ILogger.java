package net.paissad.eclipse.logger;

public interface ILogger {

    void info(String message);

    void info(String message, Exception exception);

    void warn(String message);

    void warn(String message, Exception exception);

    void error(String message);

    void error(String message, Exception exception);

}
