package com.techangkeji.module_friend_circle.ui.popupwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

import com.alibaba.android.arouter.utils.TextUtils;
import com.techangkeji.module_friend_circle.R;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.utils.IsNullUtil;

/**
 * description:
 * author:created by Andy on 2019/9/19 0019 10:35
 * email:zsp872126510@gmail.com
 */
public class InputTextMsgDialog extends AppCompatDialog {
    private Context mContext;
    private InputMethodManager imm;
    private EditText messageTextView;
    private TextView confirmBtn;
    private RelativeLayout rlDlg;
    private int mLastDiff = 0;
    private TextView tvNumber;
    private int maxNumber = 200;
    private CommentBody commentBody;

    public interface OnTextSendListener {

        void onTextSend(String msg);
    }


    private OnTextSendListener mOnTextSendListener;

    public InputTextMsgDialog(@NonNull Context context, int theme,CommentBody commentBody) {
        super(context, theme);
        this.mContext = context;
        this.commentBody=commentBody;
        this.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        init();
        setLayout();

    }

    /**
     * 最大输入字数  默认200
     */
    @SuppressLint("SetTextI18n")
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
        tvNumber.setText("0/" + maxNumber);
    }

    /**
     * 设置输入提示文字
     */
    public void setHint(String text) {
        messageTextView.setHint(text);
    }

    /**
     * 设置按钮的文字  默认为：发送
     */
    public void setBtnText(String text) {
        confirmBtn.setText(text);
    }

    private void init() {

        setContentView(R.layout.dialog_input_text_msg);
        messageTextView = (EditText) findViewById(R.id.et_input_message);
        tvNumber = (TextView) findViewById(R.id.tv_test);
        final LinearLayout rldlgview = (LinearLayout) findViewById(R.id.rl_inputdlg_view);
        messageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvNumber.setText(editable.length() + "/" + maxNumber);
                if (editable.length() > maxNumber) {
                    /*dot_hong颜色值：#E73111,text_bottom_comment颜色值：#9B9B9B*/
                    tvNumber.setTextColor(mContext.getResources().getColor(R.color.color_FF8C00));
                } else {
                    tvNumber.setTextColor(mContext.getResources().getColor(R.color.color_dark_333333));
                }
                if (editable.length() == 0) {
                    confirmBtn.setBackgroundResource(R.drawable.btn_send_normal);
                } else {
                    confirmBtn.setBackgroundResource(R.drawable.btn_send_pressed);
                }
            }
        });


        confirmBtn = (TextView) findViewById(R.id.confrim_btn);
        LinearLayout ll_tv = findViewById(R.id.ll_tv);
        imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        ll_tv.setOnClickListener(view -> {
            String msg = messageTextView.getText().toString().trim();
            if (msg.length() > maxNumber) {
                Toast.makeText(mContext, "超过最大字数限制", Toast.LENGTH_LONG).show();
                return;
            }
            if (!TextUtils.isEmpty(msg)|| !IsNullUtil.getInstance().isEmpty(commentBody)) {
                mOnTextSendListener.onTextSend(msg);
                imm.showSoftInput(messageTextView, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                messageTextView.setText("");
                commentBody.setContent(msg);
                RxBus.getDefault().post(commentBody);
                dismiss();
            } else {
                Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
            }
            messageTextView.setText(null);
        });

        messageTextView.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case KeyEvent.KEYCODE_ENDCALL:
                case KeyEvent.KEYCODE_ENTER:
                    if (messageTextView.getText().length() > maxNumber) {
                        Toast.makeText(mContext, "超过最大字数限制", Toast.LENGTH_LONG).show();
                        return true;
                    }
                    if (messageTextView.getText().length() > 0) {
                        imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                        dismiss();
                    } else {
                        Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
                    }
                    return true;
                case KeyEvent.KEYCODE_BACK:
                    dismiss();
                    return false;
                default:
                    return false;
            }
        });

        messageTextView.setOnKeyListener((view, i, keyEvent) -> {
            Log.d("My test", "onKey " + keyEvent.getCharacters());
            return false;
        });

        rlDlg = findViewById(R.id.rl_outside_view);
        rlDlg.setOnClickListener(v -> {
            if (v.getId() != R.id.rl_inputdlg_view)
                dismiss();
        });

        rldlgview.addOnLayoutChangeListener((view, i, i1, i2, i3, i4, i5, i6, i7) -> {
            Rect r = new Rect();
            //获取当前界面可视部分
            getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
            //获取屏幕的高度
            int screenHeight = getWindow().getDecorView().getRootView().getHeight();
            //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
            int heightDifference = screenHeight - r.bottom;

            if (heightDifference <= 0 && mLastDiff > 0) {
                dismiss();
            }
            mLastDiff = heightDifference;
        });
        rldlgview.setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
            dismiss();
        });

        setOnKeyListener((dialogInterface, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                dismiss();
            return false;
        });
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.mOnTextSendListener = onTextSendListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //dismiss之前重置mLastDiff值避免下次无法打开
        mLastDiff = 0;
    }

    @Override
    public void show() {
        super.show();
    }
}
