package ram1991.estafetatestapp.ui.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import butterknife.BindView;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.ui.fragments.MainFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.swipe_main)
    SwipeRefreshLayout swipeMain;

    @BindView(R.id.coordinator_main)
    CoordinatorLayout coordinator;

    @Override
    protected void createFragment() {
        Fragment fragment = MainFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.fragment_container_main, fragment).commit();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents() {
    }

    @Override
    public void onStateShow(int state) {
        switch (state) {
            case BaseActivity.STATE_NO_NETWORK:
                Snackbar.make(coordinator, R.string.no_internet, Snackbar.LENGTH_LONG).show();
                break;
            case BaseActivity.STATE_CONNECTED:
                Snackbar.make(coordinator, R.string.success, Snackbar.LENGTH_LONG).show();
                break;
            case BaseActivity.STATE_ERROR:
                Snackbar.make(coordinator, R.string.error, Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onProgressShow(int state) {
        switch (state) {
            case BaseActivity.STATE_SHOW_PROGRESS:
                swipeMain.post(new Runnable() {
                    @Override
                    public void run() {
                        swipeMain.setRefreshing(true);
                    }
                });
                break;
            case BaseActivity.STATE_HIDE_PROGRESS:
                swipeMain.post(new Runnable() {
                    @Override
                    public void run() {
                        swipeMain.setRefreshing(false);
                    }
                });
                break;
        }
    }

    public void setSwipeRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        swipeMain.setOnRefreshListener(listener);
    }
}
