package com.fuicuiedu.xc.easyshop_20170413.user.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public interface LoginView extends MvpView{

    void showPrb();

    void hidePrb();

    void loginFailed();

    void loginSuccess();

    void showMsg(String msg);
}
