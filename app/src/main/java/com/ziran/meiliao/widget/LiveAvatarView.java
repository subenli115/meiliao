package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import java.util.Locale;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/28 15:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/28$
 * @updateDes ${TODO}
 */

public class LiveAvatarView extends LinearLayout {
    private TextView mTextView;
    private ImageView ivAvatar;

    public LiveAvatarView(Context context) {
        this(context, null);
    }

    public LiveAvatarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveAvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_live_avatar_view, this, true);
        ivAvatar = ViewUtil.getView(this, R.id.iv_sjk_fulllive_avatar);
        mTextView = ViewUtil.getView(this, R.id.tv_sjk_fulllive_listcount);
    }


    public void setImageUrl(String url) {
        ImageLoaderUtils.display(getContext(), ivAvatar, url,R.mipmap.ic_user_pic);
    }

    public void setContent(String tag, String  count) {
        mTextView.setText(String.format(Locale.CANADA, "%s\n%s", tag, count));
    }
}
