package androidlab.edu.cn.livedemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.tencent.rtmp.TXLiveBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.start_push_btn)
    Button mStartPushBtn;
    @BindView(R.id.see_btn)
    Button mSeeBtn;
    private AVUser mAVUser = new AVUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String sdkver = TXLiveBase.getSDKVersionStr();

        Log.d("liteavsdk", "liteav sdk version is : " + sdkver);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA}, 100);
        }
        if (null == AVUser.getCurrentUser()) {
            AVUser.logInInBackground("杨浩东", "478214", new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (null == e) {
                        Log.i(TAG, "done: login success");
                    } else {
                        Log.e(TAG, "done: " + e.getMessage());
                    }
                }
            });

        } else {
            mAVUser = AVUser.getCurrentUser();
            Log.i(TAG, "onCreate: "+mAVUser.getObjectId().toString());
        }
    }


    @OnClick({R.id.start_push_btn, R.id.see_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_push_btn:
                Intent mIntent = new Intent(this, PushActivity.class);
                startActivity(mIntent);
                break;
            case R.id.see_btn:
                Intent mIntent2 = new Intent(this,PlayActivity.class);
                startActivity(mIntent2);
                break;
        }
    }
}
