package com.ziran.meiliao.ui.settings.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.settings.adapter.GetGoalAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 加入VIP 的Fragment
 * Created by Administrator on 2017/1/16.
 */

public class GetGoalFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result> {

    @Override
    public void returnData(Result result) {


    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        return new GetGoalAdapter(getContext(), R.layout.item_get_goal);
    }

    @Override
    protected void loadData() {
        updateData(getTestData());
    }


    public List<Item> getTestData() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "邀请好友", 30, true));
        items.add(new Item(2, "每日正念练习打卡", 30, true));
        items.add(new Item(3, "每日签到", 30, false));
        items.add(new Item(4, "新用户注册", 30, true));
        items.add(new Item(5, "首次使用付费", 30, false));
        items.add(new Item(6, "设置头像", 30, true));
        return items;
    }

    public static class Item {
        private int id;
        private String title;
        private int amount;
        private boolean received;

        public Item() {
        }

        public Item(int id, String title, int amount, boolean received) {
            this.id = id;
            this.title = title;
            this.amount = amount;
            this.received = received;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public boolean isReceived() {
            return received;
        }

        public void setReceived(boolean received) {
            this.received = received;
        }

        @Override
        public String toString() {
            return "Item{" + "id=" + id + ", title='" + title + '\'' + ", amount=" + amount + ", received=" + received + '}';
        }
    }
}
