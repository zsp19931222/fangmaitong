package com.goldze.base.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class BaseTabAdapter extends FragmentStatePagerAdapter {

    private List<?> mFragment;
    private List<String> mTitleList;
    private FragmentManager fm;


    public BaseTabAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.mFragment = mFragment;
    }


    public BaseTabAdapter(FragmentManager fm, List<?> mFragment) {
        super(fm);
        this.fm = fm;
        this.mFragment = mFragment;
    }

    /**
     * 接收首页传递的标题
     */
    public BaseTabAdapter(FragmentManager fm, List<?> mFragment, List<String> mTitleList) {
        super(fm);
        this.fm = fm;
        this.mFragment = mFragment;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;


    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }


    public void addFragmentList(List<?> fragment) {
        this.mFragment.clear();
        this.mFragment = null;
        this.mFragment = fragment;
        notifyDataSetChanged();
    }

    public void cleanFragments() {
        if (this.mFragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Object o : this.mFragment) {
                ft.remove((Fragment) o);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
    }
}
