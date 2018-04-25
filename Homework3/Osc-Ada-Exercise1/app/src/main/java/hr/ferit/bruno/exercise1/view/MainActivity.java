package hr.ferit.bruno.exercise1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hr.ferit.bruno.exercise1.R;
import hr.ferit.bruno.exercise1.TasksRepository;
import hr.ferit.bruno.exercise1.model.Task;

public class MainActivity extends AppCompatActivity {

	TasksRepository mRepository;

	Button mSave;
	EditText mTitle, mSummary, mImportance;
	TextView mTask;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeUI();

		mRepository = TasksRepository.getInstance();
	}

	private void initializeUI() {
		mSave = findViewById(R.id.button_newtask_save);
		mTitle = findViewById(R.id.edittext_newtask_title);
		mSummary = findViewById(R.id.edittext_newtask_summary);
		mImportance = findViewById(R.id.edittext_newtask_importance);
		mTask = findViewById(R.id.textview_newtask_display);
		mSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveTask(v);
			}
		});

	}

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
		// one below the other and call it on each new task.

		if(mRepository.getTasks().size() == 1)
			mTask.setText(mRepository.getTasks().get(mRepository.getTasks().size()-1).toString());
		else
		mTask.setText(mTask.getText() + "\n" + mRepository.getTasks().get(mRepository.getTasks().size()-1).toString());

	}
}
