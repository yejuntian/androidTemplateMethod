package com.longzhu.designer.toolbar.imp;

import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.longzhu.designer.R;


/**
 * 该类满足左中右导航条需求（目的：抽象所有的导航条左中右导航条公共功能）
 * 抽象某种场景下的导航条功能（左、中、右）
 */

public abstract class LCRNavigation<P extends AbsNavigation.AbsNavigationParam> extends AbsNavigation<P> {
    public LCRNavigation(P param) {
        super(param);
    }


    @Override
    public int bindLayoutId() {
        return R.layout.navigation_default_layout;
    }

    @Override
    public final void build() {
        //千万不能忘记
        super.build();
        //动态的场景导航条布局
        float rightLayoutWeight = bindRightLayoutWeight();
        if(rightLayoutWeight != DEFALT_LAYOUT_WEIGHT){
            LinearLayout rightLinearLayout = createChildLayout(rightLayoutWeight, Color.BLUE);
            //回调定义好的规范方法
            bindParent(bindRightLayoutId(), rightLinearLayout);
            initRightLayout(rightLinearLayout);
        }

        float centerLayoutWeight = bindCenterLayoutWeight();
        if(centerLayoutWeight !=DEFALT_LAYOUT_WEIGHT){
            LinearLayout centerLinearLayout = createChildLayout(centerLayoutWeight, Color.YELLOW);
            bindParent(bindCenterLayoutId(), centerLinearLayout);
            initCenterLayout(centerLinearLayout);
        }

        float leftLayoutWeight =  bindLeftLayoutWeight();
        if(leftLayoutWeight != DEFALT_LAYOUT_WEIGHT){
            LinearLayout leftLinearLayout = createChildLayout(leftLayoutWeight, Color.RED);
            bindParent(bindLeftLayoutId(), leftLinearLayout);
            initLeftLayout(leftLinearLayout);
        }
    }

    /**
     * 左边布局比重
     */
    public abstract float bindLeftLayoutWeight();

    /**
     * 中边布局比重
     */
    public abstract float bindCenterLayoutWeight();

    /**
     * 右边布局比重
     */
    public abstract float bindRightLayoutWeight();

    /**
     * 绑定左边布局
     *
     * @return
     */
    public abstract int bindLeftLayoutId();

    /**
     * 绑定中间布局
     *
     * @return
     */
    public abstract int bindCenterLayoutId();

    /**
     * 绑定右边布局
     *
     * @return
     */
    public abstract int bindRightLayoutId();

    /**
     * 初始化左边布局
     *
     * @param parent
     */
    public void initLeftLayout(LinearLayout parent) {

    }

    /**
     * 初始化中间布局
     *
     * @param parent
     */
    public void initCenterLayout(LinearLayout parent) {
    }

    /**
     * 初始化右边布局
     *
     * @param parent
     */
    public void initRightLayout(LinearLayout parent) {
    }

    /**
     * 动态创建布局
     *
     * @return
     */
    private LinearLayout createChildLayout(float weight,int color) {
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
