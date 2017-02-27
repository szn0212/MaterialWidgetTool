package tools.study.com.materialwidgettool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.coordinatorlayout.CoordinatorActivity;
import tools.study.com.materialwidgettool.coordinatorlayout.CoordinatorListActivity;
import tools.study.com.materialwidgettool.coordinatorlayout.CoordinatorListFragment;
import tools.study.com.materialwidgettool.drawerlayout.HomeActivity;
import tools.study.com.materialwidgettool.materialanimation.MaterialAnimationsActivity;
import tools.study.com.materialwidgettool.recycleview.DividerDecoration;
import tools.study.com.materialwidgettool.recycleview.RecyclerActivity;
import tools.study.com.materialwidgettool.surfaceview.SurfaceViewActivity;
import tools.study.com.materialwidgettool.swipeback.FirstActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    private WidgetListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        DividerDecoration dividerDecoration = new DividerDecoration.Builder(getBaseContext()).build();
        recyclerView.addItemDecoration(dividerDecoration);

        adapter = new WidgetListAdapter(R.layout.widget_list_item_layout);
        recyclerView.setAdapter(adapter);
        adapter.setNewData(Arrays.asList(getResources().getStringArray(R.array.widget_list)));

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getBaseContext(), HomeActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(getBaseContext(), RecyclerActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(getBaseContext(), FirstActivity.class));
                        break;

                    case 3:
                        startActivity(new Intent(getBaseContext(), MaterialAnimationsActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(getBaseContext(), SurfaceViewActivity.class));
                        break;

                    case 5:
                        startActivity(new Intent(getBaseContext(), CoordinatorListActivity.class));
                        startActivity(new Intent(getBaseContext(), CoordinatorActivity.class));
                        break;
                }
            }
        });
    }


    private long DOUBLE_CLICK_TIME = 0L;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000){
                Snackbar.make(recyclerView,"再按一次退出", Snackbar.LENGTH_SHORT).show();
                DOUBLE_CLICK_TIME = System.currentTimeMillis();
            }else {
                ((WidgetToolApplication)getApplication()).exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
