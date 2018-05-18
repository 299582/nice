package com.example.combination_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Sub_View extends FrameLayout implements View.OnClickListener {

    private View inflate;
    private ImageButton imgb_add;
    private ImageButton imgb_sub;
    private TextView text_value;
    private int value;
    private int values;



    public Add_Sub_View(@NonNull Context context) {
        this(context,null);
    }

    public Add_Sub_View(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Add_Sub_View(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //使用inflate加载布局并查找id
        inflate = View.inflate(context, R.layout.add_sub_layout, this);
        imgb_add = inflate.findViewById(R.id.imgb_add);
        imgb_sub = inflate.findViewById(R.id.imgb_sub);
        text_value = inflate.findViewById(R.id.text_value);
        //给两个按钮设置点击事件
        imgb_add.setOnClickListener(this);
        imgb_sub.setOnClickListener(this);
        //设置获取值的方法
        values = getValue();
        setValue();

    }

    //定义设置value值的方法
    private void setValue() {
        text_value.setText(values+"");
    }

    //定义一个设置最大值变量
    private int Maxvalue;
    //再定义一个公共方法方便让外界调用修改最大值
    public void setMaxValue(int maxValue){
         Maxvalue = maxValue ;
    }
    //定义取出value值的方法
    private int getValue() {

        //trim去除字符串的两边的空格
        String trim = text_value.getText().toString().trim();
        if(!trim.isEmpty()){
             value = Integer.valueOf(trim);
             text_value.setText(value+"");
        }
        return value;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgb_add:
                //调用添加的方法
                addNum();
                //调用重新赋值的方法
                setValue();
                //判断onButtonClickListenter这个对象是否为空，如果不加判断点击会报错(空指针)
                if(onButtonClickListenter != null){
                    onButtonClickListenter.onButtonAddOrSubClick(values);
                }
                break;
            case R.id.imgb_sub:
                //调用减少的方法
                subNum();
                //调用重新赋值的方法
                setValue();
                if(onButtonClickListenter != null){
                    onButtonClickListenter.onButtonAddOrSubClick(values);
                }
                break;

        }
    }

    //减少的方法
    private void subNum() {
        if(values>0){
            values--;
            //Toast.makeText(getContext(), ""+values, Toast.LENGTH_SHORT).show();
        }
    }

    //添加的方法
    private void addNum() {
        if(values<Maxvalue) {
            values++;
            //Toast.makeText(getContext(), values+"", Toast.LENGTH_SHORT).show();
        }
    }



    //定义接口回调的固定格式

    //2。定义接口变量
    private OnButtonClickListenter onButtonClickListenter;



    //3。设置方法可以让外界调用
    public void setOnButtonClickListenter(OnButtonClickListenter onButtonClickListenter){
        this.onButtonClickListenter = onButtonClickListenter;
    }

    //1.先定义一个借口
    public interface OnButtonClickListenter{
        //将初始值作为参数
        void onButtonAddOrSubClick(int value);

    }


}
