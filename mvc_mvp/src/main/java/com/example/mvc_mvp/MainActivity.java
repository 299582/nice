package com.example.mvc_mvp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvc_mvp.model.UserBean;
import com.example.mvc_mvp.presenter.LoginPresenter;
import com.example.mvc_mvp.presenter.LoginPresenterInterface;

public class MainActivity extends AppCompatActivity implements LoginPresenterInterface {
    private EditText uname,pwd;
    private ProgressDialog progressDialog;
    private String name;
    private String pwds;
    private UserBean userBean;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        loginPresenter = new LoginPresenter(this);


    }

    public void login(View view){
        name = uname.getText().toString();
        pwds = pwd.getText().toString();
        userBean = new UserBean();
        userBean.username = name;
        userBean.password = pwds;


        loginPresenter.submit(userBean);

    }


    @Override
    public void success() {
        progressDialog.dismiss();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void failed() {
        progressDialog.dismiss();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登陆失败！账号或密码不对", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void kong() {

        Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show() {
        progressDialog.show();
    }
}
