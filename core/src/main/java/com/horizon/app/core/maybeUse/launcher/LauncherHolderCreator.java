package com.horizon.app.core.maybeUse.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class LauncherHolderCreator implements CBViewHolderCreator {
    @Override
    public Object createHolder() {
        return new LauncherHolder();
    }
}
