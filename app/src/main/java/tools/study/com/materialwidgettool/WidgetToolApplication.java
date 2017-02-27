package tools.study.com.materialwidgettool;

import android.app.Application;

import tools.study.com.materialwidgettool.swipeback.library.ActivityStack;

/**
 * @author suzhuning
 * @date 2016/11/16.
 * Description:
 */
public class WidgetToolApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        this.registerActivityLifecycleCallbacks(ActivityStack.getInstance());
    }

    public void exitApp(){
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
