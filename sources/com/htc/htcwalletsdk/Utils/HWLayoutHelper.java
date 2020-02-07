package com.htc.htcwalletsdk.Utils;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.htc.htcwalletsdk.CONSTANT.HW_SECURITY_UI;
import com.htc.htcwalletsdk.Utils.TableParser.C2278Key;
import com.leanplum.internal.Constants.Kinds;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import org.spongycastle.i18n.MessageBundle;

public class HWLayoutHelper {
    static final String TAG = "HWLayoutHelper";
    private ImageButton mBackButton;
    /* access modifiers changed from: private */
    public Context mContext;
    private TextView mDescription;
    private TextView mDialogTitle;
    private FrameLayout mDynamicContent;
    private int mHeight;
    private HW_SECURITY_UI mHwSecurityUi;
    private TextView mMessage;
    private Button mNegButton;
    private Button mPosButton;
    private ProgressBar mProgBar1;
    private ProgressBar mProgBar2;
    private ProgressBar mProgBar3;
    private TextView mSubtitle;
    private TextView mTitle;
    private Drawable mTransactionBtnBackground;
    private Button mTransactionButton;
    private ViewGroup mView;
    private int mWidth;
    BasePageHolder pageHolder;

    private abstract class BasePageHolder {
        Context context;
        int height;
        private ViewGroup main;
        int width;

        /* access modifiers changed from: 0000 */
        public ViewGroup getView(HW_SECURITY_UI hw_security_ui) {
            return null;
        }

        BasePageHolder(Context context2, int i, int i2) {
            this.context = context2;
            this.width = i;
            this.height = i2;
        }

        /* access modifiers changed from: 0000 */
        public void setMainView(LayoutInflater layoutInflater, String str) {
            this.main = (ViewGroup) layoutInflater.inflate(HWLayoutHelper.this.getResourceId(str, "layout"), null);
        }

        /* access modifiers changed from: 0000 */
        public View getContentViewById(String str) {
            return this.main.findViewById(HWLayoutHelper.this.getResourceId(str, CommonProperties.f657ID));
        }

        /* access modifiers changed from: 0000 */
        public ViewGroup returnMainViewBySize(int i, int i2) {
            this.main.measure(MeasureSpec.makeMeasureSpec(i, 1073741824), MeasureSpec.makeMeasureSpec(i2, 1073741824));
            ViewGroup viewGroup = this.main;
            viewGroup.layout(0, 0, viewGroup.getMeasuredWidth(), this.main.getMeasuredHeight());
            return this.main;
        }
    }

    private class ReviewPageHolder extends BasePageHolder {
        private Button cancelButton = ((Button) getContentViewById("review_cancel_button"));
        private ImageView card_background = ((ImageView) getContentViewById("review_info_background"));
        private int mColorTableIndex;
        private Button sendButton = ((Button) getContentViewById("review_send_button"));
        private View statusbar_background = getContentViewById("review_statusbar_background");
        private RelativeLayout toolbar_layout = ((RelativeLayout) getContentViewById("review_toolbar"));

        ReviewPageHolder(LayoutInflater layoutInflater, Context context, int i, int i2, int i3) {
            super(context, i, i2);
            this.mColorTableIndex = i3;
            setMainView(layoutInflater, "activity_review_for_exodus");
        }

        /* access modifiers changed from: 0000 */
        public ViewGroup getView(HW_SECURITY_UI hw_security_ui) {
            Drawable drawable;
            int i;
            Drawable drawable2 = getDrawable("review_button_background");
            String str = "wallet_card_default";
            switch (hw_security_ui) {
                case REVIEW_ETH_BG:
                    drawable = getDrawable("wallet_card_eth");
                    i = getColorCode("colorReviewETH");
                    break;
                case REVIEW_BTC_BG:
                    drawable = getDrawable("wallet_card_btc");
                    i = getColorCode("colorReviewBTC");
                    break;
                case REVIEW_LTC_BG:
                    drawable = getDrawable("wallet_card_ltc");
                    i = getColorCode("colorReviewLTC");
                    break;
                case REVIEW_BAT_BG:
                    drawable = getDrawable("wallet_card_bat");
                    i = getColorCode("colorReviewBAT");
                    break;
                case REVIEW_ERC_BG:
                    int colorCodeFromTable = getColorCodeFromTable(this.mColorTableIndex);
                    Drawable drawable3 = getDrawable(str);
                    i = colorCodeFromTable;
                    drawable = drawable3;
                    break;
                case REVIEW_ERC_DEFAULT_BG:
                    drawable = getDrawable(str);
                    i = getColorCode("colorReviewDefault");
                    break;
                default:
                    return null;
            }
            this.card_background.setImageDrawable(drawable);
            if (drawable2 != null) {
                drawable2.setColorFilter(i, Mode.MULTIPLY);
            }
            this.sendButton.setBackground(drawable2);
            this.cancelButton.setBackground(drawable2);
            this.statusbar_background.setBackgroundColor(i);
            this.toolbar_layout.setBackgroundColor(i);
            return returnMainViewBySize(this.width, this.height);
        }

