package definitely.exammt.ui.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import definitely.exammt.db.RaidEntity;
import definitely.exammt.fragments.RaidsFragment;
import definitely.exammt.fragments.AttendingRaidsFragment;
import definitely.exammt.ui.interfaces.OnFragmentIteractionListener;

public class TabsPagerAdapter extends FragmentStatePagerAdapter implements OnFragmentIteractionListener {
    private Context context;
    private RaidsFragment raidsFragment = new RaidsFragment();
    private AttendingRaidsFragment attendingRaidsFragment = new AttendingRaidsFragment();

    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return raidsFragment;
            case 1:
                return attendingRaidsFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Available Raids";
            case 1:
                return "Attending Raids";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void onDelete(RaidEntity raidEntity) {
        attendingRaidsFragment.onDelete(raidEntity);
    }
}
