package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.SimpleTextWatcher;
import com.ziran.meiliao.utils.HandlerUtil;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/29 18:31
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/29$
 * @updateDes ${TODO}
 */

public class EditCommentFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.et_edit_comment)
    EditText mEditText;
//    @Bind(R.id.tv_edit_comment_anonymous)
//    TextView tvAnonymous;


    @Override
    protected int getLayoutResource() {
        return  R.layout.fragment_edit_comment;
    }

    @Override
    public void initPresenter() {

    }
    private boolean showRightText;

    @Override
    public void initView() {
        ntb.setTitleText("写评论");
        ntb.setLeftImagSrc(R.mipmap.livetelecast_close);
        ntb.setRightTitle("完成");
        ntb.setRightTitleVisibility(false);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //关闭fragment
                hide();
            }
        });
        KeyBordUtil.showSoftKeyboard(mEditText);
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG, HandlerUtil.obj(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_COMMENT, mEditText.getText().toString().trim()));

                hide();
            }
        });
        mEditText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.toString().length();
                if (length == 0 && showRightText) {
                    ntb.setRightTitleVisibility(false);
                    showRightText = false;
                } else if (length > 0 && !showRightText) {
                    ntb.setRightTitleVisibility(true);
                    showRightText = true;
                }
            }
        });
    }

    public void hide() {
        mEditText.setText("");
        KeyBordUtil.hideSoftKeyboard(mEditText);
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE,false);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_bottom);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_out_bottom);
        }
    }
}
