package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.listener.PlayPauseListener;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 10:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class PlayPauseViewOne extends AppCompatImageView implements PlayPauseListener {
    private int playRes = R.mipmap.ic_sjk_live_video_play;
    private int pauseRes = R.mipmap.ic_sjk_live_video_stop;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int status;

    public PlayPauseViewOne(Context context) {
        this(context, null);
    }

    public PlayPauseViewOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayPauseViewOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResource(playRes);
        isPlaying = false;
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggle(!isPlaying);
//            }
//        });
    }
    private boolean isPlaying ;

    public void toggle(boolean isPlaying) {
//        if (this.isPlaying == isPlaying) return;

        if (isPlaying) {
            setImageResource(pauseRes);
            status=pauseRes;
        } else {
            setImageResource(playRes);
            status=playRes;
        }
        this.isPlaying = isPlaying;
    }

}
