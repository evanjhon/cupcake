package com.evan.cupcake.App;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.evan.cupcake.util.ImageLoaderUtils;

/**
 * Created by Evan on 2018/12/19.
 * des
 */

public class MyApplication extends Application {
    public static int Width, Height;
    public static int titleHeight = 0;

    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        WindowManager();
        WindowTitle();
        ImageLoaderUtils.init(CONTEXT);
    }

    public static void WindowManager() {
        WindowManager wm = (WindowManager) CONTEXT.getSystemService(Context.WINDOW_SERVICE);
        Width = wm.getDefaultDisplay().getWidth();
        Height = wm.getDefaultDisplay().getHeight();
    }

    public static void WindowTitle() {
        int resourceId = CONTEXT.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            titleHeight = CONTEXT.getResources().getDimensionPixelSize(resourceId);
        }

    }

}
