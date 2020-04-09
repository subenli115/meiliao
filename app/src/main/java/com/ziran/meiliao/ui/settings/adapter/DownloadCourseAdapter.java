package com.ziran.meiliao.ui.settings.adapter;

        import android.content.Context;

        import com.ziran.meiliao.common.compressorutils.EmptyUtils;
        import com.ziran.meiliao.R;
        import com.ziran.meiliao.db.DbCore;
        import com.ziran.meiliao.entry.CourseEntry;
        import com.ziran.meiliao.entry.VideoSectionEntry;
        import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
        import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
        import com.ziran.meiliao.utils.StringUtils;

        import java.util.Random;

/**
 * Created by Administrator on 2017/1/14.
 */

public class DownloadCourseAdapter extends OneSlideAdapter<CourseEntry> {

    private final Context context;
    Random random;

    public DownloadCourseAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_COURSE;
        random = new Random();
        this.context=context;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, CourseEntry itemData, int position) {
        holder.setImageUrl(R.id.iv_item_buy_course_img, itemData.getPicture(), R.mipmap.ic_loading_square_small);
        holder.setText(R.id.tv_item_buy_course_title, itemData.getTitle());
        holder.setText(R.id.tv_item_buy_course_profile, itemData.getDetail());
        try {
            holder.setText(R.id.tv_item_buy_course_anchor, itemData.getAuthorName());
        } catch (Exception e) {
            e.printStackTrace();
            holder.setText(R.id.tv_item_buy_course_play_count, StringUtils.format(R.string.play_count, 100));
        }
    }

    @Override
    protected void delete(CourseEntry courseEntry) {
        if (EmptyUtils.isNotEmpty(courseEntry)) {
            VideoSectionEntry.deleteWhereCourseId(courseEntry.getCourseId(),context);
            DbCore.getDaoSession().getCourseEntryDao().delete(courseEntry);
        }
    }
}
