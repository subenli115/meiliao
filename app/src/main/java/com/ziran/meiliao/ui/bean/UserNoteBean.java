package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/15 16:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/15$
 * @updateDes ${TODO}
 */

public class UserNoteBean extends Result {


    /**
     * imgPrefix : http://www.dgli.net:8888/upload/images/note/
     * data : [{"createTime":"08-24 15:30","pics":",","quesList":[{"content":"124124","quesId":2,"question":"什么事让您心怀感谢？"}],"noteId":20},
     * {"createTime":"08-24 15:19","pics":",","quesList":[{"content":"124124","quesId":2,"question":"什么事让您心怀感谢？"}],"noteId":19},
     * {"createTime":"08-24 15:08","pics":"","quesList":[{"content":"14124","quesId":2,"question":"什么事让您心怀感谢？"}],"noteId":18}]
     */

    private String imgPrefix;
    private List<DataBean> data;

    public String getImgPrefix() {
        return imgPrefix;
    }

    public void setImgPrefix(String imgPrefix) {
        this.imgPrefix = imgPrefix;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        /**
         * createTime : 08-24 15:30
         * pics : ,
         * quesList : [{"content":"124124","quesId":2,"question":"什么事让您心怀感谢？"}]
         * noteId : 20
         */
        private int position;
        private String createTime;
        private List<String> pics;
        private int noteId;
        private List<QuesListBean> quesList;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public  List<String> getPics() {
            return pics;
        }

        public void setPics( List<String> pics) {
            this.pics = pics;
        }

        public int getNoteId() {
            return noteId;
        }

        public void setNoteId(int noteId) {
            this.noteId = noteId;
        }

        public List<QuesListBean> getQuesList() {
            return quesList;
        }

        public void setQuesList(List<QuesListBean> quesList) {
            this.quesList = quesList;
        }

        public static class QuesListBean implements Parcelable {

            /**
             * content : 124124
             * quesId : 2
             * question : 什么事让您心怀感谢？
             */

            private String content;
            private int quesId;
            private String question;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getQuesId() {
                return quesId;
            }

            public void setQuesId(int quesId) {
                this.quesId = quesId;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.content);
                dest.writeInt(this.quesId);
                dest.writeString(this.question);
            }

            public QuesListBean() {
            }

            protected QuesListBean(Parcel in) {
                this.content = in.readString();
                this.quesId = in.readInt();
                this.question = in.readString();
            }

            public static final Creator<QuesListBean> CREATOR = new Creator<QuesListBean>() {
                @Override
                public QuesListBean createFromParcel(Parcel source) {
                    return new QuesListBean(source);
                }

                @Override
                public QuesListBean[] newArray(int size) {
                    return new QuesListBean[size];
                }
            };
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.position);
            dest.writeString(this.createTime);
            dest.writeStringList(this.pics);
            dest.writeInt(this.noteId);
            dest.writeTypedList(this.quesList);
        }

        protected DataBean(Parcel in) {
            this.position = in.readInt();
            this.createTime = in.readString();
            this.pics = in.createStringArrayList();
            this.noteId = in.readInt();
            this.quesList = in.createTypedArrayList(QuesListBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
