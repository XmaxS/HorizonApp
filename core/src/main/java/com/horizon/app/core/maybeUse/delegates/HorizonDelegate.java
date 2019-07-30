package com.horizon.app.core.maybeUse.delegates;

//ButterKnife是一个专注于Android系统的View注入框架,
// 以前总是要写很多findViewById来找到View对象，
// 有了ButterKnife可以很轻松的省去这些步骤。
public abstract class HorizonDelegate extends BaseDelegate {

    @SuppressWarnings("unchecked")
    public <T extends HorizonDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }

}
