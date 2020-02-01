package bluemwall.mhussain96.com.bluemwall.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bluemwall.mhussain96.com.bluemwall.Fragment.CategoryFragment;
import bluemwall.mhussain96.com.bluemwall.Fragment.RecentsFragment;
import bluemwall.mhussain96.com.bluemwall.Fragment.TrendingFragment;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return CategoryFragment.getInstance();
        }
        else if (position == 1){
            return TrendingFragment.getInstance();
        }
        else if (position == 2){
            return RecentsFragment.getInstance(context);
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Category";

            case 1:
                return "Popular";

            case 2:
                return "Recents";
        }
        return "";
    }
}