        /* access modifiers changed from: 0000 */
        public Drawable getDrawable(String str) {
            String str2 = "drawable";
            if (VERSION.SDK_INT >= 21) {
                return HWLayoutHelper.this.mContext.getDrawable(HWLayoutHelper.this.getResourceId(str, str2));
            }
            return HWLayoutHelper.this.mContext.getResources().getDrawable(HWLayoutHelper.this.getResourceId(str, str2));
        }

        /* access modifiers changed from: 0000 */
        public Drawable getDrawableFromColor(int i) {
            Drawable drawable;
            String str = "drawable";
            String str2 = "wallet_card_white";
            if (VERSION.SDK_INT >= 21) {
                drawable = HWLayoutHelper.this.mContext.getDrawable(HWLayoutHelper.this.getResourceId(str2, str));
            } else {
                drawable = HWLayoutHelper.this.mContext.getResources().getDrawable(HWLayoutHelper.this.getResourceId(str2, str));
            }
            if (drawable != null) {
                drawable.setColorFilter(i, Mode.MULTIPLY);
            }
            return drawable;
        }

        /* access modifiers changed from: 0000 */
        public int getColorCode(String str) {
            int i = VERSION.SDK_INT;
            String str2 = Kinds.COLOR;
            if (i >= 23) {
                return HWLayoutHelper.this.mContext.getColor(HWLayoutHelper.this.getResourceId(str, str2));
            }
            return HWLayoutHelper.this.mContext.getResources().getColor(HWLayoutHelper.this.getResourceId(str, str2));
        }

        /* access modifiers changed from: 0000 */
        public int getColorCodeFromTable(int i) {
            return HWLayoutHelper.this.mContext.getResources().getIntArray(HWLayoutHelper.this.getResourceId("colorReviewERC20", C2278Key.table_data_array))[i];
        }
    }

    private class SocialRestorePageHolder extends BasePageHolder {
        SocialRestorePageHolder(LayoutInflater layoutInflater, Context context, int i, int i2) {
            super(context, i, i2);
            setMainView(layoutInflater, "specific_social_restore_activity_for_exodus");
        }

        /* access modifiers changed from: 0000 */
        public ViewGroup getView(HW_SECURITY_UI hw_security_ui) {
            return returnMainViewBySize(this.width, this.height);
        }
    }

    private class VerifyMessagePageHolder extends BasePageHolder {
        private TextView actionbar_title = ((TextView) getContentViewById("review_title"));
        private TextView addr_title = ((TextView) getContentViewById("review_info_addr_title_text"));
        private Button cancelButton = ((Button) getContentViewById("review_cancel_button"));
        private ImageView card_background = ((ImageView) getContentViewById("review_info_background"));
        private TextView detail_title = ((TextView) getContentViewById("review_info_coin_title_detail_text"));
        private Button editButton = ((Button) getContentViewById("review_info_edit_button"));
        private RelativeLayout message_layout = ((RelativeLayout) getContentViewById("review_info_message_content_layout"));
        private Button sendButton = ((Button) getContentViewById("review_send_button"));
        private TextView simple_title = ((TextView) getContentViewById("review_info_coin_title_simple_text"));
        private View statusbar_background = getContentViewById("review_statusbar_background");
        private RelativeLayout toolbar_layout = ((RelativeLayout) getContentViewById("review_toolbar"));
        private RelativeLayout transaction_layout = ((RelativeLayout) getContentViewById("review_info_transaction_content_layout"));

        VerifyMessagePageHolder(LayoutInflater layoutInflater, Context context, int i, int i2) {
            super(context, i, i2);
            setMainView(layoutInflater, "activity_review_for_exodus");
        }

