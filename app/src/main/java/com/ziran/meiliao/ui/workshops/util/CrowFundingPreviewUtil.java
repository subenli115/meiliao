package com.ziran.meiliao.ui.workshops.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;
import com.ziran.meiliao.utils.HtmlUtil;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/10 18:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/10$
 * @updateDes ${TODO}
 */

public class CrowFundingPreviewUtil {

    private Context mContext;

    private View headView;
    private ImageView ivHeadViewBan;
    private TextView tvHeadViewTitle;
    private TextView tvHeadViewTime;
    private TextView tvHeadViewAddress;
    private ProgressTipsView mProgressTipsView;


    public void setHeadData(String pic, String title, long startTime, long endTime, String address, int supportMembers, int leftTime, int
            targetMembers) {
        ImageLoaderUtils.displayTager(mContext, ivHeadViewBan, pic, R.mipmap.ic_loading_square_big);
        ViewUtil.setText(tvHeadViewTitle, title);
        if (startTime==0 && endTime==0){
            ViewUtil.setText(tvHeadViewTime,"开始时间待定" );
        }else{
            ViewUtil.setText(tvHeadViewTime, HtmlUtil.format("%s - %s", TimeUtil.getStringByFormat(startTime,"yyyy／MM／dd"), TimeUtil.getStringByFormat
                    (endTime,"yyyy／MM／dd")));
        }
        ViewUtil.setText(tvHeadViewAddress, address);
        //88 多少人支持  20 剩余天数   99 可以支持总人数
        mProgressTipsView.setParms(supportMembers, leftTime, targetMembers);
    }


    public void setFooterData(String pic, String name, String des) {
        ImageLoaderUtils.display(mContext, ivFooterViewAvatar, pic, R.mipmap.ic_loading_rectangle);
        ViewUtil.setText(tvFooterViewName, name);
        ViewUtil.setText(tvFooterViewDescribe, des);
    }

    private View footerView;
    private ImageView ivFooterViewAvatar;
    private TextView tvFooterViewName;
    private TextView tvFooterViewDescribe;

    public CrowFundingPreviewUtil(Context context) {
        this.mContext = context;
    }

    public void init() {
        if (headView != null) return;
        headView = LayoutInflater.from(mContext).inflate(R.layout.headerview_crowd_funding_preview, null);
        footerView = LayoutInflater.from(mContext).inflate(R.layout.footer_crowd_funding_preview, null);
        ivHeadViewBan = ViewUtil.getView(headView, R.id.iv_headview_crowd_funding_preview_ban);
        tvHeadViewTitle = ViewUtil.getView(headView, R.id.tv_headview_crowd_funding_preview_title);
        tvHeadViewTime = ViewUtil.getView(headView, R.id.tv_headview_crowd_funding_preview_time);
        tvHeadViewAddress = ViewUtil.getView(headView, R.id.tv_headview_crowd_funding_preview_address);
        mProgressTipsView = ViewUtil.getView(headView, R.id.progressTipsView);

        ivFooterViewAvatar = ViewUtil.getView(footerView, R.id.iv_footer_crowd_funding_preview_avatar);
        tvFooterViewName = ViewUtil.getView(footerView, R.id.tv_footer_crowd_funding_preview_name);
        tvFooterViewDescribe = ViewUtil.getView(footerView, R.id.tv_footer_crowd_funding_preview_describe);

    }

    public void bindTarget(IRecyclerView recyclerView) {
        if (recyclerView != null && headView != null && footerView != null) {
            recyclerView.addHeaderView(headView);
            recyclerView.addFooterView(footerView);
        }
    }

    private static Map<String,String> crowdFundgingMap;
    public static void setParams(Map<String, String> stringStringMap) {
        crowdFundgingMap = stringStringMap;
    }

    public static Map<String, String> getCrowdFundgingMap() {
        return crowdFundgingMap;
    }
}
