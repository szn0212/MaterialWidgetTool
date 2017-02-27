package tools.study.com.materialwidgettool.swipeback;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.swipeback.library.SwipeBackActivity;

/**
 * @author suzhuning
 * @date 2016/11/17.
 * Description:
 */
public class FirstActivity extends SwipeBackActivity{

    @OnClick(R.id.swipeback_to_setting_btn)
    void toSettingClick(View v){
        startActivity(new Intent(getBaseContext(), SettingActivity.class));
    }

    @OnClick(R.id.swipeback_to_second_btn)
    void toSecondClick(View v){
        startActivity(new Intent(getBaseContext(), SecondActivity.class));
    }

    @OnClick(R.id.swipeback_to_draglayout_btn)
    void toDragLayoutClick(View v){
        startActivity(new Intent(getBaseContext(), DragLayoutActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipeback_first_activity_layout);

        ButterKnife.bind(this);

    }
}
