package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NavDeepLink {
    private static final Pattern SCHEME_PATTERN = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");
    private final ArrayList<String> mArguments = new ArrayList<>();
    private final boolean mExactDeepLink;
    private final Pattern mPattern;

    NavDeepLink(@NonNull String str) {
        StringBuilder sb = new StringBuilder("^");
        if (!SCHEME_PATTERN.matcher(str).find()) {
            sb.append("http[s]?://");
        }
        Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(str);
        String str2 = ".*";
        boolean z = !str.contains(str2);
        int i = 0;
        while (matcher.find()) {
            this.mArguments.add(matcher.group(1));
            sb.append(Pattern.quote(str.substring(i, matcher.start())));
            sb.append("(.+?)");
            i = matcher.end();
            z = false;
        }
        if (i < str.length()) {
            sb.append(Pattern.quote(str.substring(i)));
        }
        this.mPattern = Pattern.compile(sb.toString().replace(str2, "\\E.*\\Q"));
        this.mExactDeepLink = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean matches(@NonNull Uri uri) {
        return this.mPattern.matcher(uri.toString()).matches();
    }

    /* access modifiers changed from: 0000 */
    public boolean isExactDeepLink() {
        return this.mExactDeepLink;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Bundle getMatchingArguments(@NonNull Uri uri, @NonNull Map<String, NavArgument> map) {
        Matcher matcher = this.mPattern.matcher(uri.toString());
        if (!matcher.matches()) {
            return null;
        }
        Bundle bundle = new Bundle();
        int size = this.mArguments.size();
        int i = 0;
        while (i < size) {
            String str = (String) this.mArguments.get(i);
            i++;
            String decode = Uri.decode(matcher.group(i));
            NavArgument navArgument = (NavArgument) map.get(str);
            if (navArgument != null) {
                try {
                    navArgument.getType().parseAndPut(bundle, str, decode);
                } catch (IllegalArgumentException unused) {
                    return null;
                }
            } else {
                bundle.putString(str, decode);
            }
        }
        return bundle;
    }
}
