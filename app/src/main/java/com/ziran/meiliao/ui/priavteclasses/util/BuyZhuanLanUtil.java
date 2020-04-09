package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;
import com.ziran.meiliao.ui.priavteclasses.presenter.BuyColumnPresenter;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.pupop.BuyAlbumPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/1 10:26
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/1$
 * @updateDes ${TODO}
 */

public class BuyZhuanLanUtil {


    private BuyAlbumPopupWindow buyAlbumPopupwindow;
    public void showPayV13(Context context,BuyAlbumPopupWindow.BuyAlbumClickListener buyAlbumClickListener,String LevelDetail,float MemberPrice) {
        ZhuanLanData mZhuanLanData = WpyxConfig.getZhuanLanData();
        if (buyAlbumPopupwindow == null) {
            buyAlbumPopupwindow = new BuyAlbumPopupWindow(context);
            buyAlbumPopupwindow.setTitle(StringUtils.format(context.getResources().getString(R.string.buy_album), mZhuanLanData.getTitle()));
            buyAlbumPopupwindow.setAmount(mZhuanLanData.getNeedCoin());
            buyAlbumPopupwindow.setHead(mZhuanLanData.getPicture());
            buyAlbumPopupwindow.setTvAmountMember(MemberPrice);
//            buyAlbumPopupwindow.setMemberPrice(LevelDetail);
            buyAlbumPopupwindow.setBuyAlbumClickListener(buyAlbumClickListener);
        }
        buyAlbumPopupwindow.setBalance(mZhuanLanData.getUserCoin());
        buyAlbumPopupwindow.setStyle((mZhuanLanData.getUserCoin() - mZhuanLanData.getNeedCoin()) < 0);
        PopupWindowUtil.show(buyAlbumPopupwindow);
    }

    public void showPayV13(Context context, BuyAlbumPopupWindow.BuyAlbumClickListener buyAlbumClickListener) {
        ZhuanLanData mZhuanLanData = WpyxConfig.getZhuanLanData();
        if (buyAlbumPopupwindow == null) {
            buyAlbumPopupwindow = new BuyAlbumPopupWindow(context);
            buyAlbumPopupwindow.setTitle(StringUtils.format(context.getResources().getString(R.string.buy_album), mZhuanLanData.getTitle()));
            buyAlbumPopupwindow.setAmount(mZhuanLanData.getNeedCoin());

            buyAlbumPopupwindow.setHead(mZhuanLanData.getPicture());
            buyAlbumPopupwindow.setBuyAlbumClickListener(buyAlbumClickListener);
        }
        buyAlbumPopupwindow.setBalance(mZhuanLanData.getUserCoin());
        buyAlbumPopupwindow.setStyle((mZhuanLanData.getUserCoin() - mZhuanLanData.getNeedCoin()) < 0);
        PopupWindowUtil.show(buyAlbumPopupwindow);
    }
    public void setUserCoin(int userCoin){
        if (buyAlbumPopupwindow!=null){
            ZhuanLanData mZhuanLanData = WpyxConfig.getZhuanLanData();
            buyAlbumPopupwindow.setBalance(userCoin);
            buyAlbumPopupwindow.setStyle((mZhuanLanData.getUserCoin() - mZhuanLanData.getNeedCoin()) < 0);
        }
    }


    private BuyAlbumPopupWindow.BuyAlbumClickListener mBuyAlbumClickListener;


    private void init(final Context context, final BuyColumnPresenter presenter, final String specColumnId) {
        if (mBuyAlbumClickListener == null) {

            mBuyAlbumClickListener = new BuyAlbumPopupWindow.BuyAlbumClickListener() {
                @Override
                public void buyAlbum(boolean needRecharge) {
                    if (needRecharge) {
                        RechargeActivity.startAction(context, String.valueOf(WpyxConfig.getZhuanLanData().getUserCoin()));
                    } else {
                        presenter.postBuySpecColumn(ApiKey.SPEC_COLUMN_BUY, MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID,
                                specColumnId));
                    }
                }
            };
        }
    }

    public void buy(Context context, BuyColumnPresenter presenter, NewMediaAndTextBean.DataBean data, String specColumnId) {
        ZhuanLanData zhuanLanData = new ZhuanLanData(1, Integer.parseInt(data.getUserCoin()), data.getNeedCoin(), specColumnId, data.getTitle(),
                data.getPic());
        zhuanLanData.setBuy(data.isIsBuy());

        WpyxConfig.setZhuanLanData(zhuanLanData);
        init(context, presenter, specColumnId);
        showPayV13(context, mBuyAlbumClickListener,data.getLevelDetail(),data.getMemberPrice());
    }

    public void buy(Context context, BuyColumnPresenter presenter, int userCoin, int needCoin, String specColumnId, String title, String picture, boolean isBuy) {
        ZhuanLanData zhuanLanData = new ZhuanLanData(1, userCoin, needCoin, specColumnId, title,
                picture);
        zhuanLanData.setBuy(isBuy);

        WpyxConfig.setZhuanLanData(zhuanLanData);
        init(context, presenter, specColumnId);
        showPayV13(context, mBuyAlbumClickListener);
    }

}