        /* access modifiers changed from: 0000 */
        public ViewGroup getView(HW_SECURITY_UI hw_security_ui) {
            Drawable drawable;
            int i;
            Drawable drawable2 = getDrawable("review_button_background");
            switch (hw_security_ui) {
                case SIGN_MSG_ETH_BG:
                    drawable = getDrawable("wallet_card_eth");
                    i = getColorCode("colorReviewETH");
                    break;
                case SIGN_MSG_BTC_BG:
                    drawable = getDrawable("wallet_card_btc");
                    i = getColorCode("colorReviewBTC");
                    break;
                case SIGN_MSG_LTC_BG:
                    drawable = getDrawable("wallet_card_ltc");
                    i = getColorCode("colorReviewLTC");
                    break;
                case SIGN_MSG_BAT_BG:
                    drawable = getDrawable("wallet_card_bat");
                    i = getColorCode("colorReviewBAT");
                    break;
                default:
                    return null;
            }
            this.card_background.setImageDrawable(drawable);
            if (drawable2 != null) {
                drawable2.setColorFilter(i, Mode.MULTIPLY);
            }
            this.sendButton.setBackground(drawable2);
            this.cancelButton.setBackground(drawable2);
            this.statusbar_background.setBackgroundColor(i);
            this.toolbar_layout.setBackgroundColor(i);
            this.transaction_layout.setVisibility(8);
            this.message_layout.setVisibility(0);
            String str = "string";
            this.actionbar_title.setText(HWLayoutHelper.this.getResourceId("text_signmsg_actionbar_title", str));
            this.editButton.setVisibility(4);
            this.simple_title.setVisibility(8);
            this.detail_title.setVisibility(8);
            this.addr_title.setVisibility(0);
            this.cancelButton.setText(HWLayoutHelper.this.getResourceId("cancel", str));
            this.sendButton.setText(HWLayoutHelper.this.getResourceId("text_signmsg_sign_button", str));
            return returnMainViewBySize(this.width, this.height);
        }

        /* access modifiers changed from: 0000 */
        public Drawable getDrawable(String str) {
            String str2 = "drawable";
            if (VERSION.SDK_INT >= 21) {
                return HWLayoutHelper.this.mContext.getDrawable(HWLayoutHelper.this.getResourceId(str, str2));
            }
            return HWLayoutHelper.this.mContext.getResources().getDrawable(HWLayoutHelper.this.getResourceId(str, str2));
        }

        /* access modifiers changed from: 0000 */
        public int getColorCode(String str) {
            int i = VERSION.SDK_INT;
            String str2 = Kinds.COLOR;
            if (i >= 23) {
                return HWLayoutHelper.this.mContext.getColor(HWLayoutHelper.this.getResourceId(str, str2));
            }
            return HWLayoutHelper.this.mContext.getResources().getColor(HWLayoutHelper.this.getResourceId(str, str2));
        }
    }

    private class VerifySocialPageHolder extends BasePageHolder {
        private LinearLayout codeLayout = ((LinearLayout) getContentViewById("code_linearlayout"));
        private TextView informationView = ((TextView) getContentViewById("verification_code_information"));
        private String name;
        private TextView nameView = ((TextView) getContentViewById("verification_code_name"));
        private TextView rejectView = ((TextView) getContentViewById("verification_code_reject"));
        private TextView titleView = ((TextView) getContentViewById("verification_code_title"));

        VerifySocialPageHolder(LayoutInflater layoutInflater, Context context, int i, int i2, String str) {
            super(context, i, i2);
            this.name = str;
            setMainView(layoutInflater, "activity_verification_code");
            getContentViewById("secure_layout").setVisibility(0);
        }

        /* access modifiers changed from: 0000 */
        public ViewGroup getView(HW_SECURITY_UI hw_security_ui) {
            if (this.name == null) {
                return null;
            }
            String str = "string";
            this.titleView.setText(String.format(this.context.getString(HWLayoutHelper.this.getResourceId("verification_share_code_title", str)), new Object[]{this.name}));
            this.codeLayout.setVisibility(4);
            this.nameView.setText(this.name);
            this.informationView.setVisibility(4);
            SpannableString spannableString = new SpannableString(this.context.getString(HWLayoutHelper.this.getResourceId("verification_share_code_reject", str)));
            spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
            this.rejectView.setText(spannableString);
            return returnMainViewBySize(this.width, this.height);
        }
    }

