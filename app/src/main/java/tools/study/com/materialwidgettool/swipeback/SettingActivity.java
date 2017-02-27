package tools.study.com.materialwidgettool.swipeback;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.swipeback.library.SwipeBackPreferenceActivity;

/**
 * @author suzhuning
 * @date 2016/11/18.
 * Description:
 */
public class SettingActivity extends SwipeBackPreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //找到Activity根布局
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        //获取根布局子View
        View content = rootView.getChildAt(0);
        //加载自定义布局文件
        LinearLayout toolbarLayout = (LinearLayout) LayoutInflater.from(this)
                .inflate(R.layout.setting_preference_toolbar_layout, null);
        //移除根布局所有子view
        rootView.removeAllViews();
        //注意这里一要将前面移除的子View添加到我们自定义布局文件中，否则PreferenceActivity中的Header将不会显示
        toolbarLayout.addView(content);
        //将包含Toolbar的自定义布局添加到根布局中
        rootView.addView(toolbarLayout);
        //设置toolbar
        Toolbar toolbar=(Toolbar)toolbarLayout.findViewById(R.id.setting_toolbar);
        toolbar.setTitle("设置");
        toolbar.setTitleTextColor(Color.WHITE);
        Drawable d=getResources().getDrawable(R.drawable.ic_action_scan, getTheme());
        toolbar.setNavigationIcon(d);

        if(hasHeaders()){
            Button button = new Button(this);
            button.setText("Edit");
            setListFooter(button);
        }

    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.swipeback_second_preference, target);

    }

}
