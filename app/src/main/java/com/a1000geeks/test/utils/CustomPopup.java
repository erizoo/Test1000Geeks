package com.a1000geeks.test.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.a1000geeks.test.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomPopup extends PopupWindow {

    public CustomPopup(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public void setUp(View contentView) {
        setElevation(6.0f);
        setFocusable(true);
        showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }

}