    public HWLayoutHelper(Context context, HW_SECURITY_UI hw_security_ui, int i, int i2) {
        this(context, hw_security_ui, i, i2, 0);
    }

    public HWLayoutHelper(Context context, HW_SECURITY_UI hw_security_ui, int i, int i2, int i3) {
        this(context, hw_security_ui, i, i2, i3, null);
    }

    public HWLayoutHelper(Context context, HW_SECURITY_UI hw_security_ui, int i, int i2, String str) {
        this(context, hw_security_ui, i, i2, 0, str);
    }

    public HWLayoutHelper(Context context, HW_SECURITY_UI hw_security_ui, int i, int i2, int i3, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("HW_SECURITY_UI:");
        sb.append(hw_security_ui);
        sb.append(", width:");
        sb.append(i);
        sb.append(". height:");
        sb.append(i2);
        ZKMALog.m275i(TAG, sb.toString());
        this.mContext = context;
        this.mHwSecurityUi = hw_security_ui;
        this.mWidth = i;
        this.mHeight = i2;
        LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        HW_SECURITY_UI hw_security_ui2 = this.mHwSecurityUi;
        HW_SECURITY_UI hw_security_ui3 = HW_SECURITY_UI.SECURITY_CHECK;
        String str2 = MessageBundle.TITLE_ENTRY;
        String str3 = "double_margin_l";
        String str4 = "dimen";
        String str5 = "layout";
        String str6 = CommonProperties.f657ID;
        if (hw_security_ui2 == hw_security_ui3 || this.mHwSecurityUi == HW_SECURITY_UI.CONFIRM_PIN_REQUIRED) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("activity_unlock_wallet", str5), null);
            this.mTitle = (TextView) this.mView.findViewById(getResourceId(str2, str6));
            LayoutParams layoutParams = new LayoutParams(this.mTitle.getLayoutParams());
            layoutParams.setMargins(this.mContext.getResources().getDimensionPixelOffset(getResourceId(str3, str4)), this.mContext.getResources().getDimensionPixelOffset(getResourceId("unlock_title_marginTop_HW", str4)), this.mContext.getResources().getDimensionPixelOffset(getResourceId(str3, str4)), 0);
            this.mTitle.setLayoutParams(layoutParams);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.RESET_WALLET_2) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("alert_dialog", str5), null);
            this.mDialogTitle = (TextView) this.mView.findViewById(getResourceId("alertTitle", str6));
            this.mMessage = (TextView) this.mView.findViewById(getResourceId("message", str6));
            this.mPosButton = (Button) this.mView.findViewById(getResourceId("button2", str6));
            this.mNegButton = (Button) this.mView.findViewById(getResourceId("button3", str6));
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.NEXT_BUTTON) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_next_button_for_exodus", str5), null);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_ETH_BG || this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_BTC_BG || this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_LTC_BG || this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_BAT_BG || this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_ERC_DEFAULT_BG || this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_ERC_BG) {
            ReviewPageHolder reviewPageHolder = new ReviewPageHolder(layoutInflater, context, i, i2, i3);
            this.pageHolder = reviewPageHolder;
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.SIGN_MSG_ETH_BG || this.mHwSecurityUi == HW_SECURITY_UI.SIGN_MSG_BTC_BG || this.mHwSecurityUi == HW_SECURITY_UI.SIGN_MSG_LTC_BG || this.mHwSecurityUi == HW_SECURITY_UI.SIGN_MSG_BAT_BG) {
            VerifyMessagePageHolder verifyMessagePageHolder = new VerifyMessagePageHolder(layoutInflater, context, i, i2);
            this.pageHolder = verifyMessagePageHolder;
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.VERIFY_SOCIAL_CODE) {
            VerifySocialPageHolder verifySocialPageHolder = new VerifySocialPageHolder(layoutInflater, context, i, i2, str);
            this.pageHolder = verifySocialPageHolder;
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.ENTER_SOCIAL_RESTORE) {
            SocialRestorePageHolder socialRestorePageHolder = new SocialRestorePageHolder(layoutInflater, context, i, i2);
            this.pageHolder = socialRestorePageHolder;
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.CREATE_PIN_ERROR_MSG) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_create_pin_error_msg_for_exodus", str5), null);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.CONFIRM_PIN_ERROR_MSG) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_confirm_pin_error_msg_for_exodus", str5), null);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.RECOVERY_ERROR_MSG) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_recovery_error_msg_for_exodus", str5), null);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.RECOVERY_INVALID_MSG) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_recovery_invalid_msg_for_exodus", str5), null);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_ERROR_MSG) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_review_error_msg_for_exodus", str5), null);
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.BTC_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.BTC_SEND_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.ETH_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.ETH_SEND_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.LTC_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.LTC_SEND_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.BAT_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.BAT_SEND_BUTTON) {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("layout_transaction_button_for_exodus", str5), null);
            this.mTransactionButton = (Button) this.mView.findViewById(getResourceId("review_button", str6));
        } else {
            this.mView = (ViewGroup) layoutInflater.inflate(getResourceId("activity_ui_template", str5), null);
            this.mTitle = (TextView) this.mView.findViewById(getResourceId(str2, str6));
            this.mDescription = (TextView) this.mView.findViewById(getResourceId("description", str6));
            this.mSubtitle = (TextView) this.mView.findViewById(getResourceId("subtitle", str6));
            LayoutParams layoutParams2 = new LayoutParams(this.mSubtitle.getLayoutParams());
            layoutParams2.setMargins(this.mContext.getResources().getDimensionPixelOffset(getResourceId(str3, str4)), this.mContext.getResources().getDimensionPixelOffset(getResourceId("subtitle_margin_top_HW", str4)), this.mContext.getResources().getDimensionPixelOffset(getResourceId(str3, str4)), 0);
            this.mSubtitle.setLayoutParams(layoutParams2);
            this.mProgBar1 = (ProgressBar) this.mView.findViewById(getResourceId("progressBar1", str6));
            this.mProgBar2 = (ProgressBar) this.mView.findViewById(getResourceId("progressBar2", str6));
            this.mProgBar3 = (ProgressBar) this.mView.findViewById(getResourceId("progressBar3", str6));
            this.mBackButton = (ImageButton) this.mView.findViewById(getResourceId("backButton", str6));
            this.mDynamicContent = (FrameLayout) this.mView.findViewById(getResourceId("dynamic_content", str6));
        }
    }

    /* access modifiers changed from: private */
    public int getResourceId(String str, String str2) {
        return this.mContext.getResources().getIdentifier(str, str2, this.mContext.getPackageName());
    }

    public ViewGroup getView() {
        int i = C22771.$SwitchMap$com$htc$htcwalletsdk$CONSTANT$HW_SECURITY_UI[this.mHwSecurityUi.ordinal()];
        String str = "write_recovery_key_description";
        String str2 = "layout_write_recovery_key_for_exodus";
        String str3 = "confirm_your_pin_code_title";
        String str4 = "text_recovery_enter_description";
        String str5 = "text_recovery_enter_title";
        String str6 = "text_recovery_verify_description";
        String str7 = "text_recovery_verify_title";
        String str8 = "layout";
        String str9 = "layout_inflater";
        String str10 = "create_your_pin_code_title";
        String str11 = "pin_open_app_desc";
        Integer valueOf = Integer.valueOf(1);
        String str12 = "string";
        switch (i) {
            case 1:
                setAllView(this.mContext.getResources().getString(getResourceId(str10, str12)), this.mContext.getResources().getString(getResourceId(str11, str12)), null, null, true);
                break;
            case 2:
                setAllView(this.mContext.getResources().getString(getResourceId(str3, str12)), this.mContext.getResources().getString(getResourceId(str11, str12)), null, null, false);
                break;
            case 3:
                setAllView(this.mContext.getResources().getString(getResourceId(str10, str12)), this.mContext.getResources().getString(getResourceId("pin_unlock_wallet_desc", str12)), null, null, true);
                break;
            case 4:
                View inflate = ((LayoutInflater) this.mContext.getSystemService(str9)).inflate(getResourceId("layout_recovery_key_introduction_for_exodus", str8), null);
                setAllView(this.mContext.getResources().getString(getResourceId("recovery_key_introduction_title", str12)), this.mContext.getResources().getString(getResourceId("recovery_key_introduction_description", str12)), null, inflate, true);
                break;
            case 5:
                View inflate2 = ((LayoutInflater) this.mContext.getSystemService(str9)).inflate(getResourceId(str2, str8), null);
                setAllView(this.mContext.getResources().getString(getResourceId("write_recovery_key_title", str12)), this.mContext.getResources().getString(getResourceId(str, str12)), null, inflate2, true);
                break;
            case 6:
                View recoveryLayoutWithInvisibleComponent = getRecoveryLayoutWithInvisibleComponent();
                setAllView(String.format(this.mContext.getString(getResourceId(str7, str12)), new Object[]{valueOf}), this.mContext.getResources().getString(getResourceId(str6, str12)), null, recoveryLayoutWithInvisibleComponent, true);
                break;
            case 7:
                View recoveryLayoutWithInvisibleComponent2 = getRecoveryLayoutWithInvisibleComponent();
                setAllView(String.format(this.mContext.getString(getResourceId(str7, str12)), new Object[]{Integer.valueOf(2)}), this.mContext.getResources().getString(getResourceId(str6, str12)), null, recoveryLayoutWithInvisibleComponent2, true);
                break;
            case 8:
                View recoveryLayoutWithInvisibleComponent3 = getRecoveryLayoutWithInvisibleComponent();
                setAllView(String.format(this.mContext.getString(getResourceId(str7, str12)), new Object[]{Integer.valueOf(3)}), this.mContext.getResources().getString(getResourceId(str6, str12)), null, recoveryLayoutWithInvisibleComponent3, true);
                break;
            case 9:
                View recoveryLayoutWithInvisibleComponent4 = getRecoveryLayoutWithInvisibleComponent();
                setAllView(String.format(this.mContext.getString(getResourceId(str5, str12)), new Object[]{valueOf}), this.mContext.getResources().getString(getResourceId(str4, str12)), null, recoveryLayoutWithInvisibleComponent4, true);
                break;
            case 10:
                View recoveryLayoutWithInvisibleComponent5 = getRecoveryLayoutWithInvisibleComponent();
                setAllView(String.format(this.mContext.getString(getResourceId(str5, str12)), new Object[]{Integer.valueOf(2)}), this.mContext.getResources().getString(getResourceId(str4, str12)), null, recoveryLayoutWithInvisibleComponent5, true);
                break;
            case 11:
                View recoveryLayoutWithInvisibleComponent6 = getRecoveryLayoutWithInvisibleComponent();
                setAllView(String.format(this.mContext.getString(getResourceId(str5, str12)), new Object[]{Integer.valueOf(3)}), this.mContext.getResources().getString(getResourceId(str4, str12)), null, recoveryLayoutWithInvisibleComponent6, true);
                break;
            case 12:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_to_unlock_your_wallet_title", str12)), null, null, null, true);
                break;
            case 13:
                setAllView(this.mContext.getResources().getString(getResourceId("passcode_required", str12)), null, null, null, true);
                break;
            case 14:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_change_pin_title", str12)), null, this.mContext.getResources().getString(getResourceId("enter_pin_change_subtitle", str12)), null, true);
                break;
            case 15:
                setAllView(this.mContext.getResources().getString(getResourceId(str10, str12)), this.mContext.getResources().getString(getResourceId(str11, str12)), null, null, true);
                break;
            case 16:
                setAllView(this.mContext.getResources().getString(getResourceId(str3, str12)), this.mContext.getResources().getString(getResourceId(str11, str12)), null, null, false);
                break;
            case 17:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_backup_security_check_title", str12)), this.mContext.getResources().getString(getResourceId("enter_pin_backup_security_check_desc", str12)), this.mContext.getResources().getString(getResourceId("enter_pin_to_continue_title", str12)), null, true);
                break;
            case 18:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_log_out_title", str12)), this.mContext.getResources().getString(getResourceId("enter_pin_log_out_desc", str12)), this.mContext.getResources().getString(getResourceId("enter_pin_log_out_subtitle", str12)), null, true);
                break;
            case 19:
                setAllView(this.mContext.getResources().getString(getResourceId("confirm_remove_trusted_contact_title", str12)), null, this.mContext.getResources().getString(getResourceId("enter_pin_to_remove_desc", str12)), null, true);
                break;
            case 20:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_to_generate_verification_code_title", str12)), null, null, null, true);
                break;
            case 21:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_show_recovery_key_title", str12)), null, this.mContext.getResources().getString(getResourceId("enter_pin_show_subtitle", str12)), null, true);
                break;
            case 22:
                View inflate3 = ((LayoutInflater) this.mContext.getSystemService(str9)).inflate(getResourceId(str2, str8), null);
                setAllView(this.mContext.getResources().getString(getResourceId("show_recovery_key_title", str12)), this.mContext.getResources().getString(getResourceId(str, str12)), null, inflate3, true);
                break;
            case 23:
                setAllView(this.mContext.getResources().getString(getResourceId("enter_pin_reset_wallet_title", str12)), null, this.mContext.getResources().getString(getResourceId("enter_pin_reset_wallet_subtitle", str12)), null, true);
                break;
            case 24:
                this.mDialogTitle.setText(getResourceId("reset_wallet_dialog_title", str12));
                this.mMessage.setText(getResourceId("reset_wallet_dialog_description", str12));
                this.mPosButton.setText(getResourceId("reset_wallet_confirm", str12));
                this.mNegButton.setText(getResourceId("reset_wallet_cancel", str12));
                break;
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                BasePageHolder basePageHolder = this.pageHolder;
                if (basePageHolder == null) {
                    ZKMALog.m275i(TAG, "pageHolder is null!!!");
                    break;
                } else {
                    return basePageHolder.getView(this.mHwSecurityUi);
                }
            case 37:
            case 38:
            case 39:
            case 40:
                setTransactionBtnText("cancel");
                setTransactionBtnBackground(this.mHwSecurityUi);
                break;
            case 41:
            case 42:
            case 43:
            case 44:
                setTransactionBtnText("text_review_send_button");
                setTransactionBtnBackground(this.mHwSecurityUi);
                break;
        }
        if (this.mHwSecurityUi == HW_SECURITY_UI.RESET_WALLET_2) {
            createLinearLayout("parentPanel");
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.NEXT_BUTTON) {
            createRelativeLayout("next_button_layout");
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.CREATE_PIN_ERROR_MSG || this.mHwSecurityUi == HW_SECURITY_UI.CONFIRM_PIN_ERROR_MSG) {
            createTextViewLayout("pin_error_message");
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.RECOVERY_ERROR_MSG) {
            createTextViewLayout("recovery_error_message");
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.RECOVERY_INVALID_MSG) {
            createTextViewLayout("recovery_invalid_message");
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.REVIEW_ERROR_MSG) {
            createTextViewLayout("review_error_message");
        } else if (this.mHwSecurityUi == HW_SECURITY_UI.BTC_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.BTC_SEND_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.ETH_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.ETH_SEND_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.LTC_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.LTC_SEND_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.BAT_CANCEL_BUTTON || this.mHwSecurityUi == HW_SECURITY_UI.BAT_SEND_BUTTON) {
            createRelativeLayout("button_layout");
        } else {
            this.mView.measure(MeasureSpec.makeMeasureSpec(this.mWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mHeight, 1073741824));
            ViewGroup viewGroup = this.mView;
            viewGroup.layout(0, 0, viewGroup.getMeasuredWidth(), this.mView.getMeasuredHeight());
        }
        return this.mView;
    }

    public void setAllView(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable View view, @Nullable boolean z) {
        if (!(this.mTitle == null || str == null)) {
            setViewTitle(str);
        }
        if (this.mDescription != null) {
            if (str2 == null) {
                setViewDescriptionInvisible();
            } else {
                setViewDescription(str2);
            }
        }
        if (!(this.mSubtitle == null || str3 == null)) {
            setViewSubtitleVisible();
            setViewSubtitle(str3);
        }
        if (!(this.mProgBar1 == null || this.mProgBar2 == null || this.mProgBar3 == null)) {
            setProgressInvisible();
        }
        if (!(this.mDynamicContent == null || view == null)) {
            setFrameLayout(view);
        }
        setSecureBarVisible();
        ImageButton imageButton = this.mBackButton;
        if (imageButton != null && !z) {
            imageButton.setVisibility(4);
        }
    }

    public void setViewTitle(String str) {
        this.mTitle.setText(str);
    }

    private void setViewDescription(String str) {
        this.mDescription.setText(str);
    }

    private void setViewDescriptionInvisible() {
        this.mDescription.setVisibility(4);
    }

    private void setViewSubtitleVisible() {
        this.mSubtitle.setVisibility(0);
    }

    private void setViewSubtitle(String str) {
        this.mSubtitle.setText(str);
    }

    private void setFrameLayout(View view) {
        this.mDynamicContent.removeAllViews();
        this.mDynamicContent.addView(view);
    }

    private void setProgressvalue(int i, int i2, int i3) {
        this.mProgBar1.setProgress(i);
        this.mProgBar2.setProgress(i2);
        this.mProgBar3.setProgress(i3);
    }

    private void setProgressInvisible() {
        this.mProgBar1.setVisibility(4);
        this.mProgBar2.setVisibility(4);
        this.mProgBar3.setVisibility(4);
    }

    private void setSecureBarVisible() {
        this.mView.findViewById(getResourceId("secure_layout", CommonProperties.f657ID)).setVisibility(0);
    }

    private View getRecoveryLayoutWithInvisibleComponent() {
        return ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(getResourceId("layout_recovery_for_exodus", "layout"), null);
    }

    private void setTransactionBtnText(String str) {
        Button button = this.mTransactionButton;
        if (button != null) {
            button.setText(getResourceId(str, "string"));
        }
    }

    private void setTransactionBtnBackground(HW_SECURITY_UI hw_security_ui) {
        int i;
        this.mTransactionBtnBackground = this.mContext.getResources().getDrawable(getResourceId("review_button_background", "drawable"));
        int i2 = C22771.$SwitchMap$com$htc$htcwalletsdk$CONSTANT$HW_SECURITY_UI[hw_security_ui.ordinal()];
        String str = Kinds.COLOR;
        switch (i2) {
            case 37:
            case 41:
                String str2 = "colorReviewBTC";
                if (VERSION.SDK_INT < 23) {
                    i = this.mContext.getResources().getColor(getResourceId(str2, str));
                    break;
                } else {
                    i = this.mContext.getColor(getResourceId(str2, str));
                    break;
                }
            case 38:
            case 42:
                String str3 = "colorReviewETH";
                if (VERSION.SDK_INT < 23) {
                    i = this.mContext.getResources().getColor(getResourceId(str3, str));
                    break;
                } else {
                    i = this.mContext.getColor(getResourceId(str3, str));
                    break;
                }
            case 39:
            case 43:
                String str4 = "colorReviewLTC";
                if (VERSION.SDK_INT < 23) {
                    i = this.mContext.getResources().getColor(getResourceId(str4, str));
                    break;
                } else {
                    i = this.mContext.getColor(getResourceId(str4, str));
                    break;
                }
            case 40:
            case 44:
                String str5 = "colorReviewBAT";
                if (VERSION.SDK_INT < 23) {
                    i = this.mContext.getResources().getColor(getResourceId(str5, str));
                    break;
                } else {
                    i = this.mContext.getColor(getResourceId(str5, str));
                    break;
                }
            default:
                String str6 = "colorReviewDefault";
                if (VERSION.SDK_INT < 23) {
                    i = this.mContext.getResources().getColor(getResourceId(str6, str));
                    break;
                } else {
                    i = this.mContext.getColor(getResourceId(str6, str));
                    break;
                }
        }
        this.mTransactionBtnBackground.setColorFilter(i, Mode.MULTIPLY);
        Drawable drawable = this.mTransactionBtnBackground;
        if (drawable != null) {
            this.mTransactionButton.setBackground(drawable);
        }
    }

    private void createLinearLayout(String str) {
        LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(getResourceId(str, CommonProperties.f657ID));
        this.mView.measure(MeasureSpec.makeMeasureSpec(this.mWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mHeight, 1073741824));
        this.mView.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight());
    }

    private void createRelativeLayout(String str) {
        RelativeLayout relativeLayout = (RelativeLayout) this.mView.findViewById(getResourceId(str, CommonProperties.f657ID));
        this.mView.measure(MeasureSpec.makeMeasureSpec(this.mWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mHeight, 1073741824));
        this.mView.layout(0, 0, relativeLayout.getMeasuredWidth(), relativeLayout.getMeasuredHeight());
    }

    private void createTextViewLayout(String str) {
        TextView textView = (TextView) this.mView.findViewById(getResourceId(str, CommonProperties.f657ID));
        this.mView.measure(MeasureSpec.makeMeasureSpec(this.mWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mHeight, 1073741824));
        ViewGroup viewGroup = this.mView;
        viewGroup.layout(0, 0, viewGroup.getMeasuredWidth(), textView.getMeasuredHeight());
    }
}
