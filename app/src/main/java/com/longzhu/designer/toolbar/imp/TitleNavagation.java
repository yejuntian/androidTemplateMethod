package com.longzhu.designer.toolbar.imp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longzhu.designer.R;
import com.longzhu.designer.toolbar.INavagation;

public class TitleNavagation extends LCRNavigation<TitleNavagation.HomeNavagationParam> {

    public TitleNavagation(HomeNavagationParam param) {
        super(param);
    }

    @Override
    public float bindLeftLayoutWeight() {
        return 0;
    }

    @Override
    public float bindCenterLayoutWeight() {
        return 1;
    }

    @Override
    public float bindRightLayoutWeight() {
        return 0;
    }

    @Override
    public int bindLeftLayoutId() {
        return DEFALT_LAYOUT;
    }

    @Override
    public int bindCenterLayoutId() {
        //通过代码创建
        return R.layout.navagation_home_center;
    }

    @Override
    public int bindRightLayoutId() {
        //通过布局创建
        return R.layout.navagation_home_right;
    }

    public static class Builders extends Builder {
        HomeNavagationParam homeNavagationParam;

        public Builders(Context context, ViewGroup parent) {
            super(context, parent);
            homeNavagationParam = new HomeNavagationParam(context, parent);
        }

        @Override
        public INavagation builder() {
            return new TitleNavagation(homeNavagationParam);

        }

        public Builder setleftRes(int leftRes) {
            homeNavagationParam.leftRes = leftRes;
            return this;
        }

        public Builder setRightImageLeftRes(int rightImageLeftRes) {
            homeNavagationParam.rightImageLeftRes = rightImageLeftRes;
            return this;
        }

        public Builder setRightImageRightRes(int rightImageRightRes) {
            homeNavagationParam.rightImageRightRes = rightImageRightRes;
            return this;
        }

        public Builder setRightImageLeftOnClickListener(View.OnClickListener rightImageLeftOnClickListener) {
            homeNavagationParam.rightImageLeftOnClickListener = rightImageLeftOnClickListener;
            return this;
        }

        public Builder setRightImageRightOnClickListener(View.OnClickListener rightImageRightOnClickListener) {
            homeNavagationParam.rightImageRightOnClickListener = rightImageRightOnClickListener;
            return this;
        }


    }

    public static class HomeNavagationParam extends AbsNavigation.AbsNavigationParam {
        public int leftRes;
        public int rightImageLeftRes;
        public int rightImageRightRes;

        public View.OnClickListener rightImageLeftOnClickListener;
        public View.OnClickListener rightImageRightOnClickListener;

        public HomeNavagationParam(Context context, ViewGroup parent) {
            super(context, parent);
        }
    }

    @Override
    public void initLeftLayout(LinearLayout parent) {
    }

    @Override
    public void initCenterLayout(LinearLayout parent) {
        TextView textView = parent.findViewById(R.id.tv_title);
        textView.setText(getString(R.string.home_toolbar_name));
    }

    @Override
    public void initRightLayout(LinearLayout parent) {
        //设置点击事件
        ImageView imageView = parent.findViewById(R.id.iv);
        imageView.setImageDrawable(getImageRes(getParam().rightImageRightRes));
        imageView.setOnClickListener(getParam().rightImageRightOnClickListener);

        ImageView imageView2 = parent.findViewById(R.id.iv2);
        imageView2.setImageDrawable(getImageRes(getParam().rightImageLeftRes));
        imageView2.setOnClickListener(getParam().rightImageLeftOnClickListener);
    }
}
