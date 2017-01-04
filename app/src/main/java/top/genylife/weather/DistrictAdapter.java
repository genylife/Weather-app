package top.genylife.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanqi on 2016/12/30.
 *
 * @since 1.0.0
 */

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder> {

    String[] mCitys;
    String mQuery;
    List<String> mShowCitys;
    OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public interface OnItemClick {

        void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder,String text);
    }

    DistrictAdapter(Context context) {
        mCitys = context.getResources().getStringArray(R.array.citys);
        mShowCitys = new ArrayList<>();
        query(null);
    }

    @Override
    public DistrictViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_district, parent, false);
        return new DistrictViewHolder(view);
    }

    public void query(String query) {
        mShowCitys.clear();
        for (String city : mCitys) {
            if(TextUtils.isEmpty(query) || city.contains(query))
                mShowCitys.add(city);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final DistrictViewHolder holder, final int position) {
        holder.mTextView.setText(mShowCitys.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onItemClick(DistrictAdapter.this, holder,mShowCitys.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShowCitys.size();
    }

    static class DistrictViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        DistrictViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
