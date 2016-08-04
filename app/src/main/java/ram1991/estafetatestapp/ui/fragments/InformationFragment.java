package ram1991.estafetatestapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.api.model.Task;

public class InformationFragment extends BaseFragment {
    public static final String TAB_NAME = "INFORMATION";

    @BindView(R.id.text_planned_start)
    TextView plannedStartDate;
    @BindView(R.id.text_planned_end)
    TextView plannedEndDate;
    @BindView(R.id.text_vin)
    TextView vin;
    @BindView(R.id.text_brand)
    TextView brand;
    @BindView(R.id.text_model)
    TextView model;
    @BindView(R.id.text_model_code)
    TextView modelCode;
    @BindView(R.id.text_driver)
    TextView driver;

    private Task mTask;

    public InformationFragment() {
    }

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTask = ((PagerFragment) getParentFragment()).getCurrentTask();
        fillContent(mTask);
    }

    @Override
    protected void updateUi() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    private void fillContent(Task task) {
        plannedStartDate.setText(task.getActualStartDate());
        plannedEndDate.setText(task.getActualEndDate());
        vin.setText(task.getVin());
        brand.setText(task.getBrand());
        model.setText(task.getModel());
        modelCode.setText(task.getModelCode());
        driver.setText(task.getDriver());
    }
}
