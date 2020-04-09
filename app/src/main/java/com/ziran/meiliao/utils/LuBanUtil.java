package com.ziran.meiliao.utils;

import android.content.Context;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.Luban;
import com.ziran.meiliao.common.compressorutils.OnCompressListener;
import com.ziran.meiliao.envet.MyCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 20:56
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class LuBanUtil {
    private List<String> paths;
    private Context mContext;
    private MyCallBack mMyCallBack;
    private List<File> result;

    public LuBanUtil(Context context, List<String> paths, MyCallBack callBack) {
        this.mContext = context;
        this.paths = paths;
        result = new ArrayList<>();
        this.mMyCallBack = callBack;
    }

    public void startCompress() {
        if (EmptyUtils.isEmpty(paths)){
            if (mMyCallBack!=null){
                mMyCallBack.call();
            }
        }else
        for (int i = 0; i < paths.size(); i++) {
            Luban.get(mContext).load(new File(paths.get(i))).putGear(Luban.THIRD_GEAR).setCompressListener(mOnCompressListener).launch();
        }
    }

    private OnCompressListener mOnCompressListener = new OnCompressListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(File file) {
            result.add(file);
            if (result.size() == paths.size()) {
                if (mMyCallBack!=null){
                    mMyCallBack.call();
                }
            }
        }

        @Override
        public void onError(Throwable e) {

        }
    };

    public List<File> getResult() {
        return result;
    }
}
