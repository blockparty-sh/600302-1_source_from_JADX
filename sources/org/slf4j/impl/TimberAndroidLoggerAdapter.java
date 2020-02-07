package org.slf4j.impl;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import timber.log.Timber;

class TimberAndroidLoggerAdapter extends MarkerIgnoringBase {
    private static final long serialVersionUID = -1227274521521287937L;

    private boolean isLoggable(int i) {
        return true;
    }

    TimberAndroidLoggerAdapter(String str) {
        this.name = str;
    }

    public boolean isTraceEnabled() {
        return isLoggable(2);
    }

    public void trace(String str) {
        log(2, str, null);
    }

    public void trace(String str, Object obj) {
        formatAndLog(2, str, obj);
    }

    public void trace(String str, Object obj, Object obj2) {
        formatAndLog(2, str, obj, obj2);
    }

    public void trace(String str, Object... objArr) {
        formatAndLog(2, str, objArr);
    }

    public void trace(String str, Throwable th) {
        log(2, str, th);
    }

    public boolean isDebugEnabled() {
        return isLoggable(3);
    }

    public void debug(String str) {
        log(3, str, null);
    }

    public void debug(String str, Object obj) {
        formatAndLog(3, str, obj);
    }

    public void debug(String str, Object obj, Object obj2) {
        formatAndLog(3, str, obj, obj2);
    }

    public void debug(String str, Object... objArr) {
        formatAndLog(3, str, objArr);
    }

    public void debug(String str, Throwable th) {
        log(3, str, th);
    }

    public boolean isInfoEnabled() {
        return isLoggable(4);
    }

    public void info(String str) {
        log(4, str, null);
    }

    public void info(String str, Object obj) {
        formatAndLog(4, str, obj);
    }

    public void info(String str, Object obj, Object obj2) {
        formatAndLog(4, str, obj, obj2);
    }

    public void info(String str, Object... objArr) {
        formatAndLog(4, str, objArr);
    }

    public void info(String str, Throwable th) {
        log(4, str, th);
    }

    public boolean isWarnEnabled() {
        return isLoggable(5);
    }

    public void warn(String str) {
        log(5, str, null);
    }

    public void warn(String str, Object obj) {
        formatAndLog(5, str, obj);
    }

    public void warn(String str, Object obj, Object obj2) {
        formatAndLog(5, str, obj, obj2);
    }

    public void warn(String str, Object... objArr) {
        formatAndLog(5, str, objArr);
    }

    public void warn(String str, Throwable th) {
        log(5, str, th);
    }

    public boolean isErrorEnabled() {
        return isLoggable(6);
    }

    public void error(String str) {
        log(6, str, null);
    }

    public void error(String str, Object obj) {
        formatAndLog(6, str, obj);
    }

    public void error(String str, Object obj, Object obj2) {
        formatAndLog(6, str, obj, obj2);
    }

    public void error(String str, Object... objArr) {
        formatAndLog(6, str, objArr);
    }

    public void error(String str, Throwable th) {
        log(6, str, th);
    }

    private void formatAndLog(int i, String str, Object... objArr) {
        if (isLoggable(i)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            logInternal(i, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    private void log(int i, String str, Throwable th) {
        if (isLoggable(i)) {
            logInternal(i, str, th);
        }
    }

    private void logInternal(int i, String str, Throwable th) {
        Timber.tag(this.name);
        if (th == null) {
            Timber.log(i, str, new Object[0]);
        } else if (str != null) {
            Timber.log(i, th, str, new Object[0]);
        } else {
            Timber.log(i, th);
        }
    }
}
