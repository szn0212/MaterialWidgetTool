package tools.study.com.materialwidgettool.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/12/5.
 * Description:
 */
public class CoordinatorListActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_list_layout);
        ButterKnife.bind(this);

        CoordinatorViewPagerAdapter adapter = new CoordinatorViewPagerAdapter(getSupportFragmentManager());
        for (int i = 1; i < 6; i++){
            adapter.addFragment(CoordinatorListFragment.newInstance("Tab" + i), "Tab" + i);
            tabLayout.addTab(tabLayout.newTab().setText("Tab" + i));
        }
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
