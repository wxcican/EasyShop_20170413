package com.fuicuiedu.xc.easyshop_20170413.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fuicuiedu.xc.easyshop_20170413.R;
import com.fuicuiedu.xc.easyshop_20170413.commons.ActivityUtils;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindViews({R.id.tv_shop,R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    TextView[] textViews;
    @BindView(R.id.main_toobar)
    Toolbar toolbar;
    @BindView(R.id.main_title)
    TextView tv_title;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private ActivityUtils activityUtils;

    //点击2次返回，退出程序
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

    }

    @Override
    public void onBackPressed() {
        //点击2次返回，退出程序
        if (!isExit){
            isExit = true;
            activityUtils.showToast("在摁一次退出程序！");
            //如果两秒内再次点击返回则退出
            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        }else{
            finish();
        }
    }
}
