package top.genylife.weather;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

/**
 * Created by wanqi on 2016/12/30.
 *
 * @since 1.0.0
 */

public class AddLocationDialog extends BlurDialogFragment {

    SearchView mSearchView;
    RecyclerView mDatasRecyclerView;
    DistrictAdapter mAdapter;

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    CallBack mCallBack;

    interface CallBack {

        void call(String text);
    }

    public static AddLocationDialog create(CallBack callBack) {
        AddLocationDialog addLocationDialog = new AddLocationDialog();
        addLocationDialog.setCallBack(callBack);
        return addLocationDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_location, container, false);
        Window window = getDialog().getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
        window.setStatusBarColor(Color.TRANSPARENT);
        mSearchView = (SearchView) view.findViewById(R.id.search_view);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconifiedByDefault(true);
        mDatasRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new DistrictAdapter(getActivity());
        mDatasRecyclerView.setAdapter(mAdapter);
        mDatasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.query(newText.trim());
                return false;
            }
        });
        mAdapter.setOnItemClick(new DistrictAdapter.OnItemClick() {
            @Override
            public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder holder, String text) {
                mCallBack.call(text);
                AddLocationDialog.this.dismiss();
            }
        });
        return view;
    }
}
