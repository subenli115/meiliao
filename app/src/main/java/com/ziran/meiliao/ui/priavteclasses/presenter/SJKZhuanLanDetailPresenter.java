package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.SpceColumnBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKZhuanLanDetailContract;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 10:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class SJKZhuanLanDetailPresenter extends SJKZhuanLanDetailContract.Presenter {

    @Override
    public void getData(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SpceColumnBean>(SpceColumnBean.class, mView) {
            @Override
            public void onSuccess(SpceColumnBean result) {
                SpceColumnBean.DataBean data = result.getData();
                changeData(data.getDir());
                mView.showData(data);
            }
        });
    }

    private List changeData(List<ZhuanLanBigInBean.DataBean.DirBean> dir) {
        if (EmptyUtils.isEmpty(dir)) return null;
        Set<String> mouths = new HashSet<>();
        String month = "", date = "";

        for (int i = 0; i < dir.size(); i++) {
            ZhuanLanBigInBean.DataBean.DirBean dirBean = dir.get(i);
            if (i == 0) {
                dirBean.setCheck(true);
            }
            String startTime = dirBean.getStartTime();
            if (EmptyUtils.isNotEmpty(startTime)) {
                month = startTime.substring(5, 7);
                date = startTime.substring(8, 10);
            }
            if (!mouths.contains(month)) {
                mouths.add(month);
                dirBean.setMouth(month);
            }
            if (i%8==0){
                dirBean.setMouth(month);
            }
            dirBean.setDay(date);
        }
        mouths.clear();
        return dir;
    }

    @Override
    public void postGz(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showGzResult(result);
            }
        });
    }

    @Override
    public void postListenUp(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }

    @Override
    public void postBuySpecColumn(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showBuySpecColumnResult(result);
            }
        });
    }

    @Override
    public void getCertificate(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<StudyFinishBean>(StudyFinishBean.class) {
            @Override
            public void onSuccess(StudyFinishBean result) {
                mView.showCertificate(result.getData());
            }
        });
    }

    @Override
    public void subscriptionShareSave(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
//                mView.showCertificate(result.getData());
            }
        });
    }
}
