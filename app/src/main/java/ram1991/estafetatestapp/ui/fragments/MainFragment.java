package ram1991.estafetatestapp.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ram1991.estafetatestapp.App;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.api.loaders.TaskLoader;
import ram1991.estafetatestapp.api.model.NetworkResponse;
import ram1991.estafetatestapp.api.model.Task;
import ram1991.estafetatestapp.ui.activities.BaseActivity;
import ram1991.estafetatestapp.ui.activities.MainActivity;
import ram1991.estafetatestapp.ui.activities.TaskPagerActivity;
import ram1991.estafetatestapp.ui.adapters.RecyclerTasksAdapter;

public class MainFragment extends BaseFragment implements RecyclerTasksAdapter.Callbacks,
        LoaderManager.LoaderCallbacks<NetworkResponse> {

    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;

    private List<Task> mTasks;
    private OnFragmentInteractionListener mListener;

    private RecyclerTasksAdapter mTaskAdapter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTasks = new ArrayList<>();
    }

    @Override
    protected void updateUi() {
        mTaskAdapter.setTasks(mTasks);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .setSwipeRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        startLoading();
                    }
                });
        setupAdapter();
        startLoading();
    }

    private void startLoading() {
        mListener.onProgressShow(BaseActivity.STATE_SHOW_PROGRESS);
        getActivity().getSupportLoaderManager().initLoader(R.id.task_loader, Bundle.EMPTY, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    private static String generateUrl() {
        return "http://amt2.estafeta.org/api/mobilesurveytasks/gettestsurveytasks/";
    }

    private static String generateLogin() {
        return "admin";
    }

    private static String generateCompanyId() {
        return "9F346DDB-8FF8-4F42-8221-6E03D6491756";
    }

    private static String generatePass() {
        return "1";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onTaskSelected(Task task) {
        Intent intent = TaskPagerActivity.getStartIntent(getActivity(), task);
        startActivity(intent);
    }

    @Override
    public Loader<NetworkResponse> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case R.id.task_loader:
                return new TaskLoader(
                        getActivity(),
                        generateUrl(),
                        generateLogin(),
                        generateCompanyId(),
                        generatePass());
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<NetworkResponse> loader, NetworkResponse data) {
        int id = loader.getId();
        if (id == R.id.task_loader) {
            getActivity().getSupportLoaderManager().destroyLoader(id);
            mListener.onProgressShow(BaseActivity.STATE_HIDE_PROGRESS);
            switch (data.getState()) {
                case App.CONNECTED:
                    mListener.onStateShow(BaseActivity.STATE_CONNECTED);
                    mTasks = data.getTypedAnswer();
                    break;
                case App.NO_NETWORK:
                    mListener.onStateShow(BaseActivity.STATE_NO_NETWORK);
                    break;
                case App.ERROR:
                    mListener.onStateShow(BaseActivity.STATE_ERROR);
                    break;
            }

        }
        updateUi();
    }

    @Override
    public void onLoaderReset(Loader<NetworkResponse> loader) {
    }

    private void setupAdapter() {
        mTaskAdapter = new RecyclerTasksAdapter(mTasks, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMain.setLayoutManager(linearLayoutManager);
        recyclerMain.setAdapter(mTaskAdapter);
    }
}
