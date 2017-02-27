package tools.study.com.materialwidgettool.recycleview;

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

/**
 * @author suzhuning
 * @date 2016/11/18.
 * Description:
 */
public class RecyclerActivity extends AppCompatActivity {

    @BindView(R.id.main_recyclerView)
    RecyclerView main_recyclerView;

    private WidgetListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        main_recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        DividerDecoration dividerDecoration = new DividerDecoration.Builder(getBaseContext()).build();
        main_recyclerView.addItemDecoration(dividerDecoration);

        adapter = new WidgetListAdapter(R.layout.widget_list_item_layout);
        main_recyclerView.setAdapter(adapter);
        adapter.setNewData(Arrays.asList(getResources().getStringArray(R.array.recycleview_type)));

        main_recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getBaseContext(), VerticalRecyclerViewActivity.class);
                switch (adapter.getItem(i)){
                    case "Vertical RecyclerView":
                        intent.putExtra("enumType", VerticalRecyclerViewActivity.RecycleType.VERTICAL);
                        break;

                    case "Horizontal RecyclerView":
                        intent.putExtra("enumType", VerticalRecyclerViewActivity.RecycleType.HORIZONTAL);
                        break;

                    case "Grid RecyclerView":
                        intent.putExtra("enumType", VerticalRecyclerViewActivity.RecycleType.GRID);
                        break;

                    case "StaggeredGrid RecyclerView":
                        intent.putExtra("enumType", VerticalRecyclerViewActivity.RecycleType.STAGGEREDGRID);
                        break;

                    case "Expansable RecycleView":
                        break;

                }

                startActivity(intent);
            }
        });

    }
}
