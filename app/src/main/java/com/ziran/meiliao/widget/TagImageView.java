package com.ziran.meiliao.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonwidget.RoundImageView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/10 10:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/10$
 * @updateDes ${TODO}
 */

public class TagImageView extends RoundImageView {
    private int tagResId;

    public TagImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        margin = (int) DisplayUtil.dp2px(getResources(), 4);
    }

    public TagImageView(Context context) {
        super(context);
        margin = (int) DisplayUtil.dp2px(getResources(), 4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tagBitmap != null) {
            switch (mPosition) {
                case LEFT_TOP:
                    canvas.drawBitmap(tagBitmap, margin, margin, null);
                    break;
                case RIGHT_TOP:
                    canvas.drawBitmap(tagBitmap, getWidth() - margin - tagBitmapWidth, margin, null);
                    break;
                case LEFT_BOTTOM:
                    canvas.drawBitmap(tagBitmap, margin, getHeight() - margin - tagBitmapHeight, null);
                    break;
                case RIGHT_BOTTOM:
                    canvas.drawBitmap(tagBitmap, getWidth() - margin - tagBitmapWidth, getHeight() - margin - tagBitmapHeight, null);
                    break;
            }
        }
    }

    public enum Position {
        LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM
    }

    private Position mPosition = Position.RIGHT_BOTTOM;
    private int margin;
    private Bitmap tagBitmap;
    private int tagBitmapWidth, tagBitmapHeight;


    public void setTagByType(int type) {
        //type 1 : 视频  2: 音频
        setTagRes(type == 1 ? R.mipmap.course_ic_video_45 : R.mipmap.course_ic_audio_45);
    }

    public void setTagRes(int tagRes) {
        if (tagResId != tagRes) {
            tagResId = tagRes;
            tagBitmap = BitmapFactory.decodeResource(getResources(), tagRes);
            tagBitmapWidth = tagBitmap.getWidth();
            tagBitmapHeight = tagBitmap.getHeight();
            invalidate();
        }
    }
}
