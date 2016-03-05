package de.czyrux.mvploadersample.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.czyrux.mvploadersample.ColorPicker;
import de.czyrux.mvploadersample.R;
import de.czyrux.mvploadersample.single.SimpleFragment;

public class PagerFragment extends Fragment {
    private static final String ARG_PAGES = "pages";

    private int pages;
    private ViewPager viewPager;

    public static PagerFragment newInstance(int pages) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGES, pages);

        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pages = getArguments().getInt(ARG_PAGES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), pages));
        viewPager.setOffscreenPageLimit(Math.max(4, pages / 2));
    }


    private static class PagerAdapter extends FragmentStatePagerAdapter {

        private final int numberOfPages;

        public PagerAdapter(FragmentManager fm, int numberOfPages) {
            super(fm);
            this.numberOfPages = numberOfPages;
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleFragment.newInstance("#-" + position, ColorPicker.getRandomColor());
        }

        @Override
        public int getCount() {
            return numberOfPages;
        }
    }
}
