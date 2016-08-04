package ram1991.estafetatestapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.api.model.Task;
import ram1991.estafetatestapp.ui.activities.TaskPagerActivity;
import ram1991.estafetatestapp.ui.adapters.ViewPagerAdapter;

public class PagerFragment extends BaseFragment {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private Task mTask;
    private FragmentManager mChildFragmentManager;

    public static PagerFragment newInstance(Task task) {
        PagerFragment fragment = new PagerFragment();
        if (task != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(TaskPagerActivity.BUNDLE_TASK, task);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    public PagerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mTask = (Task) getArguments().getSerializable(TaskPagerActivity.BUNDLE_TASK);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
        setupViewPager(viewPager);
        setupTabsInPager();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(mChildFragmentManager);
        adapter.addFragment(InformationFragment.newInstance(), InformationFragment.TAB_NAME);
        adapter.addFragment(SecondFragment.newInstance(), SecondFragment.TAB_NAME);
        viewPager.setAdapter(adapter);
    }


    private void setupTabsInPager() {
        ((TaskPagerActivity) getActivity()).setupTabsInPager(viewPager);
    }

    public Task getCurrentTask() {
        if (mTask != null) {
            return mTask;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    protected void updateUi() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }
}
