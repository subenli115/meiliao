package com.ziran.meiliao.common.okhttp;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.bean.UploadFileInfo;
import com.okhttplib.callback.CallbackOk;
import com.okhttplib.callback.ProgressCallback;
import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.baseapp.BaseApplication;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.security.EncodeUtil;

import java.io.File;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.BaseUrl;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpClientManager {
    private static String BASE_URL;

    private static final String OK_BASE_URL = "http://api.ziran518.com:9999/";
//    private static final String TEST_BASE_URL = "http://192.168.1.5:9999/";
    private static final String TEST_BASE_URL = "http://39.98.156.53:9999/";


    private static OkHttpClientManager mInstance;
    private Handler mDelivery;

    private OkHttpClientManager(Context context) {
        BASE_URL = TEST_BASE_URL;
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public OkHttpClient getOkHttpClient() {
        return OkHttpUtil.getDefault().getDefaultClient();
    }

    public static void init(Context context) {
        getInstance(context);
    }

    public static OkHttpClientManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager(context);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("OkHttpClientManager must be init meold");
        }
        return mInstance;
    }

    /**
     * 执行GET 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void getAsync(String url, Map<String, String> params, final ResultCallback callback) {
        try {
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
                        .string.network_error)), callback);
                return;
            }
            if (callback != null) callback.onStart();
            OkHttpUtil.getDefault().doGetAsync(HttpInfo.Builder().setUrl(BASE_URL + url).addParams(params).build(), new CallbackOk() {
                @Override
                public void onResponse(HttpInfo info) {
                    doResult(info, callback);
                }
            });
            LogUtils.logd("url = " + url + " /params" + params.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行GET 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void getAsyncOne(String url, String params, final ResultCallback callback) {
        try {
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
                        .string.network_error)), callback);
                return;
            }
            if (callback != null) callback.onStart();

            OkHttpUtil.getDefault().doGetAsync(HttpInfo.Builder().setUrl(BASE_URL + url + "/" + params).build(), new CallbackOk() {
                @Override
                public void onResponse(HttpInfo info) {
                    doResult(info, callback);
                }
            });
            LogUtils.logd("urlgetAsyncOne = " + BASE_URL + url + "/" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行GET 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void getDataOneHead(String url, String params,String token ,final ResultCallback callback) {
        try {
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
                        .string.network_error)), callback);
                return;
            }
            if (callback != null) callback.onStart();

            OkHttpUtil.getDefault().doGetAsync(HttpInfo.Builder().setUrl(BASE_URL + url + "/" + params).addHead("Authorization","Bearer "+token).build(), new CallbackOk() {
                @Override
                public void onResponse(HttpInfo info) {
                    doResult(info, callback);
                }
            });
            LogUtils.logd("urlgetAsyncOne = " + BASE_URL + url + "/" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 执行GET 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void getAsyncMore(String url, Map<String, String> params, final ResultCallback callback) {
        LogUtils.logd("url = " + url + " /params" + params.toString());
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            getInstance().sendFailedStringCallback(null, new NetworkErrorException(
                    BaseApplication.getAppResources().getString(R.string
                            .network_error_p)), callback);
            return;
        }
        if (callback != null) callback.onStart();
        String headString = "";
        if (params.get("accessToken") != null && !params.get("accessToken").equals("")) {
            headString = "Bearer " + params.get("accessToken");
        } else {
            headString = "Basic YXBwOmFwcA==";
        }
        OkHttpUtil.getDefault().doGetAsync(HttpInfo.Builder().setUrl(BASE_URL + url).addParams(params).addHead("Authorization", headString).build(), new CallbackOk() {
            @Override
            public void onResponse(HttpInfo info) throws IOException {
                doResult(info, callback);
            }
        });
        LogUtils.logd(headString);
    }

    /**
     * 执行GET 请求
     *
     * @param url
     * @param params
     * @param accessToken
     * @param callback
     */
    public static void getAsyncHead(String url, String params, String accessToken, final ResultCallback callback) {
        try {
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
                        .string.network_error)), callback);
                return;
            }
            if (callback != null) callback.onStart();
            HttpInfo.Builder builder = HttpInfo.Builder();
            String headString = "";
            if (accessToken != null && !accessToken.equals("")) {
                headString = "Bearer " + accessToken;
            } else {
                headString = "Basic YXBwOmFwcA==";
            }
//            if (params.size() <= 1) {
            builder.addHead("Authorization", headString).setUrl(BASE_URL + url + "/" + params);
