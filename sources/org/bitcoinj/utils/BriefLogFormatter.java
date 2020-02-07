package org.bitcoinj.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.bitcoinj.core.NetworkParameters;

public class BriefLogFormatter extends Formatter {
    private static Logger logger;
    private static final MessageFormat messageFormat = new MessageFormat("{3,date,HH:mm:ss} {0} {1}.{2}: {4}\n{5}");

    public static void init() {
        logger = Logger.getLogger("");
        Handler[] handlers = logger.getHandlers();
        if (handlers.length > 0) {
            handlers[0].setFormatter(new BriefLogFormatter());
        }
    }

    public static void initVerbose() {
        init();
        logger.setLevel(Level.ALL);
        logger.log(Level.FINE, NetworkParameters.PAYMENT_PROTOCOL_ID_TESTNET);
    }

    public static void initWithSilentBitcoinJ() {
        init();
        Logger.getLogger("org.bitcoinj").setLevel(Level.SEVERE);
    }

    public String format(LogRecord logRecord) {
        Object[] objArr = new Object[6];
        objArr[0] = Integer.valueOf(logRecord.getThreadID());
        String sourceClassName = logRecord.getSourceClassName();
        objArr[1] = sourceClassName.substring(sourceClassName.lastIndexOf(46) + 1);
        objArr[2] = logRecord.getSourceMethodName();
        objArr[3] = new Date(logRecord.getMillis());
        objArr[4] = logRecord.getMessage();
        if (logRecord.getThrown() != null) {
            StringWriter stringWriter = new StringWriter();
            logRecord.getThrown().printStackTrace(new PrintWriter(stringWriter));
            objArr[5] = stringWriter.toString();
        } else {
            objArr[5] = "";
        }
        return messageFormat.format(objArr);
    }
}
