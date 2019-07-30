package com.horizon.app.core.maybeUse.delegates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.horizon.app.core.R;
import com.horizon.app.core.maybeUse.launcher.ILauncherListener;
import com.horizon.app.core.maybeUse.launcher.LauncherHolderCreator;
import com.horizon.app.core.maybeUse.launcher.LauncherTag;
import com.horizon.app.core.maybeUse.util.HorizonPreference;

import java.util.ArrayList;

//滚动启动器
public class LauncherScrollDelegate extends HorizonDelegate implements OnItemClickListener {

    //传入图片
    private ConvenientBanner<Integer> mConvenientBanner = null;
    //图片容器
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            HorizonPreference.setAppFlag(LauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            HorizonPreference.setAppFlag(LauncherTag.SIGNED.name(),1);
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }
}
