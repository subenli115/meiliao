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

public class PlayPauseView extends AppCompatImageView implements PlayPauseListener {
    private int playRes = R.mipmap.player_play;
    private int pauseRes = R.mipmap.player_stop;
    private int playRes1 = R.mipmap.ic_sjk_live_video_play;
    private int pauseRes1 = R.mipmap.ic_sjk_live_video_stop;

    public PlayPauseView(Context context) {
        this(context, null);
    }

    public PlayPauseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayPauseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        } else {
            setImageResource(playRes);
        }
        this.isPlaying = isPlaying;
    }

    public void play(boolean isPlaying){
        if (isPlaying) {
            setImageResource(pauseRes);
        } else {
            setImageResource(playRes);
        }
        this.isPlaying = isPlaying;
    }
}
