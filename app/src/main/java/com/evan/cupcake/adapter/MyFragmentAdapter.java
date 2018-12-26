package com.evan.cupcake.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evan.cupcake.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Evan on 2018/12/21.
 * des
 */

public class MyFragmentAdapter extends BaseAdapter {
    private Context context;
    private JSONArray array = new JSONArray();
    private int[] imgurl = {R.mipmap.my01, R.mipmap.my02, R.mipmap.my03, R.mipmap.my04, R.mipmap.my05, R.mipmap.my06, R.mipmap.my07, R.mipmap.my08, R.mipmap.my09};
    private String[] name = {"账单查询", "密码管理", "实名认证", "银行卡", "我的费率", "公众号", "客服电话", "操作教程", "设置"};

    public MyFragmentAdapter(Context context, JSONArray array) {
        this.context = context;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.length();
    }

    @Override
    public JSONObject getItem(int position) {
        if (array.length() == 0) {
            return null;
        }
        return array.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_my_fragment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgHeader.setImageResource(imgurl[position]);
        holder.tvName.setText(name[position]);

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView imgHeader;
        public TextView tvName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.imgHeader = (ImageView) rootView.findViewById(R.id.img_header);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }
}
