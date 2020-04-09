package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/15 18:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/15$
 * @updateDes ${TODO}
 */

public class RecordChartBean extends Result {

    /**
     * data : {"chart":[{"total":0,"title":"晚上"},{"total":0,"title":"晚上"},{"total":0,"title":"晚上"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ChartBean> chart;

        public List<ChartBean> getChart() {
            return chart;
        }

        public void setChart(List<ChartBean> chart) {
            this.chart = chart;
        }

        public static class ChartBean {
            @Override
            public String toString() {
                return "ChartBean{" + "total=" + total + ", title='" + title + '\'' + ", text='" + text + '\'' + '}';
            }

            /**
             * total : 0
             * title : 晚上
             */

            private int total;
            private String title;
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
