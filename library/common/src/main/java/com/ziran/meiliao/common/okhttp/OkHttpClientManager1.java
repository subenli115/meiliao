//package com.ziran.meiliao.common.okhttp;
//
//import android.accounts.NetworkErrorException;
//import android.content.Context;
//import android.os.Handler;
//import android.os.Looper;
//
//import com.google.gson.internal.$Gson$Types;
//import com.okhttplib.HttpInfo;
//import com.okhttplib.OkHttpUtil;
//import com.okhttplib.bean.UploadFileInfo;
//import com.okhttplib.callback.CallbackOk;
//import com.okhttplib.callback.ProgressCallback;
//import com.ziran.meiliao.common.R;
//import com.ziran.meiliao.common.baseapp.BaseApplication;
//import com.ziran.meiliao.common.commonutils.JsonUtils;
//import com.ziran.meiliao.common.commonutils.LogUtils;
//import com.ziran.meiliao.common.commonutils.NetWorkUtils;
//import com.ziran.meiliao.common.commonutils.ToastUitl;
//import com.ziran.meiliao.common.security.EncodeUtil;
//
//import org.apache.http.conn.ConnectTimeoutException;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.net.ConnectException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * Created by zhy on 15/8/17.
// */
//public class OkHttpClientManager1 {
//    public static String BASE_URL;
//    //    private static final String OK_BASE_URL = "http://www.psytap.com/";
////    private static final String TEST_BASE_URL = "http://www.psytap.com:8888/";
////        private static final String OK_BASE_URL = "http://www.dgli.net/";
//    private static final String TEST_BASE_URL = "http://www.dgli.net:8888/";
//    //    private static final String TEST_BASE_URL = "http://www.psytap.com:8888/";
//    private static OkHttpClientManager1 mInstance;
//    private Handler mDelivery;
//
//    private OkHttpClientManager1(Context context) {
////        BASE_URL =  OK_BASE_URL ;
//        BASE_URL = TEST_BASE_URL;
//        mDelivery = new Handler(Looper.getMainLooper());
//    }
//
//    public OkHttpClient getOkHttpClient() {
//        return OkHttpUtil.getDefault().getDefaultClient();
//    }
//
//    public static void init(Context context) {
//        getInstance(context);
//    }
//
//    public static OkHttpClientManager1 getInstance(Context context) {
//        if (mInstance == null) {
//            synchronized (OkHttpClientManager1.class) {
//                if (mInstance == null) {
//                    mInstance = new OkHttpClientManager1(context);
//                }
//            }
//        }
//        return mInstance;
//    }
//
//    public static OkHttpClientManager1 getInstance() {
//        if (mInstance == null) {
//            throw new NullPointerException("OkHttpClientManager must be init meold");
//        }
//        return mInstance;
//    }
//
//    /**
//     * 执行GET 请求
//     *
//     * @param url
//     * @param params
//     * @param callback
//     */
//    public static void getAsync(String url, Map<String, String> params, final ResultCallback callback) {
//        try {
//            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
//                        .string.network_error)), callback);
//                return;
//            }
//            OkHttpUtil.getDefault().doGetAsync(HttpInfo.Builder().setUrl(BASE_URL + url).addParams(params).build(), new CallbackOk() {
//                @Override
//                public void onResponse(HttpInfo info) throws IOException {
//                    doResult(info, callback);
//                }
//            });
//            LogUtils.logd("url = " + url + " /params" + params.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void doResult(HttpInfo info, ResultCallback callback) {
//        if (info.isSuccessful()) {
//            try {
//                String string = info.getRetDetail().trim();
//                if (callback.mType == String.class) {
//                    getInstance().sendSuccessResultCallback(string, callback);
//                } else {
//                    Object o = JsonUtils.fromJson(string, callback.mType);
//                    getInstance().sendSuccessResultCallback(o, callback);
//                }
//                LogUtils.logd(info.getUrl() + " mType " +  callback.mType+ "\n" + string);
//
//            } catch (com.google.gson.JsonParseException e) {
//                getInstance().sendFailedStringCallback(null, e, callback);
//            }
//        }
//    }
//
//    /**
//     * 执行POST 请求
//     *
//     * @param url
//     * @param params
//     * @param callback
//     */
//    public static void postAsync(String url, Map<String, String> params, final ResultCallback callback) {
//        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//            getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R.string
//                    .network_error_p)), callback);
//            return;
//        }
//        OkHttpUtil.getDefault().doPostAsync(HttpInfo.Builder().setUrl(BASE_URL + url).addParams(params).build(), new CallbackOk() {
//            @Override
//            public void onResponse(HttpInfo info) throws IOException {
//                doResult(info, callback);
//            }
//        });
//        LogUtils.logd("url = " + url + " /params" + params.toString());
//    }
//
//
//    /**
//     * 处理请求错误回调
//     *
//     * @param request
//     * @param e
//     * @param callback
//     */
//    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
//        mDelivery.post(new Runnable() {
//            @Override
//            public void run() {
//                if (callback != null) callback.onError(request, e);
//            }
//        });
//    }
//
//    /**
//     * 处理请求成功回调
//     *
//     * @param object
//     * @param callback
//     */
//    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
//        mDelivery.post(new Runnable() {
//            @Override
//            public void run() {
//                if (callback != null) {
//                    callback.onResponse(object.toString());
//                }
//            }
//        });
//    }
//
//    /**
//     * 返回结果基类
//     *
//     * @param <T>
//     */
//    public static abstract class ResultCallback1<T> {
//        Type mType;
//
//        public ResultCallback1() {
//            mType = getSuperclassTypeParameter(getClass());
//        }
//
//        static Type getSuperclassTypeParameter(Class<?> subclass) {
//            Type superclass = subclass.getGenericSuperclass();
//            if (superclass instanceof Class) {
//                throw new RuntimeException("Missing type parameter.");
//            }
//            ParameterizedType parameterized = (ParameterizedType) superclass;
//            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
//        }
//
//        public abstract void onError(Request request, Exception e);
//
//        public abstract void onStart();
//
//        public abstract void onResponse(T response);
//
//    }
//    /**
//     * 返回结果基类
//     *
//     */
//    public static abstract class ResultCallback {
//
//
//        public abstract void onError(Request request, Exception e);
//
//        public abstract void onStart();
//
//        public abstract void onResponse(String response);
//
//    }
//
//
//    /**
//     * 请求回调的对象
//     *
//     * @param <T>
//     */
//    public abstract static class MyRequstCallBack<T extends Result> extends ResultCallback<T> {
//        public String getExceptionMessage(Exception e) {
//            if (e instanceof ConnectException || e instanceof ConnectTimeoutException) {
//                ToastUitl.showShort("服务器繁忙,请稍后重试");
//                return "服务器繁忙,请稍后重试";
//            }
//            return e.getMessage();
//        }
//
//        @Override
//        public void onError(Request request, Exception e) {
//            e.printStackTrace();
//            onError(getExceptionMessage(e), -1);
//        }
//
//        @Override
//        public void onResponse(T result) {
//            try {
//                // 1 表示请求成功,其他表示请求失败
//                if (result == null) {
//                    onError("服务器繁忙,请稍后重试", -1);
//                    return;
//                }
//                switch (result.getResultCode()) {
//                    case 1:
//                        onSuccess(result);
//                        break;
//                    case -5:
//                        onSuccess(result);   //异地登录
//                        ToastUitl.showShort(result.getResultMsg());
//                        onError(result.getResultMsg(), result.getResultCode());
//                        break;
//                    case 10:
//                        ToastUitl.showShort(result.getResultMsg());
//                        showEmpty(result);
//                        break;
//                    default:
//                        onError(result.getResultMsg(), result.getResultCode());
//                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                if (result!=null){
//                    onError(result.getResultMsg(), result.getResultCode());
//                }else{
//                    onError("服务器繁忙,请稍后重试", -1);
//                }
//            }
//        }
//
//        public abstract void onSuccess(T result);
//
//        public void showEmpty(T result) {
//        }
//
//        public abstract void onError(String msg, int code);
//    }
//
//    //执行上传文件操作
//    public static void postContentAndFiles(String url, Map<String, String> map, List<File> files, final OkHttpClientManager1
//            .ResultCallback callback) {
//        try {
//            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
//                        .string.network_error)), callback);
//                return;
//            }
//            List<UploadFileInfo> uploadFiles = new ArrayList<>();
//            for (int i = 0; i < files.size(); i++) {
//                uploadFiles.add(new UploadFileInfo(files.get(i).getPath(), "file", new ProgressCallback() {
//                    @Override
//                    public void onResponseMain(String filePath, HttpInfo info) {
//                        doResult(info, callback);
//                    }
//                }));
//            }
//            HttpInfo info = HttpInfo.Builder().setUrl(BASE_URL + url).addParams(map).addUploadFiles(uploadFiles).build();
//            OkHttpUtil.getDefault().doUploadFileAsync(info);
//            LogUtils.logd("map : " + map.toString() + files.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //执行上传文件操作
//    public static void postContentAndFile(String url, Map<String, String> map, String filePath, final OkHttpClientManager1.ResultCallback
//            callback) {
//        try {
//            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//                getInstance().sendFailedStringCallback(null, new NetworkErrorException(BaseApplication.getAppResources().getString(R
//                        .string.network_error)), callback);
//                return;
//            }
//
//            HttpInfo info = HttpInfo.Builder().setUrl(BASE_URL + url).addParams(map).addUploadFile("file", filePath, new ProgressCallback
//                    () {
//                @Override
//                public void onResponseMain(String filePath, HttpInfo info) {
//                    doResult(info, callback);
//                }
//            }).build();
//            OkHttpUtil.getDefault().doUploadFileAsync(info);
//            LogUtils.logd("map : " + map.toString() + filePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 批量上传文件操作
//     *
//     * @param url
//     * @param map
//     * @param files
//     * @param callback
//     */
//    public static void _postContentAndFiles(String url, final Map<String, String> map, List<File> files, final OkHttpClientManager1
//            .ResultCallback callback) {
//        /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
//        MultipartBody.Builder builder = new MultipartBody.Builder(boundary);
//        builder.setType(MultipartBody.FORM);
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
//        if (files != null && files.size() > 0) {
//            for (int i = 0; i < files.size(); i++) {
//                okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/jpeg"), files.get(i));
//                builder.addFormDataPart("file", files.get(i).getName(), fileBody);
//            }
//        }
//        MultipartBody mBody = builder.build();
//        Request request = new Request.Builder().url(BASE_URL + url).post(mBody).build();
//        deliveryResult(callback, request);
//        LogUtils.logd("url" + BASE_URL + url + "\n map" + map.toString() + "file" + files.toString());
//    }
//
//
////
////    /**
////     * 拼接URL
////     *
////     * @param url
////     * @param parmas
////     * @return
////     */
////    private String parseUrl(String url, Map<String, String> parmas) {
////        StringBuilder sb = new StringBuilder();
////        sb.append(BASE_URL).append(url);
////        if (parmas == null || parmas.size() == 0) return sb.toString();
////        sb.append("?");
////        Set<String> keySet = parmas.keySet();
////        for (String s : keySet) {
////            sb.append(s).append("=").append(parmas.get(s)).append("&");
////        }
////        sb.deleteCharAt(sb.length() - 1);
////        return sb.toString();
////    }
////
////
////    /**
////     * 异步下载文件
////     *
////     * @param url
////     * @param fileName 本地文件存储的文件夹
////     * @param callback
////     */
////    private void _downloadAsyn(final String url, final String fileName, final ResultCallback callback) {
////        final Request request = new Request.Builder().url(url).build();
////        final Call call = mOkHttpClient.newCall(request);
////        call.enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                sendFailedStringCallback(request, e, callback);
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                InputStream is = null;
////                byte[] buf = new byte[2048];
////                int len;
////                FileOutputStream fos = null;
////                try {
////                    is = response.body().byteStream();
////                    File file = new File(fileName);
////                    fos = new FileOutputStream(file);
////                    while ((len = is.read(buf)) != -1) {
////                        fos.write(buf, 0, len);
////                    }
////                    fos.flush();
////                    //如果下载文件成功，第一个参数为文件的绝对路径
////                    sendSuccessResultCallback(file.getAbsolutePath(), callback);
////                } catch (IOException e) {
////                    sendFailedStringCallback(response.request(), e, callback);
////                } finally {
////                    IOUtil.close(is);
////                    IOUtil.close(fos);
////                }
////            }
////        });
////    }
////
////    /**
////     * 将Map对象转成Param对象
////     *
////     * @param params
////     * @return
////     */
////    private Param[] map2Params(Map<String, String> params) {
////        if (params == null) return new Param[0];
////        int size = params.size();
////        Param[] res = new Param[size];
////        Set<Map.Entry<String, String>> entries = params.entrySet();
////        int i = 0;
////        for (Map.Entry<String, String> entry : entries) {
////            String value = entry.getValue();
////            if ("comment".equals(entry.getKey())) {
////                value = EncodeUtil.encodeUTF(entry.getValue());
////            }
////            res[i++] = new Param(entry.getKey(), value);
////        }
////        LogUtils.logd("MAP.params: " + params.toString());
////        return res;
////    }
////
////    /**
////     * 将url 和Param 转为 Request 对象
////     *
////     * @param url
////     * @param params
////     * @return
////     */
////    private Request buildPostRequest(String url, Param[] params) {
////        if (params == null) {
////            params = new Param[0];
////        }
////        FormBody.Builder builder = new FormBody.Builder();
////        for (Param param : params) {
////            builder.add(param.key, param.value);
////        }
////        RequestBody requestBody = builder.build();
////        return new Request.Builder().url(BASE_URL + url).post(requestBody).build();
////    }
//
//    /**
//     * 处理请求结果
//     *
//     * @param callback
//     * @param request
//     */
//    private static void deliveryResult(final ResultCallback callback, final Request request) {
//        if (callback != null) {
//            callback.onStart();
//        }
//        getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String string = response.body().string().trim();
//                    if (callback.mType == String.class) {
//                        getInstance().sendSuccessResultCallback(string, callback);
//                    } else {
//                        Object o = JsonUtils.fromJson(string, callback.mType);
//                        getInstance().sendSuccessResultCallback(o, callback);
//                    }
//                    LogUtils.logd(request.url().toString() + "\n" + string);
//
//                } catch (IOException | com.google.gson.JsonParseException e) {
//                    getInstance().sendFailedStringCallback(response.request(), e, callback);
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                getInstance().sendFailedStringCallback(request, e, callback);
//            }
//        });
//    }
//
//}
