package org.spongycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.spongycastle.i18n.filter.Filter;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.i18n.filter.UntrustedUrlInput;

public class LocalizedMessage {
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    protected FilteredArguments arguments;
    protected String encoding = DEFAULT_ENCODING;
    protected FilteredArguments extraArgs = null;
    protected Filter filter = null;

    /* renamed from: id */
    protected final String f1344id;
    protected ClassLoader loader = null;
    protected final String resource;

    protected class FilteredArguments {
        protected static final int FILTER = 1;
        protected static final int FILTER_URL = 2;
        protected static final int NO_FILTER = 0;
        protected int[] argFilterType;
        protected Object[] arguments;
        protected Filter filter;
        protected Object[] filteredArgs;
        protected boolean[] isLocaleSpecific;
        protected Object[] unpackedArgs;

        FilteredArguments(LocalizedMessage localizedMessage) {
            this(new Object[0]);
        }

        FilteredArguments(Object[] objArr) {
            this.filter = null;
            this.arguments = objArr;
            this.unpackedArgs = new Object[objArr.length];
            this.filteredArgs = new Object[objArr.length];
            this.isLocaleSpecific = new boolean[objArr.length];
            this.argFilterType = new int[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] instanceof TrustedInput) {
                    this.unpackedArgs[i] = objArr[i].getInput();
                    this.argFilterType[i] = 0;
                } else if (objArr[i] instanceof UntrustedInput) {
                    this.unpackedArgs[i] = objArr[i].getInput();
                    if (objArr[i] instanceof UntrustedUrlInput) {
                        this.argFilterType[i] = 2;
                    } else {
                        this.argFilterType[i] = 1;
                    }
                } else {
                    this.unpackedArgs[i] = objArr[i];
                    this.argFilterType[i] = 1;
                }
                this.isLocaleSpecific[i] = this.unpackedArgs[i] instanceof LocaleString;
            }
        }

        public boolean isEmpty() {
            return this.unpackedArgs.length == 0;
        }

        public Object[] getArguments() {
            return this.arguments;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object obj;
            Object[] objArr = new Object[this.unpackedArgs.length];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.unpackedArgs;
                if (i >= objArr2.length) {
                    return objArr;
                }
                Object[] objArr3 = this.filteredArgs;
                if (objArr3[i] != null) {
                    obj = objArr3[i];
                } else {
                    Object obj2 = objArr2[i];
                    if (this.isLocaleSpecific[i]) {
                        obj = filter(this.argFilterType[i], ((LocaleString) obj2).getLocaleString(locale));
                    } else {
                        obj = filter(this.argFilterType[i], obj2);
                        this.filteredArgs[i] = obj;
                    }
                }
                objArr[i] = obj;
                i++;
            }
        }

        private Object filter(int i, Object obj) {
            if (this.filter != null) {
                if (obj == null) {
                    obj = "null";
                }
                if (i != 0) {
                    if (i == 1) {
                        return this.filter.doFilter(obj.toString());
                    }
                    if (i != 2) {
                        return null;
                    }
                    return this.filter.doFilterUrl(obj.toString());
                }
            }
            return obj;
        }

        public Filter getFilter() {
            return this.filter;
        }

        public void setFilter(Filter filter2) {
            if (filter2 != this.filter) {
                for (int i = 0; i < this.unpackedArgs.length; i++) {
                    this.filteredArgs[i] = null;
                }
            }
            this.filter = filter2;
        }
    }

    public LocalizedMessage(String str, String str2) throws NullPointerException {
        if (str == null || str2 == null) {
            throw new NullPointerException();
        }
        this.f1344id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(this);
    }

    public LocalizedMessage(String str, String str2, String str3) throws NullPointerException, UnsupportedEncodingException {
        if (str == null || str2 == null) {
            throw new NullPointerException();
        }
        this.f1344id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(this);
        if (Charset.isSupported(str3)) {
            this.encoding = str3;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The encoding \"");
        sb.append(str3);
        sb.append("\" is not supported.");
        throw new UnsupportedEncodingException(sb.toString());
    }

    public LocalizedMessage(String str, String str2, Object[] objArr) throws NullPointerException {
        if (str == null || str2 == null || objArr == null) {
            throw new NullPointerException();
        }
        this.f1344id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(objArr);
    }

    public LocalizedMessage(String str, String str2, String str3, Object[] objArr) throws NullPointerException, UnsupportedEncodingException {
        if (str == null || str2 == null || objArr == null) {
            throw new NullPointerException();
        }
        this.f1344id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(objArr);
        if (Charset.isSupported(str3)) {
            this.encoding = str3;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The encoding \"");
        sb.append(str3);
        sb.append("\" is not supported.");
        throw new UnsupportedEncodingException(sb.toString());
    }

    public String getEntry(String str, Locale locale, TimeZone timeZone) throws MissingEntryException {
        ResourceBundle resourceBundle;
        String str2 = DEFAULT_ENCODING;
        String str3 = this.f1344id;
        String str4 = ".";
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(str4);
            sb.append(str);
            str3 = sb.toString();
        }
        String str5 = str3;
        try {
            if (this.loader == null) {
                resourceBundle = ResourceBundle.getBundle(this.resource, locale);
            } else {
                resourceBundle = ResourceBundle.getBundle(this.resource, locale, this.loader);
            }
            String string = resourceBundle.getString(str5);
            if (!this.encoding.equals(str2)) {
                string = new String(string.getBytes(str2), this.encoding);
            }
            if (!this.arguments.isEmpty()) {
                string = formatWithTimeZone(string, this.arguments.getFilteredArgs(locale), locale, timeZone);
            }
            return addExtraArgs(string, locale);
        } catch (MissingResourceException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Can't find entry ");
            sb2.append(str5);
            sb2.append(" in resource file ");
            sb2.append(this.resource);
            sb2.append(str4);
            String sb3 = sb2.toString();
            String str6 = this.resource;
            ClassLoader classLoader = this.loader;
            if (classLoader == null) {
                classLoader = getClassLoader();
            }
            MissingEntryException missingEntryException = new MissingEntryException(sb3, str6, str5, locale, classLoader);
            throw missingEntryException;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public String formatWithTimeZone(String str, Object[] objArr, Locale locale, TimeZone timeZone) {
        MessageFormat messageFormat = new MessageFormat(" ");
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(str);
        if (!timeZone.equals(TimeZone.getDefault())) {
            Format[] formats = messageFormat.getFormats();
            for (int i = 0; i < formats.length; i++) {
                if (formats[i] instanceof DateFormat) {
                    DateFormat dateFormat = (DateFormat) formats[i];
                    dateFormat.setTimeZone(timeZone);
                    messageFormat.setFormat(i, dateFormat);
                }
            }
        }
        return messageFormat.format(objArr);
    }

    /* access modifiers changed from: protected */
    public String addExtraArgs(String str, Locale locale) {
        if (this.extraArgs == null) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        Object[] filteredArgs = this.extraArgs.getFilteredArgs(locale);
        for (Object append : filteredArgs) {
            stringBuffer.append(append);
        }
        return stringBuffer.toString();
    }

    public void setFilter(Filter filter2) {
        this.arguments.setFilter(filter2);
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null) {
            filteredArguments.setFilter(filter2);
        }
        this.filter = filter2;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public String getId() {
        return this.f1344id;
    }

    public String getResource() {
        return this.resource;
    }

    public Object[] getArguments() {
        return this.arguments.getArguments();
    }

    public void setExtraArgument(Object obj) {
        setExtraArguments(new Object[]{obj});
    }

    public void setExtraArguments(Object[] objArr) {
        if (objArr != null) {
            this.extraArgs = new FilteredArguments(objArr);
            this.extraArgs.setFilter(this.filter);
            return;
        }
        this.extraArgs = null;
    }

    public Object[] getExtraArgs() {
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments == null) {
            return null;
        }
        return filteredArguments.getArguments();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource: \"");
        stringBuffer.append(this.resource);
        stringBuffer.append("\" Id: \"");
        stringBuffer.append(this.f1344id);
        stringBuffer.append("\"");
        stringBuffer.append(" Arguments: ");
        stringBuffer.append(this.arguments.getArguments().length);
        stringBuffer.append(" normal");
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null && filteredArguments.getArguments().length > 0) {
            stringBuffer.append(", ");
            stringBuffer.append(this.extraArgs.getArguments().length);
            stringBuffer.append(" extra");
        }
        stringBuffer.append(" Encoding: ");
        stringBuffer.append(this.encoding);
        stringBuffer.append(" ClassLoader: ");
        stringBuffer.append(this.loader);
        return stringBuffer.toString();
    }
}