//            } else {
//                builder.addHead("Authorization", headString).setUrl(BASE_URL + url).addParams(params);
//            }
            OkHttpUtil.getDefault().doGetAsync(builder.build(), new CallbackOk() {
                @Override
                public void onResponse(HttpInfo info) {
                    doResult(info, callback);
                }
            });
            LogUtils.logd("url = " + url + " /params" + params+headString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doResult(HttpInfo info, ResultCallback callback) {
        if (info.isSuccessful()) {
            try {
                String string = info.getRetDetail().trim();
                getInstance().sendSuccessResultCallback(string, callback);
                if (info.getParams() != null) {
                    LogUtils.logd(info.getUrl() + " \n HttpInfo getParams " + info.getParams().toString() + "\n" + string);
                }
            } catch (com.google.gson.JsonParseException e) {
                getInstance().sendFailedStringCallback(null, e, callback);
            }
        }
    }

    /**
     * 执行POST 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void postAsync(String url, Map<String, String> params, final ResultCallback callback) {
        LogUtils.logd("url = " + url + " /params" + params.toString());
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R.string
                    .network_error_p)), callback);
            return;
        }
        if (callback != null) callback.onStart();
        OkHttpUtil.getDefault().doPostAsync(HttpInfo.Builder().setUrl(BASE_URL + url).addParams(params).build(), new CallbackOk() {
            @Override
            public void onResponse(HttpInfo info) throws IOException {
                doResult(info, callback);
            }
        });
//        LogUtils.logd("url = " + url + " /params" + params.toString());
    }

    /**
     * 执行POST 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void postAsyncAddHead(String url, Map<String, String> params, String rUrl, final ResultCallback callback) {
        LogUtils.logd("url = " + url + " /params" + params.toString());
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R.string
                    .network_error_p)), callback);
            return;
        }
        if (callback != null) callback.onStart();
        String headString = "";
        if (params.get("accessToken") != null && !params.get("accessToken").equals("")) {
            headString = "Bearer " + params.get("accessToken");
        } else {
            headString = "Basic YXBwOmFwcA==";
        }
        Log.e("postAsyncAddHead",""+headString);
        OkHttpUtil.getDefault().doPostAsync(HttpInfo.Builder().setUrl(BASE_URL + url + rUrl).addParams(params).addHead("Authorization", headString).addHead("Accept-Language"," zh-CN,zh").build(), new CallbackOk() {
            @Override
            public void onResponse(HttpInfo info) throws IOException {
                doResult(info, callback);
            }
        });
        LogUtils.logd(headString);
    }

    /**
     * 执行Put 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void putAsyncAddHead(String url, Map<String, String> params, final ResultCallback callback) {
        LogUtils.logd("url = " + url + " /params" + params.toString());
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            getInstance().sendFailedStringCallback(null, new NetworkErrorException(
                    BaseApplication.getAppResources().getString(R.string
                            .network_error_p)), callback);
            return;
        }
        if (callback != null) callback.onStart();
        String headString = "";
        if (params.get("accessToken") != null && !params.get("accessToken").equals("")) {
            headString = "Bearer " + params.get("accessToken");
        } else {
            headString = "Basic YXBwOmFwcA==";
        }
        OkHttpUtil.getDefault().doPutAsync(HttpInfo.Builder().setUrl(BASE_URL + url).addParams(params).addHead("Authorization", headString).build(), new CallbackOk() {
            @Override
            public void onResponse(HttpInfo info) throws IOException {
                doResult(info, callback);
            }
        });
        LogUtils.logd(headString);
    }

    /**
     * 执行delete 请求
     *
     * @param url
     * @param callback
     */
    public static void deleteAsync(String url,  Map<String, String> params,String id ,final ResultCallback callback) {
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            getInstance().sendFailedStringCallback(null, new NetworkErrorException(
                    BaseApplication.getAppResources().getString(R.string
                            .network_error_p)), callback);
            return;
        }
        if (callback != null) callback.onStart();
        String headString = "";
        if (params.get("accessToken") != null && !params.get("accessToken").equals("")) {
            headString = "Bearer " + params.get("accessToken");
        } else {
            headString = "Basic YXBwOmFwcA==";
        }
        OkHttpUtil.getDefault().doDeleteAsync(HttpInfo.Builder().setUrl(BASE_URL + url+ "/" + id).addHead("Authorization", headString).build(), new CallbackOk() {
            @Override
            public void onResponse(HttpInfo info) throws IOException {
                doResult(info, callback);
            }
        });
        LogUtils.logd(headString);
    }

    /**
     * 处理请求错误回调
     *
     * @param request
     * @param e
     * @param callback
     */
    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) callback.onError(request, e);
            }
        });
    }

    /**
     * 处理请求成功回调
     *
     * @param object
     * @param callback
     */
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object.toString());
                }
            }
        });
    }

    public static void setDebug(boolean debug) {
        BASE_URL = debug ? TEST_BASE_URL : OK_BASE_URL;
    }

    /**
     * 返回结果基类
     */
    public static abstract class ResultCallback {

        public abstract void onError(Request request, Exception e);

        public abstract void onStart();

        public abstract void onResponse(String response);

    }


    //执行上传文件操作
    public static void postContentAndFiles(String url, Map<String, String> map, List<File> files, final OkHttpClientManager
            .ResultCallback callback) {
        try {
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
                        .string.network_error)), callback);
                return;
            }
            List<UploadFileInfo> uploadFiles = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                uploadFiles.add(new UploadFileInfo(files.get(i).getPath(), "file", new ProgressCallback() {
                    @Override
                    public void onResponseMain(String filePath, HttpInfo info) {
                        doResult(info, callback);
                    }
                }));
            }
            Log.e("uploadFiles",""+uploadFiles.size());
            HttpInfo info = HttpInfo.Builder().setUrl(BASE_URL + url).addParams(map).addHead("Authorization", "Bearer " + map.get("accessToken")).addUploadFiles(uploadFiles).build();
            OkHttpUtil.getDefault().doUploadFileAsync(info);
            LogUtils.logd("map : " + map.toString() + files.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //执行上传文件操作
    public static void postContentAndFile(String url, Map<String, String> map, String filePath, final OkHttpClientManager.ResultCallback
            callback) {
        try {
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
                        .string.network_error)), callback);
                return;
            }

            HttpInfo info = HttpInfo.Builder().setUrl(BASE_URL + url).addParams(map).addUploadFile("file", filePath, new ProgressCallback
                    () {
                @Override
                public void onResponseMain(String filePath, HttpInfo info) {
                    doResult(info, callback);
                }
            }).build();
            OkHttpUtil.getDefault().doUploadFileAsync(info);
            LogUtils.logd("map : " + map.toString() + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url
     * @param pathList
     * @throws Exception
     */
    public static void upLoadFiles(final String url, final String params, final List<String> pathList, final OkHttpClientManager.ResultCallback
            callback ){
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
            MediaType MutilPart_Form_Data = MediaType.parse("multipart/form-data; charset=utf-8");
            RequestBody bodyParams = RequestBody.create(MutilPart_Form_Data, JSON.toJSONString(params));
            MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("keyVo", "", bodyParams);
            //循环添加文件
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));
                requestBodyBuilder.addFormDataPart("file", file.getName(), RequestBody.create(MutilPart_Form_Data, new File(pathList.get(i))));
            }
            RequestBody requestBody = requestBodyBuilder.build();

            Request request = new Request.Builder()
                    .url(BASE_URL + url)
                    .addHeader("Authorization","Bearer "+params )
                    .post(requestBody)
                    .build();
            deliveryResult(callback, request);
    }


    /**
     * 批量上传文件操作
     *
     * @param url
     * @param callback
     */
    public static void _postContentAndFiles(String url, String token, List<File> files, final OkHttpClientManager
            .ResultCallback callback) {
        /* form的分割线,自己定义 */
        String boundary = "xx--------------------------------------------------------------xx";
        MultipartBody.Builder builder = new MultipartBody.Builder(boundary);
        builder.setType(MultipartBody.FORM);
//        if (map != null && map.size() > 0) {
//            Set<String> keySet = map.keySet();
//            for (String key : keySet) {
//                String value = map.get(key);
//                if ("text".equals(key) || "content".equals(key)) {
//                    value = EncodeUtil.encodeUTF(value);
//                }
//                builder.addFormDataPart(key, value);
//            }
//        }
        if (files != null && files.size() > 0) {
            for (int i = 0; i < files.size(); i++) {
                okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/jpeg"), files.get(i));
                builder.addFormDataPart("file", files.get(i).getName(), fileBody);
            }
        }
        MultipartBody mBody = builder.build();
        Request request = new Request.Builder().url(BASE_URL + url).addHeader("Authorization",token).post(mBody).build();
        deliveryResult(callback, request);
    }


    /**
     * 处理请求结果
     *
     * @param callback
     * @param request
     */
    private static void deliveryResult(final ResultCallback callback, final Request request) {
        if (callback != null) {
            callback.onStart();
        }
        getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String string = response.body().string().trim();
                    getInstance().sendSuccessResultCallback(string, callback);

                    LogUtils.logd(request.url().toString() + "\n" + string);

                } catch (IOException | com.google.gson.JsonParseException e) {
                    getInstance().sendFailedStringCallback(response.request(), e, callback);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getInstance().sendFailedStringCallback(request, e, callback);
            }
        });
    }

}
