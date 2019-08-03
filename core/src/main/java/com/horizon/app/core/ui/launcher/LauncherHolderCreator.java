package com.horizon.app.core.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
