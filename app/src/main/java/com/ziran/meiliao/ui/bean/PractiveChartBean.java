package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/14.
 */

public class PractiveChartBean extends Result{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * chartData : [{"y_axis":"20","x_axis":"1"},{"y_axis":"10","x_axis":"2"},{"y_axis":"25","x_axis":"3"},{"y_axis":"2","x_axis":"4"},{"y_axis":"0","x_axis":"5"},{"y_axis":"50","x_axis":"6"},{"y_axis":"50","x_axis":"7"},{"y_axis":"60","x_axis":"8"},{"y_axis":"0","x_axis":"9"},{"y_axis":"80","x_axis":"10"},{"y_axis":"50","x_axis":"11"},{"y_axis":"3","x_axis":"12"},{"y_axis":"20","x_axis":"13"},{"y_axis":"10","x_axis":"14"}]
         * Y : 分钟
         * X : 日期
         */

        private String Y;
        private String X;
        private List<ChartDataBean> chartData;

        public String getY() {
            return Y;
        }

        public void setY(String Y) {
            this.Y = Y;
        }

        public String getX() {
            return X;
        }

        public void setX(String X) {
            this.X = X;
        }

        public List<ChartDataBean> getChartData() {
            return chartData;
        }

        public void setChartData(List<ChartDataBean> chartData) {
            this.chartData = chartData;
        }

        public static class ChartDataBean {
            /**
             * y_axis : 20
             * x_axis : 1
             */

            private String y_axis;
            private String x_axis;

            public String getY_axis() {
                return y_axis;
            }

            public void setY_axis(String y_axis) {
                this.y_axis = y_axis;
            }

            public String getX_axis() {
                return x_axis;
            }

            public void setX_axis(String x_axis) {
                this.x_axis = x_axis;
            }
        }
    }
}
