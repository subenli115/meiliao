package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.envet.SimpleTextWatcher;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CrowdFundingUsedTemplateAdapter extends MultiItemRecycleViewAdapter<CrowdFundingPreviewBean> {

    public static final int TYPE_FIRST = 1;
    public static final int TYPE_SECOND = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final int TYPE_FIVE = 5;
    public static final int TYPE_IMAGE = 6;
    private boolean hasFocus;

    public CrowdFundingUsedTemplateAdapter(Context context, boolean isPreview) {
        super(context, new CrowdFundingUsedTemplateMultiItemType());
        this.hasFocus = !isPreview;

    }

    @Override
    public void convert(ViewHolderHelper helper, final CrowdFundingPreviewBean bean, int position) {
        int viewType = getItemViewType(position);
        helper.setVisible(R.id.iv_right_delete, hasFocus);
        if (hasFocus) {
            helper.setOnClickListener(R.id.iv_right_delete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(bean);
                }
            });
        }

        switch (viewType) {
            case TYPE_IMAGE:
                RoundImageView roundImageView = helper.getView(R.id.iv_item_crowd_funding_used_template_image);
                ImageLoaderUtils.displayRatioTarger(mContext,roundImageView, bean.getPicture(), R.mipmap.ic_loading_square);
//                helper.setImageUrlTarget(R.id.iv_item_crowd_funding_used_template_image, bean.getPicture(), R.mipmap.ic_loading_square);
                break;
            case TYPE_FIRST:
            case TYPE_SECOND:
            case TYPE_FOUR:
            case TYPE_THREE:
                setItemData(helper, bean, hasFocus);
                break;
            case TYPE_FIVE:
                setItemData(helper, bean);
                if (bean.getName() != null) {
                    helper.setFocusable(R.id.tv_item_crowd_funding_used_template_name, false);
                    helper.setText(R.id.tv_item_crowd_funding_used_template_name, bean.getName());
                }
                helper.setImageCircle(R.id.iv_item_crowd_funding_used_template_avatar, bean.getPicture());
                break;
        }
    }

    private class MyTextWatcher extends SimpleTextWatcher {
        private CrowdFundingPreviewBean mBean;
        private int type;
        public static final int TYPE_TITLE = 0;
        public static final int TYPE_INTRO = 1;
        public static final int TYPE_NAME = 2;

        public MyTextWatcher(CrowdFundingPreviewBean bean, int type) {
            mBean = bean;
            this.type = type;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            onChange(s.toString());
            if (mSimpleTextWatcher != null) {
                mSimpleTextWatcher.onTextChanged(s, start, before, count);
            }
        }

        public void onChange(String text) {
            if (type == TYPE_TITLE) {
                mBean.setTitle(text);
            } else if (type == TYPE_INTRO) {
                mBean.setIntro(text);
            } else if (type == TYPE_NAME) {
                mBean.setName(text);
            }
        }
    }

    private void setItemData(ViewHolderHelper helper, CrowdFundingPreviewBean bean, boolean hasFocus) {
        if (EmptyUtils.isNotEmpty(bean.getTitle())) {
            helper.setFocusable(R.id.et_item_crowd_funding_used_template_title, hasFocus);
            TextView tvTitle = helper.getView(R.id.et_item_crowd_funding_used_template_title);
            tvTitle.setText(bean.getTitle());
        }
        helper.setOnTextChangeListener(R.id.et_item_crowd_funding_used_template_title, new MyTextWatcher(bean, MyTextWatcher
                .TYPE_TITLE));
        if (EmptyUtils.isNotEmpty(bean.getIntro())) {
            helper.setFocusable(R.id.et_item_crowd_funding_used_template_content, hasFocus);
            TextView tv = helper.getView(R.id.et_item_crowd_funding_used_template_content);
            tv.setText("默认内容".equals(bean.getIntro()) ? "" : bean.getIntro());

        }
        helper.setOnTextChangeListener(R.id.et_item_crowd_funding_used_template_content, new MyTextWatcher(bean, MyTextWatcher
                .TYPE_INTRO));
    }

    private void setItemData(ViewHolderHelper helper, CrowdFundingPreviewBean bean) {
        if (EmptyUtils.isNotEmpty(bean.getTitle())) {
            helper.setFocusable(R.id.et_item_crowd_funding_used_template_title, hasFocus);
            helper.setText(R.id.et_item_crowd_funding_used_template_title, bean.getTitle());
            helper.setOnTextChangeListener(R.id.et_item_crowd_funding_used_template_title, new MyTextWatcher(bean, MyTextWatcher
                    .TYPE_TITLE));
        }
        if (EmptyUtils.isNotEmpty(bean.getIntro())) {
            helper.setFocusable(R.id.et_item_crowd_funding_used_template_content, hasFocus);
            if ("默认内容".equals(bean.getIntro())) {
                TextView tv = helper.getView(R.id.et_item_crowd_funding_used_template_content);
                tv.setText("");
            } else {
                helper.setText(R.id.et_item_crowd_funding_used_template_content, bean.getIntro());
            }
            helper.setOnTextChangeListener(R.id.et_item_crowd_funding_used_template_content, new MyTextWatcher(bean, MyTextWatcher
                    .TYPE_INTRO));
        }
    }

    private SimpleTextWatcher mSimpleTextWatcher;

    public void setSimpleTextWatcher(SimpleTextWatcher simpleTextWatcher) {
        mSimpleTextWatcher = simpleTextWatcher;
    }

    private static class CrowdFundingUsedTemplateMultiItemType implements MultiItemTypeSupport<CrowdFundingPreviewBean> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_FIRST:
                    return R.layout.item_crowd_funding_used_template_frist;
                case TYPE_SECOND:
                    return R.layout.item_crowd_funding_used_template_two;
                case TYPE_THREE:
                    return R.layout.item_crowd_funding_used_template_five;

                case TYPE_FOUR:
                    return R.layout.item_crowd_funding_used_template_three;

                case TYPE_FIVE:
                    return R.layout.item_crowd_funding_used_template_four;
                case TYPE_IMAGE:
                    return R.layout.item_crowd_funding_used_template_six;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, CrowdFundingPreviewBean bean) {
            return bean.getType();
        }
    }

}
