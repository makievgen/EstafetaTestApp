package ram1991.estafetatestapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.api.model.Task;

public class RecyclerTasksAdapter extends RecyclerView.Adapter<RecyclerTasksAdapter.TasksHolder> {

    private List<Task> mTasks;
    private Callbacks mCallbacks;

    public RecyclerTasksAdapter(List<Task> tasks, Callbacks callbacks) {
        this.mTasks = tasks;
        this.mCallbacks = callbacks;
    }

    @Override
    public TasksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view_task, parent, false);
        return new TasksHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.bindOrder(task);
    }

    public void setTasks(List<Task> tasks) {
        this.mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class TasksHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_vin)
        TextView vin;
        @BindView(R.id.text_end_date)
        TextView end_date;
        @BindView(R.id.text_brand)
        TextView brand;
        @BindView(R.id.text_model)
        TextView model;
        @BindView(R.id.text_driver)
        TextView driver;
        @BindView(R.id.text_model_code)
        TextView modelCode;

        private Task mTask;

        public TasksHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setClickable(true);
        }

        public void bindOrder(Task task) {
            mTask = task;
            vin.setText(mTask.getVin());
            end_date.setText(mTask.getActualEndDate());
            brand.setText(mTask.getBrand());
            model.setText(mTask.getModel());
            driver.setText(mTask.getDriver());
            modelCode.setText(mTask.getModelCode());
        }

        @OnClick(R.id.grid_task)
        public void onClick() {
            mCallbacks.onTaskSelected(mTask);
        }
    }

    public interface Callbacks {
        void onTaskSelected(Task task);
    }
}

