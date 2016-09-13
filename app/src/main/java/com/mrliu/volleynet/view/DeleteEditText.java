package com.mrliu.volleynet.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;


/**
 * 作者：liubin1 on 2016/3/24 14:28
 * 该类的说明：带删除的EdiText
 * 修改历史：
 */

public class DeleteEditText extends EditText {
    private Drawable d;
    private Rect r;

    public DeleteEditText(Context paramContext) {
        super(paramContext);
        initEditText();
    }

    public DeleteEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        initEditText();
    }

    public DeleteEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initEditText();
    }

    // 初始化edittext 控件
    private void initEditText() {
        setEditTextDrawable();
        addTextChangedListener(new TextWatcher() { // 对文本内容改变进行监听
            @Override
            public void afterTextChanged(Editable paramEditable) {
            }

            @Override
            public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
            }

            @Override
            public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
                DeleteEditText.this.setEditTextDrawable();
            }
        });
    }

    public void setEditTextDrawable() {
        if (getText().toString().length() == 0) {
            setCompoundDrawables(null, null, null, null);
        } else {
            setCompoundDrawables(null, null, this.d, null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d = null;
        this.r = null;
    }

    /**
     * 左上右下
     */
    @Override
    public void setCompoundDrawables(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4) {
        if (paramDrawable3 != null)
            this.d = paramDrawable3;
        super.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent p) {
        if ((this.d != null) && (p.getAction() == MotionEvent.ACTION_UP)) {
            this.r = this.d.getBounds();
            int i = (int) p.getRawX();
            if (i > getRight() - this.r.width()) {
                setText("");
                p.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.onTouchEvent(p);
    }
}
