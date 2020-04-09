package com.citypicker.citylist.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.citypicker.R;
import com.citypicker.citylist.sortlistview.CharacterParser;
import com.citypicker.citylist.sortlistview.SortModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/18 11:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/18$
 * @updateDes ${TODO}
 */

public class CityDataDb {
    private List<SortModel> SortModels;
    private static CityDataDb sCityDataDb;

    public static CityDataDb get() {
        if (sCityDataDb == null) {
            synchronized (CityDataDb.class) {
                if (sCityDataDb == null) {
                    sCityDataDb = new CityDataDb();
                }
            }
        }
        return sCityDataDb;
    }

    public List<SortModel> getData(Context context) {
        if (SortModels == null) {
            SortModels = new ArrayList<>();
            String[] cityArrays = context.getResources().getStringArray(R.array.cities_data);
            if (cityArrays.length == 0) return null;
            for (int i = 0; i < cityArrays.length; i++) {
                String[] temp = cityArrays[i].split(",");
                if (temp.length >2) {
                    SortModel SortModel = new SortModel();
                    SortModel.setCodeNumber(temp[1]);
                    SortModel.setName(temp[0]);
                    SortModel.setCode(temp[2]);
                    //汉字转换成拼音
                    String pinyin = CharacterParser.getInstance().getSelling(temp[0]);
                    String sortString = pinyin.substring(0, 1).toUpperCase();

                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        SortModel.setSortLetters(sortString.toUpperCase());
                    } else {
                        SortModel.setSortLetters("#");
                    }
                    SortModels.add(SortModel);
                }
            }
        }
        return SortModels;
    }

    public static SortModel getCountryZipCode(Context context){
        return getCountryZipCode(context,get().getData(context));
    }
    public static SortModel getCountryZipCode(Context context, List<SortModel> items) {
        String countryID = "";
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //getNetworkCountryIso
            countryID = manager.getSimCountryIso().toUpperCase();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getCode().trim().equals(countryID.trim())) {
                    return items.get(i);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return SortModel.getDefault();
        }
        return SortModel.getDefault();
    }

}
