package com.evan.cupcake.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evan.cupcake.R;
import com.evan.cupcake.adapter.MyFragmentAdapter;
import com.evan.cupcake.base.BaseFragment;
import com.evan.cupcake.view.MyGridView;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Evan on 2018/12/19.
 * des
 */

public class MyFragment extends BaseFragment {
    private MyGridView gvItem;
    private PullToRefreshLayout pullToRefresh;
    private MyFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        initData();
        return view;
    }
    /**
     * 初始化事件
     */
    private void initData() {
        pullToRefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
                        pullToRefresh.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束加载更多
                        pullToRefresh.finishLoadMore();
                    }
                }, 2000);
            }
        });

    }

    private void initView(View view) {
        gvItem = (MyGridView) view.findViewById(R.id.gv_item);
        pullToRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to_refresh);
        JSONArray array = new JSONArray();
        for (int i = 0; i < 9; i++) {
            try {
                JSONObject object = new JSONObject();
                object.put("q", "qqq");
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter=new MyFragmentAdapter(getActivity(),array);
        gvItem.setAdapter(adapter);
        gvItem.invalidate();
        adapter.notifyDataSetChanged();
    }
}
