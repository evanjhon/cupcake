package com.evan.cupcake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evan.cupcake.App.MyApplication;
import com.evan.cupcake.R;
import com.evan.cupcake.activity.ManageActivity;
import com.evan.cupcake.base.BaseFragment;
import com.evan.cupcake.util.DpUtil;
import com.evan.cupcake.util.ImageLoaderUtils;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 2018/12/19.
 * des
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, XBanner.XBannerAdapter {
    private PullToRefreshLayout pullToRefresh;
    private LinearLayout llManageCard;
    private LinearLayout llLoansApply;
    private LinearLayout llHeartenMoney;
    private LinearLayout llPromoteLimit;
    private LinearLayout llMarket;
    private LinearLayout llShare;
    private LinearLayout llVip;
    private LinearLayout llCardAppraisal;
    private LinearLayout llItemAll;
    private LinearLayout llTop;
    private LinearLayout llCenter;
    private LinearLayout llBottom;
    private TextView tvTips;
    private XBanner xbanner;
    private List<String> bannerDate = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bannerDate.add("http://i1.umei.cc/uploads/tu/201701/798/s11u0nvggfr.jpg");
        bannerDate.add("http://i1.umei.cc/uploads/tu/201701/798/fhqvsqjmjj2.jpg");
        bannerDate.add("http://i1.umei.cc/uploads/tu/201701/798/crxieha1urr.jpg");
        initView(view);
        initEvent();
        return view;
    }

    /**
     * 初始化点击事件
     */
    private void initEvent() {

        llManageCard.setOnClickListener(this);
        llLoansApply.setOnClickListener(this);
        llHeartenMoney.setOnClickListener(this);
        llPromoteLimit.setOnClickListener(this);
        llMarket.setOnClickListener(this);
        llShare.setOnClickListener(this);
        llVip.setOnClickListener(this);
        llCardAppraisal.setOnClickListener(this);
        llItemAll.setOnClickListener(this);
        tvTips.setOnClickListener(this);
        xbanner.setOnClickListener(this);
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {

            }
        });


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
     * 初始化布局
     *
     * @param view
     */
    private void initView(View view) {
        pullToRefresh = view.findViewById(R.id.pull_to_refresh);
        llManageCard = view.findViewById(R.id.ll_manage_card);
        llLoansApply = view.findViewById(R.id.ll_loans_apply);
        llHeartenMoney = view.findViewById(R.id.ll_hearten_money);
        llPromoteLimit = view.findViewById(R.id.ll_promote_limit);
        llMarket = view.findViewById(R.id.ll_market);
        llShare = view.findViewById(R.id.ll_share);
        llVip = view.findViewById(R.id.ll_vip);
        llCardAppraisal = view.findViewById(R.id.ll_card_appraisal);
        llItemAll = view.findViewById(R.id.ll_item_all);
        tvTips = view.findViewById(R.id.tv_tips);
        llTop = view.findViewById(R.id.ll_top);
        llCenter = view.findViewById(R.id.ll_center);
        llBottom = view.findViewById(R.id.ll_bottom);
        xbanner = view.findViewById(R.id.xbanner);

        int height = (MyApplication.Height - DpUtil.dip2px(getActivity(), 46)
                - DpUtil.dip2px(getActivity(), 60)
                - MyApplication.titleHeight) / 3;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = height;
        llTop.setLayoutParams(lp);
        llBottom.setLayoutParams(lp);
        llCenter.setLayoutParams(lp);
        FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fp.height = height + DpUtil.dip2px(getActivity(), 30);
        fp.gravity = Gravity.BOTTOM;
        fp.setMargins(DpUtil.dip2px(getActivity(), 10), 0, DpUtil.dip2px(getActivity(), 10), DpUtil.dip2px(getActivity(), 10));
        llItemAll.setLayoutParams(fp);

        if (bannerDate.size() > 0) {
            xbanner.setVisibility(View.VISIBLE);
            xbanner.setmAdapter(HomeFragment.this);
            xbanner.setData(bannerDate, null);
        } else {
            xbanner.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_manage_card:
                intent.setClass(getActivity(), ManageActivity.class);
                break;
            case R.id.ll_loans_apply:
                break;
            case R.id.ll_hearten_money:
                break;
            case R.id.ll_promote_limit:
                break;
            case R.id.ll_market:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_vip:
                break;
            case R.id.ll_card_appraisal:
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    private void jumpIntoOther() {

    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        ImageLoaderUtils.setTransparentBgImage(bannerDate.get(position), (ImageView) view);
    }
}
