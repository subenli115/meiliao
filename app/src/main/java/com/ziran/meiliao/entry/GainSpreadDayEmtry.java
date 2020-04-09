package com.ziran.meiliao.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/11 9:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/11$
 * @updateDes ${TODO}
 */

@Entity
public class GainSpreadDayEmtry  {


    @Id
    private Long id;
    public String dayTime;	//文件夹名称
    public boolean isShow;
    @Generated(hash = 1402034454)
    public GainSpreadDayEmtry(Long id, String dayTime, boolean isShow) {
        this.id = id;
        this.dayTime = dayTime;
        this.isShow = isShow;
    }
    @Generated(hash = 1678640146)
    public GainSpreadDayEmtry() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDayTime() {
        return this.dayTime;
    }
    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

}
