package org.xutils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wyouflf on 15/10/29.
 * view注入接口
 */
public interface ViewInjector {

    /**
     * 注入view
     *
     * @param view 待注入的view
     */
    void inject(View view);

    /**
     * 注入activity
     *
     * @param activity 待注入的activity
     */
    void inject(Activity activity);

    /**
     * 注入view holder
     *
     * @param handler view holder
     * @param view 待注入的view
     */
    void inject(Object handler, View view);

    /**
     * 注入fragment
     *
     * @param fragment 待注入的fragment
     * @param inflater view解析器
     * @param container view的父类
     * @return 该fragment的content view
     */
    View inject(Object fragment, LayoutInflater inflater, ViewGroup container);
}
