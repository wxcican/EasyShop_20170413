package com.fuicuiedu.xc.easyshop_20170413.main.me.goodsupload;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public interface GoodsUpLoadView extends MvpView{

    void showPrb();

    void hidePrb();

    void upLoadSuccess();

    void showMsg(String msg);
}
