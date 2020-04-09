package com.ziran.meiliao.ui.settings.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/4 10:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/4$
 * @updateDes ${TODO}
 */

public class CollectJDXFragment extends BaseFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    private CollectMusicFragment musicFragment;
    private CollectAlbumFragment albumFragment;

    @Override
    protected void initView() {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragmentByTag1 = manager.findFragmentByTag(CollectMusicFragment.class.getSimpleName());
        Fragment fragmentByTag2 = manager.findFragmentByTag(CollectAlbumFragment.class.getSimpleName());
        if (fragmentByTag1 != null) {
            musicFragment = (CollectMusicFragment) fragmentByTag1;
        } else {
            musicFragment = new CollectMusicFragment();
        }
        if (fragmentByTag2 != null) {
            albumFragment = (CollectAlbumFragment) fragmentByTag2;
        } else {
            albumFragment = new CollectAlbumFragment();
        }

        transaction.add(R.id.frameLayout, musicFragment, CollectMusicFragment.class.getSimpleName());
        transaction.add(R.id.frameLayout, albumFragment, CollectAlbumFragment.class.getSimpleName());
        transaction.show(musicFragment).hide(albumFragment).commit();
        showMusicFragment = true;
    }

    private boolean showMusicFragment;

    public void switchFragment(ImageView iv) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showMusicFragment = !showMusicFragment;
        if (showMusicFragment) {
            transaction.show(musicFragment).hide(albumFragment).commit();
        }else{
            transaction.hide(musicFragment).show(albumFragment).commit();
        }
        iv.setImageResource(showMusicFragment?R.mipmap.collect_ic_music:R.mipmap.collect_ic_musicdoc);
    }

    public DeleteRefreshFragment getCurrFragment() {
        return showMusicFragment?musicFragment:albumFragment;
    }
}
