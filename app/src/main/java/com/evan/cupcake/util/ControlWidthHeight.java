package com.evan.cupcake.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhf on 2018/10/19.
 */

public class ControlWidthHeight {
    /**
     * 设置View高度
     */
    public static void inputhigh(int width, View tv) {
        ViewGroup.LayoutParams para = tv.getLayoutParams();
        para.height = width;
        tv.setLayoutParams(para);
    }
}
