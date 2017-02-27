package tools.study.com.materialwidgettool.surfaceview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.WidgetListAdapter;
import tools.study.com.materialwidgettool.recycleview.DividerDecoration;

/**
 * @author suzhuning
 * @date 2016/11/24.
 * Description:
 */
public class SurfaceViewActivity extends AppCompatActivity {

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerDecoration.Builder(this).build());

        WidgetListAdapter adapter = new WidgetListAdapter(R.layout.widget_list_item_layout);
        recyclerView.setAdapter(adapter);
        adapter.setNewData(Arrays.asList(getResources().getStringArray(R.array.surfaceview_type)));

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getBaseContext(), AnimBubbleActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(getBaseContext(), CustomCanvasActivity.class));
                        break;
                }
            }
        });
    }
}
