package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.base.BasePresenter;

import java.util.Map;

public abstract class BuyColumnPresenter<T,E> extends BasePresenter<T,E> {

    public abstract void postBuySpecColumn(String url, Map<String, String> params);
}
