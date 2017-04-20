package com.fuicuiedu.xc.easyshop_20170413.main.me.personInfo;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public interface PersonView extends MvpView{

    void showPrb();

    void hidePrb();

    void showMsg(String msg);
    //用来更新头像
    void updataAvatar(String url);
}
