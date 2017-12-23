package com.longzhu.designer.toolbar.imp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.longzhu.designer.toolbar.INavagation;


/**
 * UI显示：该类就是产品类
 * 该类是针对所有导航条场景
 */

public abstract class AbsNavigation<P extends AbsNavigation.AbsNavigationParam> implements INavagation {
    protected static final int DEFALT_LAYOUT = 0;
    protected static final float DEFALT_LAYOUT_WEIGHT = 0f;
    private P param;
    private View contentView;

    public AbsNavigation(P param) {
        this.param = param;
    }

    @Override
    public int bindLayoutId() {
        return DEFALT_LAYOUT;
    }

    @Override
    public void build() {
        if (bindLayoutId() == DEFALT_LAYOUT) {
            return;
        }
        if (param == null || param.context == null || param.parent == null || param.inflater == null) {
            return;
        }
        if (contentView == null) {
            contentView = bindParent(bindLayoutId(),param.parent);
        }
    }

    public ViewGroup bindParent(int layoutId, ViewGroup parent) {
        if (layoutId == DEFALT_LAYOUT || parent == null) {
            return null;
        }
        View childView = param.inflater.inflate(layoutId, parent, false);
        return bindParent(childView, parent);
    }

    public ViewGroup bindParent(View childView, ViewGroup parent) {
        //将来刷新视图
        ViewParent viewParent = childView.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeAllViews();
        }

        parent.addView(childView,0);
        return (ViewGroup) childView;
    }

    public P getParam() {
        return param;
    }

    public View getContentView() {
        return contentView;
    }

    /**
     * 构建者
     */
    public abstract static class Builder {
        public Builder(Context context, ViewGroup parent) {

        }

        public abstract INavagation builder();
    }

    public static class AbsNavigationParam {
        public Context context;
        public ViewGroup parent;
        public LayoutInflater inflater;

        public AbsNavigationParam(Context context, ViewGroup parent) {

            this.context = context;
            this.parent = parent;
            inflater = LayoutInflater.from(context);
        }
    }

    public Drawable getImageRes(int res){
       return getParam().context.getResources().getDrawable(res);
    }

    public String getString(int id){
        return getParam().context.getResources().getString(id);
    }

}
