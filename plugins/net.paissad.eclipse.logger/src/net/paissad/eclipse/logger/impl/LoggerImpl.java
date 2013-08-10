package net.paissad.eclipse.logger.impl;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

public class LoggerImpl extends AbstractLogger {

    public LoggerImpl(Bundle bundle, String pluginId, ILog backendLog) {
        super(bundle, pluginId, backendLog);
    }

    @Override
    public void info(String message, Exception exception) {
        getBackendLog().log(new Status(IStatus.INFO, getPluginId(), message, exception));
    }

    @Override
    public void warn(String message, Exception exception) {
        getBackendLog().log(new Status(IStatus.WARNING, getPluginId(), message, exception));
    }

    @Override
    public void error(String message, Exception exception) {
        getBackendLog().log(new Status(IStatus.ERROR, getPluginId(), message, exception));
    }

}
