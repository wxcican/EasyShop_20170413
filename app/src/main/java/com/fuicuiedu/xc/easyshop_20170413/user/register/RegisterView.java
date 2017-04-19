package com.fuicuiedu.xc.easyshop_20170413.user.register;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public interface RegisterView extends MvpView{

    //显示进度条
    void showPrb();
    //隐藏进度条
    void hidePrb();
    //注册成功
    void registerSuccess();
    //注册失败
    void registerFailed();
    //提示信息
    void showMsg(String msg);
    //用户名密码不对时提示用户
    void showUserPasswordError(String msg);

}
