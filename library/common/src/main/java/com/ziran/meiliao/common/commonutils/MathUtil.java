package com.ziran.meiliao.common.commonutils;

import android.graphics.PointF;

/**
 * author admin
 * create  2017/4/1 11
 * des     ${TODO}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/4/1 11
 * updateDes    ${TODO}
 */

public class MathUtil {

    /**
     * 获取两个点之间的直线距离
     * 使用数学的勾股定理
     *
     * @param onePointF 第一个点
     * @param twoPointF 第二个点
     * @return 两点之间的距离
     */
    public static double getTwoPointFDistance(PointF onePointF, PointF twoPointF) {

        return Math.sqrt(Math.pow(onePointF.y - twoPointF.y, 2) + Math.pow(onePointF.x - twoPointF.x, 2));
    }

    /**
     * 获取两个点之间的直线距离
     *
     * @param onePointF
     * @param twoPointF
     * @param radius
     * @return
     */
    public static float getCurveOffsetX(PointF onePointF, PointF twoPointF, float radius) {
        //cos 返回角的三角正悬
        return (float) (radius * Math.sin(Math.atan((onePointF.y - twoPointF.y) / (onePointF.x - twoPointF.x))));
    }


    public static float getCurveOffsetY(PointF onePointF, PointF twoPointF, float radius) {
        //cos 返回角的三角余弦
        return (float) (radius * Math.cos(Math.atan((onePointF.y - twoPointF.y) / (onePointF.x - twoPointF.x))));
    }

    //
    public static PointF getContorlPointF(PointF contorlPointF, PointF onePointF, PointF twoPointF) {
        if (contorlPointF == null) {
            contorlPointF = new PointF();
        }
        float x = (float) ((onePointF.x + twoPointF.x) * 0.618);
        float y = (float) ((onePointF.y + twoPointF.y) * 0.618);
        contorlPointF.set(x, y);
        return contorlPointF;
    }

}
