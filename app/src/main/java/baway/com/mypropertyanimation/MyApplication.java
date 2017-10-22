package baway.com.mypropertyanimation;

import android.app.Application;

/**
 * Created by Administrator on 2017/10/11.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //全局异常捕获
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
    }
}
