package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.TrailerDetailBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.TrailerAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.TrailerContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.TrailerPresenter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class TrailerFragment extends CommonRefreshFragment<TrailerPresenter, CommonModel> implements TrailerContract.View {

    TextView tvTrailerIntro;
    TextView tvTrailerTitle;
    TextView tvTrailerTeaIntro;
    TextView tvTrailerCourseDetail;
    ImageView ivHeadPic;
    private View headView;


    private void initHeadView() {

        headView = LayoutInflater.from(getContext()).inflate(R.layout.headerview_trailer, null);
        tvTrailerIntro = ViewUtil.getView(headView, R.id.tv_trailer_intro);
        tvTrailerTitle = ViewUtil.getView(headView, R.id.tv_trailer_title);
        ivHeadPic = ViewUtil.getView(headView, R.id.iv_tralier_headPic);
        tvTrailerTeaIntro = ViewUtil.getView(headView, R.id.tv_trailer_teaIntro);
        tvTrailerCourseDetail = ViewUtil.getView(headView, R.id.tv_trailer_course_detail);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
//        setRecyclerEnabled(false,true);
        return new TrailerAdapter(getContext(),R.layout.item_trailer_detail);
    }

    @Override
    protected void loadData() {
        mPresenter.getTrailerNative(ApiKey.TRAILER_NATIVE, MapUtils.getCourseMap("34"));
    }

    @Override
    public void updateHead(TrailerDetailBean.DataBean data) {
        if (headView == null) {
            initHeadView();
            iRecyclerView.setHeadView(headView);
        }
        ImageLoaderUtils.displayTager(getContext(), ivHeadPic, data.getPic());
        tvTrailerIntro.setText(data.getIntro());
        tvTrailerTitle.setText(data.getTitle());
        if (EmptyUtils.isNotEmpty(data.getAuthor()) && EmptyUtils.isNotEmpty(data.getAuthor().getTeaIntro())){
            tvTrailerTeaIntro.setText(Html.fromHtml(data.getAuthor().getTeaIntro()));
        }
        ViewUtil.setHtmlText(tvTrailerCourseDetail,data.getDetail());

    }

    @Override
    public void showList(TrailerDetailBean.DataBean data) {
        updateData( data.getDir());
    }
}
