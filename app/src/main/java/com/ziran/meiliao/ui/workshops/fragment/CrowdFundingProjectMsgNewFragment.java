//package com.ziran.meiliao.ui.workshops.fragment;
//
//import android.os.Bundle;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//
//import com.citypicker.Interface.OnCityItemClickListener;
//import com.citypicker.bean.CityBean;
//import com.citypicker.bean.DistrictBean;
//import com.citypicker.bean.ProvinceBean;
//import com.citypicker.citywheel.CityConfig;
//import com.citypicker.citywheel.CityPickerView;
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.common.baseapp.AppManager;
//import com.ziran.meiliao.common.commonutils.JsonUtils;
//import com.ziran.meiliao.common.commonutils.KeyBordUtil;
//import com.ziran.meiliao.common.commonutils.ViewUtil;
//import com.ziran.meiliao.common.commonwidget.FilterTextView;
//import com.ziran.meiliao.common.commonwidget.LoadingDialog;
//import com.ziran.meiliao.common.compressorutils.EmptyUtils;
//import com.ziran.meiliao.common.compressorutils.FileUtil;
//import com.ziran.meiliao.common.imagePager.Constant;
//import com.ziran.meiliao.common.imagePager.ImagePreviewActivity;
//import com.ziran.meiliao.common.okhttp.Result;
//import com.ziran.meiliao.constant.ApiKey;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.constant.BaseItemId;
//import com.ziran.meiliao.envet.MyCallBack;
//import com.ziran.meiliao.ui.base.CommonHttpFragment;
//import com.ziran.meiliao.ui.base.CommonModel;
//import com.ziran.meiliao.ui.bean.CheckMsgBean;
//import com.ziran.meiliao.ui.bean.CrowdFundingPreviewBean;
//import com.ziran.meiliao.ui.bean.CrowdFundingPreviewDetailBean;
//import com.ziran.meiliao.ui.bean.OrderCreateResultBean;
//import com.ziran.meiliao.ui.bean.SearchItemBean;
//import com.ziran.meiliao.ui.me.activity.MyCrowdFundingOrderActivity;
//import com.ziran.meiliao.ui.workshops.activity.CrowdFundingChooseTopicActivity;
//import com.ziran.meiliao.ui.workshops.activity.CrowdFundingPreviewActivity;
//import com.ziran.meiliao.ui.workshops.activity.CrowdFundingUsedTemplateActivity;
//import com.ziran.meiliao.ui.workshops.adapter.CheckMsgAdapter;
//import com.ziran.meiliao.ui.workshops.adapter.ImageAdapter;
//import com.ziran.meiliao.ui.workshops.contract.CrowdFundingProjectMsgContract;
//import com.ziran.meiliao.ui.workshops.presenter.CrowdFundingProjectMsgPresenter;
//import com.ziran.meiliao.ui.workshops.util.CheckBaseItemUtil;
//import com.ziran.meiliao.ui.workshops.util.CrowFundingPreviewUtil;
//import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
//import com.ziran.meiliao.ui.workshops.widget.PriceSelectView;
//import com.ziran.meiliao.ui.workshops.widget.TitleGridView;
//import com.ziran.meiliao.utils.HtmlUtil;
//import com.ziran.meiliao.utils.LuBanUtil;
//import com.ziran.meiliao.utils.MapUtils;
//import com.ziran.meiliao.widget.BaseItemView;
//import com.ziran.meiliao.widget.SmoothCheckBox;
//import com.ziran.meiliao.widget.pupop.BasePopupWindow;
//import com.ziran.meiliao.widget.pupop.DataPopupWindow;
//import com.ziran.meiliao.widget.pupop.ReferenceTemplateWindow;
//import com.yuyh.library.imgsel.ImgSelActivity;
//import com.yuyh.library.imgsel.ImgSelConfig;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//import rx.functions.Action1;
//
///**
// * 我要众筹个人信息填写界面
// * Created by Administrator on 2017/1/7.
// */
//
//public class CrowdFundingProjectMsgNewFragment extends CommonHttpFragment<CrowdFundingProjectMsgPresenter, CommonModel> implements
//        CrowdFundingProjectMsgContract.View, BaseItemId, AppConstant.SearchId {
//
//
//    @Bind(R.id.bivCourse)
//    BaseItemView bivCourse;
//    @Bind(R.id.bivTeacher)
//    BaseItemView bivTeacher;
//    @Bind(R.id.bivType)
//    BaseItemView bivType;
//    @Bind(R.id.bivTime)
//    BaseItemView bivTime;
//    @Bind(R.id.bivAddress)
//    BaseItemView bivAddress;
//    @Bind(R.id.bivOfficialPrice)
//    BaseItemView bivOfficialPrice;
//    @Bind(R.id.bivPeople)
//    BaseItemView bivPeople;
//    @Bind(R.id.bivOnePrice)
//    BaseItemView bivOnePrice;
//    @Bind(R.id.bivDays)
//    BaseItemView bivDays;
//    @Bind(R.id.bivCourseProfile)
//    BaseItemView bivCourseProfile;
//    @Bind(R.id.tgv_pic)
//    TitleGridView tgvPic;
//    @Bind(R.id.bivSponsorMsg)
//    BaseItemView bivSponsorMsg;
//    @Bind(R.id.bivSponsorAvatar)
//    BaseItemView bivSponsorAvatar;
//    @Bind(R.id.pricedSelectView)
//    PriceSelectView mPriceSelectView;
//    @Bind(R.id.bivSponsorName)
//    BaseItemView bivSponsorName;
//    @Bind(R.id.bivCourseContent)
//    BaseItemView bivCourseContent;
//    @Bind(R.id.bivSponsorProfile)
//    BaseItemView bivSponsorProfile;
//    @Bind(R.id.bivSponsorDemo)
//    BaseItemView bivSponsorDemo;
//    @Bind(R.id.tgv_check)
//    TitleGridView tgvCheck;
//    @Bind(R.id.checkbox_service)
//    SmoothCheckBox checkboxService;
//    @Bind(R.id.et_service_phone)
//    EditText etServicePhone;
//    @Bind(R.id.tv_crowd_funding_project_msg_submit)
//    FilterTextView tvCrowdFundingProjectMsgSubmit;
//    @Bind(R.id.tv_edit_template)
//    FilterTextView tvEditTemplate;
//    private ImageAdapter mImageAdapter;
//    private List<String> typeList;
//
//    private CheckMsgAdapter checkMsgAdapter;
//    private SearchItemBean mCourseBean;
//    private SearchItemBean mTeacherBean;
//    private String avatar;
//    private String _name;
//    private String _picture;
//    private String _id;
//    private int _type;
//    private String  price;
//    @Override
//    protected int getLayoutResource() {
//        return R.layout.fragment_crowd_funding_project_msg_new;
//    }
//
//
//    @Override
//    protected void initBundle(Bundle extras) {
//        if (extras.containsKey(AppConstant.ExtraKey.FROM_ID)) {
//            _id = extras.getString(AppConstant.ExtraKey.FROM_ID);
//
//            // type  1 课题  2 .老师
//            _type = extras.getInt(AppConstant.ExtraKey.FROM_TYPE);
//            _name = extras.getString("name");
//            _picture = extras.getString("picture");
//            if ( _type == 2){
//                mTeacherBean = new SearchItemBean();
//                mTeacherBean.setId(_id);
//                mTeacherBean.setName(_name);
//                mTeacherBean.setPicture(_picture);
//            }else if (_type==1){
//                mCourseBean = new SearchItemBean();
//                mCourseBean.setId(_id);
//                mCourseBean.setTitle(_name);
//                mCourseBean.setPicture(_picture);
//                if (extras.containsKey("price")){
//                    price = extras.getString("price");
//
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mImageAdapter != null) {
//            mImageAdapter.refreshData(Constant.deleteUrl, tgvPic);
//        }
//        stopProgressDialog();
//    }
//
//
//    private String onePrice = "";
//    @Override
//    protected void initOther() {
//        mDataPopupWindow = new DataPopupWindow(getContext());
//        mPriceSelectView.setOnValueChangeListener(new PriceSelectView.OnValueChangeListener() {
//            @Override
//            public void onValueChange(String newValue) {
//                onePrice = newValue;
//                bivOnePrice.setContent(newValue+"/人");
//            }
//        });
//        checkboxService.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
//                if (isChecked) {
//                    KeyBordUtil.showSoftKeyboard(etServicePhone);
//                } else {
//                    ViewUtil.setFocusable(etServicePhone, false);
//                }
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.IMAGE_RESULT, new Action1<Message>() {
//            @Override
//            public void call(Message msg) {
//                if (msg.obj == null) return;
//                if (msg.what == WHAT_FROM_TYPE_CROWD_FUNDING_PROJECT_MSG) {
//                    mImageAdapter.addAllNew((List) msg.obj, true);
//                    tgvPic.setAdapter(mImageAdapter);
//                } else if (msg.what == WHAT_FROM_TYPE_CROWD_FUNDING_PROJECT_MSG_AVATAR) {
//                    avatar = ((List) msg.obj).get(0).toString();
//                    bivSponsorAvatar.setImageUrl(avatar);
//                }
//            }
//        });
//        if (EmptyUtils.isNotEmpty(price)){
//            bivOfficialPrice.setContent(price+"");
//        }
//        mRxManager.on(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, new Action1<Message>() {
//            @Override
//            public void call(Message msg) {
//                switch (msg.what) {
//                    case WHAT_TOPIC:
//                        mCourseBean = (SearchItemBean) msg.obj;
//                        bivCourse.setContent(mCourseBean.getTitle());
//                        bivOfficialPrice.setContent(String.valueOf(mCourseBean.getOfficePrice()));
//                        break;
//                    case WHAT_TEACHER:
//                        mTeacherBean = (SearchItemBean) msg.obj;
//                        bivTeacher.setContent(mTeacherBean.getName());
//                        break;
//                    case 666:
//                        showUsedTemplate(true);
//                        break;
//                }
//            }
//        });
//        mRxManager.on(AppConstant.ExtraKey.CLEAR_FILTER, new Action1<String >() {
//            @Override
//            public void call(String s) {
//                if ("course".equals(s)){
//                    mCourseBean = null;
//                    bivCourse.setContent("");
//                }else if ("teacher".equals(s)){
//                    mTeacherBean = null;
//                    bivTeacher.setContent("");
//                }
//            }
//        });
//        mDataPopupWindow.setOnDissmisListener(new BasePopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                if (mDataPopupWindow.getItem() == bivType &&  "讲座".equals(bivType.getContent())){
//                    bivPeople.setContent("");
//                }
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, new Action1<Integer>() {
//            @Override
//            public void call(Integer id) {
//                if (isPause) return;
//                String name;
//                switch (id) {
//                    case ID_COURSE:
//                        if (EmptyUtils.isNotEmpty(_id) && _type == 2) {
//                            name = _name;
//                            CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TOPIC, _id, name,true);
//                        } else if (EmptyUtils.isNotEmpty(_id) && _type == 1){
//                            bivCourse.setEnabled(false);
//                        }else{
//                            String teacherId = mTeacherBean != null ? mTeacherBean.getId() : "";
//                            name = mTeacherBean != null ? mTeacherBean.getName() : "";
//                            CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TOPIC, teacherId, name);
//                        }
//                        break;
//                    case ID_TEACHER:
//                        if (EmptyUtils.isNotEmpty(_id) && _type == 1) {
//                            name = _name;
//                            CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TEACHER, _id, name,true);
//                        } else if (EmptyUtils.isNotEmpty(_id) && _type == 2){
//                            bivTeacher.setEnabled(false);
//                        }else{
//                            String courseId = String.valueOf(mCourseBean != null ? mCourseBean.getId() : "");
//                            name = mCourseBean != null ? mCourseBean.getTitle() : "";
//                            CrowdFundingChooseTopicActivity.startAction(TYPE_FORM_TEACHER, courseId, name);
//                        }
//
//                        break;
//                    case ID_TYPE:
//                        if (typeList == null) {
//                            typeList = new ArrayList<>();
//                            typeList.add("讲座");
//                            typeList.add("工作坊");
//                            typeList.add("团建");
//                        }
//                        mDataPopupWindow.setItemData(typeList, bivType, "课程形式");
//                        break;
//
//                    case ID_ADDRESS:
//                        showCityPicker();
//                        break;
//                    case ID_PEOPLE:
//                        int minPeople = EmptyUtils.isNotEmpty(bivType.getContent()) && "讲座".equals(bivType.getContent())?20:15;
//                        int maxPeople = EmptyUtils.isNotEmpty(bivType.getContent()) && "讲座".equals(bivType.getContent())?300:50;
//                        int space = EmptyUtils.isNotEmpty(bivType.getContent()) && "讲座".equals(bivType.getContent())?10:1;
//                        mDataPopupWindow.setItemData(DataPopupWindow.getPeopleData(minPeople, maxPeople, space), bivPeople, "人数");
//                        break;
//                    case ID_CROWD_FUNDING_CROWD_FUNDING_TIME:
//                        mDataPopupWindow.setItemData(DataPopupWindow.getPeopleData(60, 95, 1), bivDays, "众筹天数");
//                        break;
//                    case ID_CROWD_FUNDING_CROWD_OFFICIAL_PRICE:
//
//                        break;
//                    case ID_CROWD_FUNDING_CROWD_USED_TEMPLE:
//                        if (showEditTemplate) {
//                            showUsedTemplate(false);
//                            QJGDataUtil.clearTemplateData();
//                        } else {
//                            if (bivCourse.checkNull("请选择课程") && bivTeacher.checkNull("请选择老师")) {
//                                if (isPreviewTemplateData) {
//                                    QJGDataUtil.clearTemplateData();
//                                }
//                                String courseId = mCourseBean.getId();
//                                String teacherId = mTeacherBean.getId();
//                                Bundle bundle = getBundle("1");
//                                bundle.putString("courseId",courseId);
//                                bundle.putString("teacherId",teacherId);
//                                startActivity(CrowdFundingUsedTemplateActivity.class,bundle);
//                            }
//                        }
//                        break;
//                    case ID_CROWD_FUNDING_CROWD_SPONSOR_MSG:
//                        showReferenceTemplate();
//                        break;
//                    case ID_CROWD_FUNDING_CROWD_AVATAR:
//                        ImgSelActivity.startActivity(getActivity(), ImgSelConfig.DEFAULT_SIGN_HEAD(""), ImgSelConfig.RequestCode,
//                                WHAT_FROM_TYPE_CROWD_FUNDING_PROJECT_MSG_AVATAR);
//                        break;
//                }
//            }
//        });
//
//        checkMsgAdapter = new CheckMsgAdapter(getContext());
//        checkMsgAdapter.addAll(QJGDataUtil.getCheckMsg());
//        tgvCheck.setAdapter(checkMsgAdapter);
//
//        mImageAdapter = new ImageAdapter(getContext());
//        tgvPic.setAdapter(mImageAdapter);
//        tgvPic.setGridViewPadding(12);
//        tgvPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mImageAdapter.isAdd(position)) {
//                    List<String> selectAll = mImageAdapter.getAll();
//                    ImgSelConfig.SELECT_DATA = new ArrayList<>(selectAll);
//                    ImgSelActivity.startActivity(getActivity(), ImgSelConfig.MULTI_SELECT(6 - selectAll.size()), ImgSelConfig
//                            .RequestCode, WHAT_FROM_TYPE_CROWD_FUNDING_PROJECT_MSG);
//                } else {
//                    ArrayList<String> strings = new ArrayList<>(mImageAdapter.getAll());
//                    ImagePreviewActivity.startImagePagerActivity(getActivity(), strings, position);
//                }
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.SUBMIT_CROWD_FUNDING_MSG, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                submit();
//            }
//        });
//
//        if (EmptyUtils.isNotEmpty(_id)) {
//            if (_type == 1) {
//                bivCourse.setContent(_name);
//            } else if (_type == 2) {
//                bivTeacher.setContent(_name);
//            }
//        }
//
//    }
//
//    private CityPickerView cityPicker;
//
//    private void showCityPicker() {
//        if (cityPicker == null) {
//            CityConfig cityConfig = new CityConfig.Builder(getContext()).itemPadding(24).build();
//            cityPicker = new CityPickerView(cityConfig);
//            cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
//                @Override
//                public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
//                    if (district != null) {
//                        bivAddress.setContent(HtmlUtil.format("%s省%s市%s", province.getName(), city.getName(), district.getName()));
//                    } else {
//                        //返回结果
//                        bivAddress.setContent(HtmlUtil.format("%ss省%s市", province.getName(), city.getName()));
//                    }
//                }
//            });
//        }
//        cityPicker.show();
//    }
//
//    private boolean showEditTemplate;
//
//    private void showUsedTemplate(boolean show) {
//        showEditTemplate = show;
//        bivCourseContent.setRightText(show ? "取消模版" : "使用模版");
//        tvEditTemplate.setVisibility(show ? View.VISIBLE : View.GONE);
//        tgvPic.setVisibility(show ? View.GONE : View.VISIBLE);
//        bivCourseProfile.setVisibility(show ? View.GONE : View.VISIBLE);
//    }
//
//    private DataPopupWindow mDataPopupWindow;
//
//    public void showReferenceTemplate() {
//        ReferenceTemplateWindow referenceTemplateWindow = new ReferenceTemplateWindow(getContext());
//        referenceTemplateWindow.show(this);
//    }
//
//
//    @OnClick({R.id.tv_crowd_funding_project_msg_preview, R.id.tv_crowd_funding_project_msg_submit, R.id.tv_edit_template})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_crowd_funding_project_msg_preview:
//                if (CheckBaseItemUtil.check(bivCourse, bivTeacher, bivType, bivAddress, bivOfficialPrice, bivOnePrice, bivDays,
//                        bivSponsorName)) {
//                    if (!showEditTemplate) {
//                        QJGDataUtil.setTemplateData(createTemplateData());
//                    }
//                    CrowdFundingPreviewDetailBean detailBean = QJGDataUtil.setTemplateHeadAndFooter();
//                    detailBean.setHead(mCourseBean.getPicture(), mCourseBean.getTitle(), bivAddress.getContent(), 0, Integer
//                            .parseInt(bivDays.getContent()), Integer.parseInt(bivPeople.getContent()));
//                    detailBean.setFooter(bivSponsorName.getContent(), avatar, bivSponsorProfile.getContent(), bivSponsorDemo.getContent());
//                    CrowFundingPreviewUtil.setParams(setParams());
//                    startActivity(CrowdFundingPreviewActivity.class);
//                }
//                break;
//            case R.id.tv_crowd_funding_project_msg_submit:
//                submit();
//                break;
//            case R.id.tv_edit_template:
//                startActivity(CrowdFundingUsedTemplateActivity.class);
//                break;
//        }
//    }
//
//    private List<CrowdFundingPreviewBean> templateData1;
//
//
//
//    private void submit() {
//
//        if (CheckBaseItemUtil.check(bivCourse, bivTeacher, bivType, bivAddress, bivOfficialPrice, bivOnePrice, bivDays, bivSponsorName)) {
//            LoadingDialog.showDialogForLoading(AppManager.getAppManager().currentActivity(), "正在上传资料", false);
//            Map<String, String> stringMap = setParams();
//            mPresenter.postData(ApiKey.CROWN_FUND_CREATE_CROWD_FUND, stringMap);
//        }
//    }
//
//    @NonNull
//    private Map<String, String> setParams() {
//        if (showEditTemplate) {
//            templateData1 = QJGDataUtil.getTemplateData();
//        } else {
//            templateData1 = createTemplateData();
//        }
//        List<CheckMsgBean> checkMsgBean = checkMsgAdapter.getAll();
//        Map<String, String> needed = new HashMap<>();
//        for (CheckMsgBean bean : checkMsgBean) {
//            if (bean.isCheck()) {
//                needed.put(bean.getKey(), "1");
//            }
//        }
//        Map<String, String> map = new HashMap<>();
//        if (_type==1){
//
//        }else if (_type==2){
//
//        }
//        map.put("courseId", String.valueOf(mCourseBean.getId()));
//        map.put("authorId", mTeacherBean.getId());
//        map.put("typeId", getTypeId());
//        map.put("startTime", "");
//        map.put("endTime", "");
//        map.put("address", bivAddress.getContent());
//        map.put("targetMembers", bivPeople.getContent());
//        onePrice = onePrice.replace("¥","");
//        map.put("avgPrice", onePrice);
//        map.put("days", bivDays.getContent());
//        map.put("name", bivSponsorName.getContent());
//        map.put("intro", bivSponsorProfile.getContent());
//        map.put("others", bivSponsorDemo.getContent());
//        if (checkboxService.isChecked()) {
//            map.put("servicePhone", etServicePhone.getText().toString());
//        }
//
//        map.put("details", JsonUtils.toJson(templateData1));
//        String othersNeeded = JsonUtils.toJson(needed);
//        String data = JsonUtils.toJson(map);
//
//        Map<String, String> stringMap = MapUtils.getDefMap(true);
//        stringMap.put("data", data);
//        stringMap.put("needed", othersNeeded);
//        return stringMap;
//    }
//
//    private boolean isPreviewTemplateData;
//
//    private List<CrowdFundingPreviewBean> createTemplateData() {
//        List<CrowdFundingPreviewBean> templateData1 = new ArrayList<>();
//        if (EmptyUtils.isNotEmpty(bivCourseProfile.getContent())) {
//            templateData1.add(new CrowdFundingPreviewBean(1, -2, "", "", bivCourseProfile.getContent(), ""));
//        }
//        if (EmptyUtils.isNotEmpty(mImageAdapter.getAll())) {
//            List<String> pics = mImageAdapter.getAll();
//            for (int i = 0; i < pics.size(); i++) {
//                templateData1.add(new CrowdFundingPreviewBean(6, 1 + i, "", "", "", pics.get(i)));
//            }
//        }
//        isPreviewTemplateData = true;
//        return templateData1;
//    }
//
//    private String getTypeId() {
//        return String.valueOf(typeList.indexOf(bivType.getContent()));
//    }
//
//    private String orderId;
//
//    @Override
//    public void showSubmitResult(OrderCreateResultBean.DataBean result) {
//        orderId = result.getOrderId();
//        Map<String, String> stringMap = MapUtils.getTagId("3");
//        stringMap.put("id", orderId);
//        mPresenter.updateAvatar(ApiKey.UPLOAD_IMGS_UPLOAD_IMGS, stringMap, FileUtil.str2File(avatar));
//    }
//
//    private LuBanUtil luBanUtil;
//
//    @Override
//    public void showUpdateAvatarResult(Result result) {
//
//        if (EmptyUtils.isNotEmpty(templateData1)) {
//            List<String> imgs = new ArrayList<>();
//            for (CrowdFundingPreviewBean previewBean : templateData1) {
//                if (previewBean.getType() == 6) {
//                    imgs.add(previewBean.getPicture());
//                }
//            }
//            uploadCount = 0;
//            totalCount = imgs.size();
//            if (totalCount == 0) {
//                startActivity(MyCrowdFundingOrderActivity.class, getBundle(MyCrowdFundingOrderActivity.FROM_TYPE_PARTAKE, orderId), true);
//                return;
//            }
//            luBanUtil = new LuBanUtil(getContext(), imgs, new MyCallBack() {
//                @Override
//                public void call() {
//                    Map<String, String> stringMap = MapUtils.getTagId("2");
//                    stringMap.put("id", orderId);
//                    for (File file : luBanUtil.getResult()) {
//                        mPresenter.updateDetailImg(ApiKey.UPLOAD_IMGS_UPLOAD_IMGS, stringMap, FileUtil.str2File(file.getAbsolutePath()));
//                    }
//                }
//            });
//            luBanUtil.startCompress();
//        } else startActivity(MyCrowdFundingOrderActivity.class, getBundle(MyCrowdFundingOrderActivity.FROM_TYPE_PARTAKE, orderId), true);
//    }
//
//    private int uploadCount;
//    private int totalCount;
//
//    @Override
//    public void showUpdateDetailImgResult(Result result) {
//        uploadCount++;
//        if (uploadCount == totalCount) {
//            stopProgressDialog();
//            mRxManager.post(AppConstant.RXTag.PREVIEW_CLOSE, true);
//            startActivity(MyCrowdFundingOrderActivity.class, getBundle(MyCrowdFundingOrderActivity.FROM_TYPE_PARTAKE, orderId), true);
//        }
//    }
//}
