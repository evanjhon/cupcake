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

/**
 * Created by Evan on 2018/12/21.
 * des
 */

public class UpdateAdapter extends BaseAdapter {
    private Context context;
    private JSONArray array = new JSONArray();

    public UpdateAdapter(Context context, JSONArray array) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_update, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(position);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public View rootView;
        private ImageView imgHeader;
        public TextView tvName;
        public TextView tvMoney;
        public TextView tvUpdate;
        public TextView tvTip1;
        public TextView tvTip2;
        public TextView tvTip3;
        public TextView tvTip4;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.imgHeader = rootView.findViewById(R.id.img_header);
            this.tvName = rootView.findViewById(R.id.tv_name);
            this.tvMoney = rootView.findViewById(R.id.tv_money);
            this.tvUpdate = rootView.findViewById(R.id.tv_update);
            this.tvTip1 = rootView.findViewById(R.id.tv_tip1);
            this.tvTip2 = rootView.findViewById(R.id.tv_tip2);
            this.tvTip3 = rootView.findViewById(R.id.tv_tip3);
            this.tvTip4 = rootView.findViewById(R.id.tv_tip4);
        }

    }

    public void SetOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public OnClick onClick;

    /**
     * 点击接口
     */
    public interface OnClick {
        void onClick(int pos);
    }
}
