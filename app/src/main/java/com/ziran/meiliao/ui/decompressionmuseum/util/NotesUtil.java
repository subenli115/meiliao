package com.ziran.meiliao.ui.decompressionmuseum.util;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import com.ziran.meiliao.ui.decompressionmuseum.activity.DragPhotoActivity;
import com.ziran.meiliao.widget.ninegridimageview.NineGridImageView;
import com.yuyh.library.imgsel.ImgSelActivity;

import java.util.ArrayList;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/25 13:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/25$
 * @updateDes ${TODO}
 */

public class NotesUtil {


    public static void startPhotoActivity(Activity context, ImageView imageView, int index, ArrayList<NineGridImageView.Locations> list,
                                   ArrayList<String> imgPaths) {
        Intent intent = new Intent(context, DragPhotoActivity.class);
        int location[] = new int[2];
        intent.putStringArrayListExtra(ImgSelActivity.INTENT_RESULT, imgPaths);
        intent.putParcelableArrayListExtra("location", list);
        intent.putExtra("index", index);
        imageView.getLocationOnScreen(location);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());
        context.startActivity(intent);
        context.overridePendingTransition(0, 0);
    }

}
