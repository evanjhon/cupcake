package com.evan.cupcake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evan.cupcake.R;
import com.evan.cupcake.StartActivity;
import com.evan.cupcake.adapter.UpdateAdapter;
import com.evan.cupcake.base.BaseFragment;
import com.evan.cupcake.view.MyListView;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Evan on 2018/12/19.
 * des
 */

public class UpdateFragment extends BaseFragment {
    private TextView tvTips;
    private LinearLayout llHeader;
    private MyListView lvData;
    private PullToRefreshLayout pullToRefresh;

    private UpdateAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
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

    /**
     * 初始化布局问
     *
     * @param view
     */
    private void initView(View view) {
        tvTips = (TextView) view.findViewById(R.id.tv_tips);
        llHeader = (LinearLayout) view.findViewById(R.id.ll_header);
        lvData = (MyListView) view.findViewById(R.id.lv_data);
        pullToRefresh = (PullToRefreshLayout) view.findViewById(R.id.pull_to_refresh);
        JSONArray array = new JSONArray();
        for (int i = 0; i < 10; i++) {
            try {
                JSONObject object = new JSONObject();
                object.put("q", "qqq");
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter = new UpdateAdapter(getActivity(), array);
        lvData.setAdapter(adapter);
        lvData.invalidate();
        adapter.notifyDataSetInvalidated();
        adapter.SetOnClick(new UpdateAdapter.OnClick() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), StartActivity.class);
                startActivity(intent);
            }
        });
    }
}
