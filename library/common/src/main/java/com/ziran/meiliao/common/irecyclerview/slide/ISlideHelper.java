package com.ziran.meiliao.common.irecyclerview.slide;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/24 14:26
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/24$
 * @updateDes ${TODO}
 */

public interface ISlideHelper {
    boolean isCheck ();
    void slideOpen ();
    void slideClose ( );
    void clearSelect ( );
    void selectAll ( );
    int getSelectSize ( );
    void addOrRemove(int position, boolean isCheck);
    void delete (String msg );
}
