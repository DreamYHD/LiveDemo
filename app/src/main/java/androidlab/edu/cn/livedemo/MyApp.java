package androidlab.edu.cn.livedemo;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import java.util.AbstractCollection;

/**
 * Created by dreamY on 2017/9/16.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"KElRaSz2NTiDTiMPpaHKKI4y-gzGzoHsz","EcQnyT6mczTqjcBtSXIKys3m");
    }
}