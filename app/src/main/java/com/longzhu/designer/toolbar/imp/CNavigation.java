package com.longzhu.designer.toolbar.imp;

import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.longzhu.designer.R;


/**
 * 该类满足左中右导航条需求（目的：抽象所有的导航条左中右导航条公共功能）
 * 抽象某种场景下的导航条功能（左、右）
 */

public abstract class CNavigation<P extends AbsNavigation.AbsNavigationParam> extends AbsNavigation<P> {
    public CNavigation(P param) {
        super(param);
    }


    @Override
    public int bindLayoutId() {
        return R.layout.navigation_default_layout;
    }

    @Override
    public void build() {
        //千万不能忘记
        super.build();
        //动态的场景导航条布局
        LinearLayout centerLinearLayout = createChildLayout(2.0f, Color.YELLOW);

        //回调定义好的规范方法
        bindParent(bindCenterLayoutId(), centerLinearLayout);

        initCenterLayout(centerLinearLayout);
    }

    /**
     * 绑定中间布局
     *
     * @return
     */
    public abstract int bindCenterLayoutId();


    /**
     * 初始化中间布局
     *
     * @param parent
     */
    public void initCenterLayout(LinearLayout parent) {
    }

    /**
     * 动态创建布局
     *
     * @return
     */
    private LinearLayout createChildLayout(float weight, int color) {
        LinearLayout linearLayout = new LinearLayout(getParam().context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.weight = weight;
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        linearLayout.setBackgroundColor(color);
        linearLayout.setLayoutParams(layoutParams);
        //绑定父容器布局
        bindParent(linearLayout, (ViewGroup) getContentView());
        return linearLayout;
    }
}
