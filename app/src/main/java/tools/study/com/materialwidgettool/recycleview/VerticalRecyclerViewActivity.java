package tools.study.com.materialwidgettool.recycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.study.library.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.WidgetListAdapter;

/**
 * @author suzhuning
 * @date 2016/11/21.
 * Description:
 */
public class VerticalRecyclerViewActivity extends AppCompatActivity{

    @BindView(R.id.swiperefresh_layout)
    SwipeRefreshLayout swiperefresh_layout;
    @BindView(R.id.recyclerview_layout)
    RecyclerView recyclerView;

    private WidgetListAdapter adapter;
    private int page = 1;
    private int TOTAL_COUNT = 200;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);
        ButterKnife.bind(this);

        RecycleType type = (RecycleType) getIntent().getSerializableExtra("enumType");

        switch (type){
            case HORIZONTAL:
                recyclerView.addItemDecoration(new DividerDecoration.Builder(getBaseContext())
                        .setWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f,
                                getBaseContext().getResources().getDisplayMetrics())).build());
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
                adapter = new WidgetListAdapter(R.layout.widget_list_item_horizontal_layout);
                break;
            case VERTICAL:
                recyclerView.addItemDecoration(new DividerDecoration.Builder(getBaseContext()).build());
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                adapter = new WidgetListAdapter(R.layout.widget_list_item_layout);
                break;
            case GRID:
                recyclerView.addItemDecoration(new DividerDecoration.Builder(getBaseContext())
                        .setWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f,
                                getBaseContext().getResources().getDisplayMetrics()))
                        .setHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f,
                                getBaseContext().getResources().getDisplayMetrics())).build());
                recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
                adapter = new WidgetListAdapter(R.layout.widget_list_item_grid_layout);
                break;
            case STAGGEREDGRID:
                recyclerView.addItemDecoration(new DividerDecoration.Builder(getBaseContext())
                        .setWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f,
                                getBaseContext().getResources().getDisplayMetrics()))
                        .setHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f,
                                getBaseContext().getResources().getDisplayMetrics())).build());
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                adapter = new WidgetListAdapter(true, R.layout.widget_list_item_staggeredgrid_layout);
                break;
            case EXPANSABLE:
                break;
        }


        recyclerView.setAdapter(adapter);

        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        adapter.isFirstOnly(false);//true表示每个item只执行一次， false表示可以重复执行动画
        adapter.setEnableLoadMore(true);
//        adapter.setAutoLoadMoreSize(1);//默认是1

        swiperefresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(true);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        });

        adapter.setLoadMoreView(new CustomLoadMoreView());

//        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.recycleview_header_view, null);
//
//        adapter.addHeaderView(view);
//        TextView textview = (TextView) view.findViewById(R.id.textview);
//        if(textview != null) {
//            textview.setText("FooterView");
//        }
//        adapter.addFooterView(view);

        getData(true);
    }

    private void getData(final boolean isRefresh) {
        if(isRefresh){
            page = 1;
        }else {
            page++;
        }

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(adapter.getItemCount() >= TOTAL_COUNT){
                    adapter.loadMoreEnd();
                }else {
                    datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++){
                        datas.add("Item " + ((page - 1) * 20 + i));
                    }

//              if(isRefresh && datas.size() == 0){
//                   adapter.setEmptyView(view);
//                   return;
//              }

                    if (datas != null && datas.size() > 0) {
                        //获取数据成功
                        if (isRefresh) {
                            adapter.setNewData(datas);
                            swiperefresh_layout.setRefreshing(false);
                        } else {
                            adapter.addData(datas);
                        }
                        adapter.loadMoreComplete();
                    } else {
                        //获取数据失败
                        adapter.loadMoreFail();
                    }
                }

            }
        });

    }


    public enum RecycleType{
        HORIZONTAL,
        VERTICAL,
        GRID,
        STAGGEREDGRID,
        EXPANSABLE;
    }
}
