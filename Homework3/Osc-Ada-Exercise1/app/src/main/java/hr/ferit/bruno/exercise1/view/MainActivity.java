package hr.ferit.bruno.exercise1.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.bruno.exercise1.R;
import hr.ferit.bruno.exercise1.TasksRepository;
import hr.ferit.bruno.exercise1.model.Task;

public class MainActivity extends AppCompatActivity {

	TasksRepository mRepository;

	@BindView(R.id.button_newtask_save) Button mSave;
	@BindView(R.id.edittext_newtask_title) EditText mTitle;
	@BindView(R.id.edittext_newtask_summary) EditText mSummary;
	@BindView(R.id.edittext_newtask_importance) EditText mImportance;
	@BindView(R.id.textview_newtask_display) TextView mDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		mRepository = TasksRepository.getInstance();
	}

	@OnClick(R.id.button_newtask_save)
	public void saveTask(View view){

		// ToDo: 	store the task
		// Parse data from the widgets and store it in a task
		// Store the task in the fake database using the repository

		mRepository.save(new Task(Integer.parseInt(mImportance.getText().toString()), mTitle.getText().toString(), mSummary.getText().toString()));

		// ToDo:	clear the UI for the new task
		// Clear all of the editText controls

		mImportance.setText("");
		mTitle.setText("");
		mSummary.setText("");

		// ToDo: 	define a method
		// Create a method for displaying the tasks in the textview as strings
		// one below the other and call it on each new task
		showTasks(mRepository.getTasks());

	}

	private void showTasks(List<Task> tasks){

		Collections.sort(tasks);
		mDisplay.setText("");
		mDisplay.setGravity(Gravity.LEFT);
		for(Task task : tasks)
		{
			mDisplay.setText(mDisplay.getText() + task.toString() + "\n");
		}

	}

}
