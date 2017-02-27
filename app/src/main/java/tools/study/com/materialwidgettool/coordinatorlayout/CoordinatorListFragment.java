package tools.study.com.materialwidgettool.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.WidgetListAdapter;
import tools.study.com.materialwidgettool.recycleview.DividerDecoration;

/**
 * @author suzhuning
 * @date 2016/12/5.
 * Description:
 */
public class CoordinatorListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static CoordinatorListFragment newInstance(String title){
        CoordinatorListFragment fragment = new CoordinatorListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coordinator_list_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String title = getArguments().getString("title");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerDecoration.Builder(getContext()).build());
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lists.add(title + i);
        }
        recyclerView.setAdapter(new WidgetListAdapter(R.layout.widget_list_item_layout, lists));

    }
}
