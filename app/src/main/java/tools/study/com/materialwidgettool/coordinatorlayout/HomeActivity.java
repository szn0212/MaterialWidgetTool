package tools.study.com.materialwidgettool.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/12/7.
 * Description:
 *      https://github.com/rogerou/Baby
 */
public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_home_layout);
        ButterKnife.bind(this);
    }
}
