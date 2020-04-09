package com.ziran.meiliao.ui.NewDecompressionmuseum.asrpartialjson;

/**
 * Created by Administrator on 2018/10/30.
 */

import java.util.ArrayList;

public class AsrPartialJsonData {
    private ArrayList<String> results_recognition;
    private OriginResult origin_result;
    private String error;
    private String best_result;
    private String result_type;

    public ArrayList<String> getResults_recognition() {
        return results_recognition;
    }

    public OriginResult getOrigin_result() {
        return origin_result;
    }

    public String getBest_result() {
        return best_result;
    }

    public String getError() {
        return error;
    }

    public String getResult_type() {
        return result_type;
    }
}