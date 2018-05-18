package com.example.mvc_mvp.presenter;

import android.os.SystemClock;
import android.text.TextUtils;
import com.example.mvc_mvp.model.UserBean;

public class LoginPresenter implements LoginPresenterInterface{
    public void submit(UserBean userBean) {
        if (TextUtils.isEmpty(userBean.username) || TextUtils.isEmpty(userBean.password)) {

            loginPresenterInterface.kong();
        }else{
            loginPresenterInterface.show();
            UserLoginPresenter(userBean);

        }

    }

    public void UserLoginPresenter(final UserBean userBean){



            new Thread(){
                @Override
                public void run() {
                    super.run();
                    SystemClock.sleep(2000);

                    if ("cx".equals(userBean.username) && "cx".equals(userBean.password)){
                        loginPresenterInterface.success();
                    }else{
                        loginPresenterInterface.failed();
                    }
                }
            }.start();
        }






    private final LoginPresenterInterface loginPresenterInterface;

    public LoginPresenter(LoginPresenterInterface loginPresenterInterface){
        this.loginPresenterInterface = loginPresenterInterface;
    }


    @Override
    public void success() {

    }

    @Override
    public void failed() {

    }

    @Override
    public void kong() {

    }

    @Override
    public void show() {

    }

}
