package com.ziran.meiliao.utils;

import android.text.Html;
import android.text.Spanned;

import java.util.Locale;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 16:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */

public class HtmlUtil {

    /**
     * @param content
     * @return
     */
    public static Spanned parseRechargeBalance(String content) {
        String color = "#333333";
        String html = "<span>当前金币数量：<span><font color=" + color + ">" + content + "</span></span>";
        return Html.fromHtml(html);
    }


    public static Spanned getCouponEmptyMsg() {
        String ff = " 您还没有优惠券哦！<br> 赶快点击“" + "<font color='#EF8C57' size='30'>" + "我要优惠券" + "</font>" + "”领取吧！";
        return Html.fromHtml(ff);
//        return fromHtml(format("您还没有优惠券哦！<br> 赶快点击“<font color='#EF8C57' size='30'>我要优惠券</font>”领取吧！",left,content,right));
    }

    /**
     * @param content
     * @return
     */
    public static Spanned progressTips(String left, String content, String right) {
//        String color = "#151515";
//        String html = "<span>" + left + "：<span><font color=" + color + ">" + content + "</span>" + right + "</span>";

        return fromHtml(format("<span>%s：<span><font color=#151515>%s</span>%s</span>", left, content, right));
//        return Html.fromHtml(html);
    }

    /**
     * 合计：9999元
     *
     * @param price
     * @return
     */
    public static Spanned buyPrice(int price) {
        String html = "合计<font color='#FFA008' >" + price + "</font><font size='120' color='#FFA008' >元</font>";
        return Html.fromHtml(html);
    }

    public static Spanned getTeamString(String tag, String text) {
        return fromHtml(getTeamString1(tag, text));
    }

    public static String getTeamString1(String tag, String text) {
        String color = "#333333";
        return format("<span>%s: <span><font color=%s>%s</span></span>", tag, color, text);
    }

    public static Spanned crowdFundingContent(String tag, int price) {
        String color = "#FFA008";
        return fromHtml(format("<span>%s: <span><font color=%s>¥%d</span></span>", tag, color, price));
    }

    public static String format(String content, Object... args) {
        return String.format(Locale.getDefault(), content, args);
    }

    public static String formatEm(String content) {
        return String.format(Locale.getDefault(), "\u3000\u3000%s", content);
    }

    public static Spanned fromHtml(String str) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(str);
        }
    }
}
