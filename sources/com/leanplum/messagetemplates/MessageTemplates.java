package com.leanplum.messagetemplates;

import android.content.Context;

public class MessageTemplates {
    private static boolean registered = false;

    static class Args {
        static final String ACCEPT_ACTION = "Accept action";
        static final String ACCEPT_BUTTON_BACKGROUND_COLOR = "Accept button.Background color";
        static final String ACCEPT_BUTTON_TEXT = "Accept button.Text";
        static final String ACCEPT_BUTTON_TEXT_COLOR = "Accept button.Text color";
        static final String ACCEPT_TEXT = "Accept text";
        static final String ACTION_URL = "Action URL";
        static final String BACKGROUND_COLOR = "Background color";
        static final String BACKGROUND_IMAGE = "Background image";
        static final String CANCEL_ACTION = "Cancel action";
        static final String CANCEL_TEXT = "Cancel text";
        static final String CLOSE_URL = "Close URL";
        static final String DISMISS_ACTION = "Dismiss action";
        static final String DISMISS_TEXT = "Dismiss text";
        static final String HAS_DISMISS_BUTTON = "Has dismiss button";
        static final String HTML_ALIGN = "HTML Align";
        static final String HTML_ALIGN_BOTTOM = "Bottom";
        static final String HTML_ALIGN_TOP = "Top";
        static final String HTML_HEIGHT = "HTML Height";
        static final String HTML_TAP_OUTSIDE_TO_CLOSE = "Tap Outside to Close";
        static final String HTML_WIDTH = "HTML Width";
        static final String HTML_Y_OFFSET = "HTML Y Offset";
        static final String LAYOUT_HEIGHT = "Layout.Height";
        static final String LAYOUT_WIDTH = "Layout.Width";
        static final String MESSAGE = "Message";
        static final String MESSAGE_COLOR = "Message.Color";
        static final String MESSAGE_TEXT = "Message.Text";
        static final String OPEN_URL = "Open URL";
        static final String TITLE = "Title";
        static final String TITLE_COLOR = "Title.Color";
        static final String TITLE_TEXT = "Title.Text";
        static final String TRACK_ACTION_URL = "Track Action URL";
        static final String TRACK_URL = "Track URL";
        static final String URL = "URL";

        Args() {
        }
    }

    static class Values {
        static final String ALERT_MESSAGE = "Alert message goes here.";
        static final int CENTER_POPUP_HEIGHT = 250;
        static final int CENTER_POPUP_WIDTH = 300;
        static final String CONFIRM_MESSAGE = "Confirmation message goes here.";
        static final String DEFAULT_ACTION_URL = "http://leanplum/runAction";
        static final String DEFAULT_BASE_URL = "http://leanplum/";
        static final String DEFAULT_CLOSE_URL = "http://leanplum/close";
        static final boolean DEFAULT_HAS_DISMISS_BUTTON = true;
        static final String DEFAULT_HTML_ALING = "Top";
        static final int DEFAULT_HTML_HEIGHT = 0;
        static final String DEFAULT_OPEN_URL = "http://leanplum/loadFinished";
        static final String DEFAULT_TRACK_ACTION_URL = "http://leanplum/runTrackedAction";
        static final String DEFAULT_TRACK_URL = "http://leanplum/track";
        static final String DEFAULT_URL = "http://www.example.com";
        public static final String FILE_PREFIX = "__file__";
        public static final String HTML_TEMPLATE_PREFIX = "__file__Template";
        static final String INTERSTITIAL_MESSAGE = "Interstitial message goes here.";
        static final String NO_TEXT = "No";
        static final String OK_TEXT = "OK";
        static final String POPUP_MESSAGE = "Popup message goes here.";
        static final String YES_TEXT = "Yes";

        Values() {
        }
    }

    static String getApplicationName(Context context) {
        int i = context.getApplicationInfo().labelRes;
        if (i == 0) {
            return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        }
        return context.getString(i);
    }

    public static synchronized void register(Context context) {
        synchronized (MessageTemplates.class) {
            if (!registered) {
                registered = true;
                OpenURL.register();
                Alert.register(context);
                Confirm.register(context);
                CenterPopup.register(context);
                Interstitial.register(context);
                WebInterstitial.register();
                HTMLTemplate.register();
            }
        }
    }
}
