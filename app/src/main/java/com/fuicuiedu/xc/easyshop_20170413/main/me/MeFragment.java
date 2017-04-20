package com.fuicuiedu.xc.easyshop_20170413.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuicuiedu.xc.easyshop_20170413.R;
import com.fuicuiedu.xc.easyshop_20170413.commons.ActivityUtils;
import com.fuicuiedu.xc.easyshop_20170413.main.me.personInfo.PersonActivity;
import com.fuicuiedu.xc.easyshop_20170413.model.CachePreferences;
import com.fuicuiedu.xc.easyshop_20170413.user.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class MeFragment extends Fragment {

    private ActivityUtils activityUtils;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_me,container,false);
            activityUtils = new ActivityUtils(this);
            ButterKnife.bind(this,view);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: 2017/4/20 0020 判断是否登录，显示昵称和头像 
    }

    @OnClick({R.id.iv_user_head,R.id.tv_person_info, R.id.tv_login, R.id.tv_person_goods, R.id.tv_goods_upload})
    public void onClick(View view){
        //需要判断用户是否登录，从而决定跳转的位置
        if (CachePreferences.getUser().getName() == null){
            activityUtils.startActivity(LoginActivity.class);
            return;
        }

        switch (view.getId()){
            case R.id.iv_user_head:
            case R.id.tv_login:
            case R.id.tv_person_info:
                //跳转到个人信息页面
                activityUtils.startActivity(PersonActivity.class);
                break;
            case R.id.tv_person_goods:
                //跳转到我的商品页面
                activityUtils.showToast("跳转到我的商品页面,待实现");
                break;
            case R.id.tv_goods_upload:
                //跳转到商品上传的页面
                activityUtils.showToast("跳转到商品上传的页面，待实现");
                break;

        }



    }


}
