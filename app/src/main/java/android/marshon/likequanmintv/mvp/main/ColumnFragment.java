package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.ColumnFragmentListAdapter;
import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.listener.UpDownRvScrollListener;
import android.marshon.likequanmintv.mvp.column.interactor.ColumnFragmentInteractorImpl;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ColumnFragment extends BaseFragment implements LoadMoreCommonAdapter.OnLoadMoreListener, IGetDataDelegate<List<ItemColumn>>{


    private RecyclerView mRv;
    private List<ItemColumn> mColumnList=new ArrayList<>();
    private ColumnFragmentListAdapter mAdapter;
    private ColumnFragmentInteractorImpl mColumnFragmentInteractor;
    private UpDownRvScrollListener.UpdownScroll mUpdownScroll;


    public static ColumnFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ColumnFragment fragment = new ColumnFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frg_column;
    }

    @Override
    protected void initView(View rootView) {
        mRv=(RecyclerView)rootView.findViewById(R.id.mRv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        mRv.setLayoutManager(gridLayoutManager);
        mAdapter=new ColumnFragmentListAdapter(mActivity,R.layout.listitem_column, mColumnList);
        mAdapter.setOnLoadMoreListener(this);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mColumnFragmentInteractor = new ColumnFragmentInteractorImpl();
        mColumnFragmentInteractor.loadColumnList(this);

    }


    @Override
    public void onLoadMoreRequested() {


    }

    @Override
    public void getDataSuccess(List<ItemColumn> itemColumns) {
        mAdapter.refreshDatas(itemColumns);


    }

    @Override
    public void getDataError(String errmsg) {

    }



}
