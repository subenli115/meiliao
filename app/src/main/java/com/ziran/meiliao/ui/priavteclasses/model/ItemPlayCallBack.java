package com.ziran.meiliao.ui.priavteclasses.model;

import com.ziran.meiliao.entry.VideoSectionEntry;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/26 17:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/26$
 * @updateDes ${TODO}
 */

public interface ItemPlayCallBack {
    void playUrl(VideoSectionEntry videoSectionEntry);
    void playShiKan(VideoSectionEntry videoSectionEntry);
    int isLiveStreaming();
    boolean isPlaying();
}
