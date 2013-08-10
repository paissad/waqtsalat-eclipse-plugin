package net.paissad.eclipse.logger.impl;

import org.eclipse.core.runtime.ILog;
import org.osgi.framework.Bundle;

import net.paissad.eclipse.logger.ILogger;

public abstract class AbstractLogger implements ILogger {

    private final Bundle bundle;
    private final String pluginId;
    private final ILog   backendLog;

    protected AbstractLogger(Bundle bundle, String pluginId, ILog backendLog) {
        this.bundle = bundle;
        this.pluginId = pluginId;
        this.backendLog = backendLog;
    }

    @Override
    public void info(String message) {
        info(message, null);
    }

    @Override
    public void warn(String message) {
        warn(message, null);
    }

    @Override
    public void error(String message) {
        error(message, null);
    }
    
    protected Bundle getBundle() {
        return this.bundle;
    }
    
    protected String getPluginId() {
        return this.pluginId;
    }
    
    protected ILog getBackendLog() {
        return this.backendLog;
    }
}
