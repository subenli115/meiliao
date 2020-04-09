package com.ziran.meiliao.ui.NewDecompressionmuseum.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.WeekDietHomeActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.WeekLiveHomeActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.WeekNineDotActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.WeekPracticeWithPlayerActicity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.WeekQuestionActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.RecordParentInfoAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeHomeContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeHomePresenter;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeHomeBean;
import com.ziran.meiliao.ui.bean.PracticeRecordBean;
import com.ziran.meiliao.ui.bean.RecordChildInfoBean;
import com.ziran.meiliao.ui.bean.RecordParentInfoBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_PRACTICEHIS;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSUPDATE;

public class BaseWeekFragment extends CommonHttpFragment<PracticeHomePresenter, CommonModel>  implements PracticeHomeContract.View{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private String bookId;
    private RecordChildInfoBean recordChildInfoBean;
    private String mTypeId;
    private String mId;
    List<RecordParentInfoBean> list;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_one_week;
    }

    @Override
    protected void initBundle(Bundle extras) {
        bookId = extras.getString(AppConstant.ExtraKey.FROM_ID);
    }
    @Override
    protected void initView() {
    super.initView();

        String str = (String)getArguments().get("num");

        list = new ArrayList<>();
        Map<String, String> weekMap = MapUtils.getDefMap(true);
        if(bookId==null){
            weekMap.put("id", "1");
        }else{
            weekMap.put("id", bookId);
        }
        weekMap.put("weeks", str);
        if(mPresenter!=null){
            mPresenter.getPracticeRecordData(PRACTIEACTIVITY_PRACTICEHIS,weekMap);
        }
}
    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    @Override
    public void showHomeData(PracticeHomeBean.DataBean result) {

    }
    private String toChinese(String string) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

        String result = "";

        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;

    }

    @Override
    public void showRecordData(PracticeRecordBean.DataBean result) {
        List<PracticeRecordBean.DataBean.ExerciseListBean> exerciseList = result.getExerciseList();
        for (int i = 0; i <exerciseList.size(); i++) {
            RecordParentInfoBean parentInfo = new RecordParentInfoBean();
            List<RecordChildInfoBean> childInfoList = new ArrayList<>();
            parentInfo.setTitle("第"+toChinese(i+1+"")+"天");
            for (int j = 0; j < exerciseList.get(i).getDaysExerciseList().size(); j++) {
                RecordChildInfoBean childInfo = new RecordChildInfoBean();
                PracticeRecordBean.DataBean.ExerciseListBean.DaysExerciseListBean daysExerciseListBean = exerciseList.get(i).getDaysExerciseList().get(j);
                childInfo.setName(daysExerciseListBean.getName());
                childInfo.setStatus(daysExerciseListBean.getStatus());
                childInfo.setType_id(daysExerciseListBean.getType_id());
                childInfo.setId(daysExerciseListBean.getId());
                childInfo.setBooks_id(daysExerciseListBean.getBooks_id());
                childInfoList.add(childInfo);
            }
            parentInfo.setMenuList(childInfoList);
            list.add(parentInfo);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecordParentInfoAdapter recordParentInfoAdapter = new RecordParentInfoAdapter(getActivity(), list);
        recordParentInfoAdapter.setItemClickListener(new RecordParentInfoAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, String id,String typeId) {
                //调用
                Map<String, String> statusMap = MapUtils.getDefMap(true);
                mTypeId=typeId;
                mId=id;
                statusMap.put("id",bookId);
                statusMap.put("itemId",id);
                statusMap.put("status","1");
                mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE,statusMap);
            }

        });
        recyclerView.setAdapter(recordParentInfoAdapter);
    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {
        switch ( Integer.parseInt(mTypeId)){
            case 1:
                WeekPracticeWithPlayerActicity.startAction(getContext(),mId+"",bookId,result,true);
                break;
            case 2:
                WeekNineDotActivity.startAction(getContext(),bookId,mId);
                break;
            case 3:
                WeekDietHomeActivity.startAction(getContext(),bookId,mId+"",result,true, 1);
                break;
            case 4:
                WeekQuestionActivity.startAction(getContext(),bookId,mId+"",result,true, 1);
                break;
            case 5:
                WeekLiveHomeActivity.startAction(getContext(),bookId,mId+"",result,true, 1);
                break;
        }
    }
}