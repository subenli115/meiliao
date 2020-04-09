package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.imagePager.Constant;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.workshops.widget.TitleGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 15:01
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class ImageAdapter extends CommonAblistViewAdapter<String> {

    public static String ADD_PIC = "ADD_PIC";

    public ImageAdapter(Context context) {
        super(context, R.layout.item_image);
        add(ADD_PIC);
    }

    @Override
    public void convert(ViewHolderHelper holder, String s, int position) {
        ImageView iv = holder.getView(R.id.iv_item_image);
        if (ADD_PIC.equals(s)) {
            iv.setImageResource(R.mipmap.btn_add_pic);
        } else {
            ImageLoaderUtils.display(mContext, iv, s, R.mipmap.ic_loading_rectangle);
        }
    }


    @Override
    public void addAll(List<String> elements) {
        if (elements.size() < 6) {
            elements.add(ADD_PIC);
        }
        super.addAll(elements);
    }


    @Override
    public List<String> getAll() {
        List<String> result = new ArrayList<>(super.getAll());
        result.remove(ADD_PIC);
        return result;
    }

    public void replaceAllNew(List<String> elements, boolean isAdd) {
        if (elements.size() < 6 && isAdd) {
            elements.add(ADD_PIC);
        }
        mDatas = elements;
    }

    public void addAllNew(List<String> elements, boolean isAdd) {
        List<String> data = getAll();
        data.addAll(elements);
        if (data.size() < 6 && isAdd) {
            data.add(ADD_PIC);
        }
        mDatas = data;
    }

    @Override
    public void replaceAll(List<String> elements) {
        if (elements.size() < 6) {
            elements.add(ADD_PIC);
        }
        super.replaceAll(elements);
    }

    public boolean isAdd(int position) {
        return ADD_PIC.equals(get(position));
    }

    public void refreshData(List<String> deleteUrl, TitleGridView tgvPic) {
        LogUtils
                .logd("deleteUrl:"+deleteUrl);
        if (EmptyUtils.isNotEmpty(deleteUrl)){
            List<String> strings = getAll();
            strings.removeAll(deleteUrl);
            if (!strings.contains(ADD_PIC) ){
                strings.add(ADD_PIC);
            }
            mDatas = strings;
            tgvPic.setAdapter(this);
            Constant.deleteUrl.clear();
        }
    }
}
