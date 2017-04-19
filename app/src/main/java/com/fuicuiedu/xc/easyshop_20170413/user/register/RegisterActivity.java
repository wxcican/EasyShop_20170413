package com.fuicuiedu.xc.easyshop_20170413.user.register;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuicuiedu.xc.easyshop_20170413.R;
import com.fuicuiedu.xc.easyshop_20170413.commons.ActivityUtils;
import com.fuicuiedu.xc.easyshop_20170413.commons.RegexUtils;
import com.fuicuiedu.xc.easyshop_20170413.components.AlertDialogFragment;
import com.fuicuiedu.xc.easyshop_20170413.components.ProgressDialogFragment;
import com.fuicuiedu.xc.easyshop_20170413.main.MainActivity;
import com.fuicuiedu.xc.easyshop_20170413.model.UserResult;
import com.fuicuiedu.xc.easyshop_20170413.network.EasyShopClient;
import com.fuicuiedu.xc.easyshop_20170413.network.UICallBack;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends MvpActivity<RegisterView,RegisterPresenter> implements RegisterView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    EditText et_userName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_pwdAgain)
    EditText et_pwdAgain;
    @BindView(R.id.btn_register)
    Button btn_register;

    private String username;
    private String password;
    private String pwd_again;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

        init();

    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();//创建注册的业务类
    }

    private void init() {
        //给左上角加一个返回图标，需要重写菜单点击事件，否则点击无效
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_userName.addTextChangedListener(textWatcher);
        et_pwd.addTextChangedListener(textWatcher);
        et_pwdAgain.addTextChangedListener(textWatcher);
    }

    //给左上角加一个返回图标，需要重写菜单点击事件，否则点击无效
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果点击的是返回键，则finish
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            username = et_userName.getText().toString();
            password = et_pwd.getText().toString();
            pwd_again = et_pwdAgain.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(pwd_again));
            btn_register.setEnabled(canLogin);
        }
    };

    @OnClick(R.id.btn_register)
    public void onClick() {
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.username_rules);
            return;
        } else if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.password_rules);
            return;
        } else if (!TextUtils.equals(password, pwd_again)) {
            activityUtils.showToast(R.string.username_equal_pwd);
            return;
        }

        //业务类执行注册的业务
        presenter.register(username,password);

    }


    //###################################   视图接口的实现   ######################
    @Override
    public void showPrb() {
        //关闭软键盘
        activityUtils.hideSoftKeyboard();
        //进度条
        //初始化进度条
        if (dialogFragment == null)dialogFragment = new ProgressDialogFragment();
        //如果进度条已经显示，则跳出
        if (dialogFragment.isVisible()) return;
        //否则进度条显示
        dialogFragment.show(getSupportFragmentManager(),"dialogFragment");
    }

    @Override
    public void hidePrb() {
        dialogFragment.dismiss();
    }

    @Override
    public void registerSuccess() {
        //成功跳转到主页
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void registerFailed() {
        et_userName.setText("");
    }

    @Override
    public void showMsg(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void showUserPasswordError(String msg) {
        //展示弹窗，提示错误信息
        AlertDialogFragment fragment = AlertDialogFragment.newInstance(msg);
        fragment.show(getSupportFragmentManager(),getString(R.string.username_pwd_rule));
    }
}
