package com.example.jnc.testDemo;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


//extends AppCompatActivity implements View.OnClickListener
public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText editName,editpwd;
    private Object packageName;
    private String url ="";
    public static int screenConfig=0;
    private String TAG;


    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configuration configuration = getResources().getConfiguration();
        //screenConfig = getResources().getConfiguration().screenLayout;
        //Toast.makeText(MainActivity.this,screenConfig,Toast.LENGTH_LONG).show();
        //ImageView imageView = this.<ImageView>findViewById(R.id.main_image);
        //ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));//初始化完成
        //ImageLoader.getInstance().displayImage(imageUrl,imageView);
       /* UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        if (uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION) {
            Log.d(TAG, "Running on a TV Device");
        } else {
            Log.d(TAG,"Running on a non-TV Device");
        }*/
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       *//* TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView1);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        tv1.setText("imei:" + tm.getDeviceSoftwareVersion());
        tv2.setText("imsi:" + tm.getSubscriberId());
*//*
        editName =(EditText) findViewById(R.id.editText3);
        editpwd =(EditText) findViewById(R.id.editText5);
        loginBtn =(Button) findViewById(R.id.button);
        loginBtn.setOnClickListener(this);
    }
        public boolean isCorrectInfo(String userName,String userPwd) {
            if ("test".equals(userName) && "123".equals(userPwd))
                return true;
            else
                return false;
        }

    @Override
    public void onClick(View view) {
        String userName = editName.getText() + "";
        String userPwd = editpwd.getText() + "";
        if (isCorrectInfo(userName,userPwd)){
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
    }*/
}
