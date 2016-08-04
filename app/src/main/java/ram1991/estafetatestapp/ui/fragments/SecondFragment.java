package ram1991.estafetatestapp.ui.fragments;

import ram1991.estafetatestapp.R;

public class SecondFragment extends BaseFragment {
    public static final String TAB_NAME = "OTHER";

    public SecondFragment() {
    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    protected void updateUi() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }
}
