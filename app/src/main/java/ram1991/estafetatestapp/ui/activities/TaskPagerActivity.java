package ram1991.estafetatestapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.api.model.Task;
import ram1991.estafetatestapp.ui.fragments.PagerFragment;

public class TaskPagerActivity extends BaseActivity {

    public static final String BUNDLE_TASK = "task";

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private Task mTask;

    public static Intent getStartIntent(Context context, Task task) {
        Intent intent = new Intent(context, TaskPagerActivity.class);
        if (task != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BUNDLE_TASK, task);
            intent.putExtras(bundle);
        }
        return intent;
    }

    @Override
    protected void createFragment() {
        Fragment fragment = PagerFragment.newInstance(mTask);
        fragmentManager.beginTransaction().add(R.id.fragment_container_pager, fragment).commit();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pager;
    }

    @Override
    protected void initComponents() {
        mTask = (Task) getIntent().getSerializableExtra(BUNDLE_TASK);
    }

    @Override
    public void onStateShow(int state) {
    }

    @Override
    public void onProgressShow(int state) {

    }

    public void setupTabsInPager(ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }
}
