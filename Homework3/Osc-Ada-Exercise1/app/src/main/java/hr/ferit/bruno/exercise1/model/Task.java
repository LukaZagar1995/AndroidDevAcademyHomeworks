package hr.ferit.bruno.exercise1.model;

import android.support.annotation.NonNull;

public class Task implements Comparable{
	private int mImportance;
	private String mTitle;
	private String mSummary;

	public Task(int importance, String title, String summary) {
		mImportance = importance;
		mTitle = title;
		mSummary = summary;
	}

	public int getImportance() {
		return mImportance;
	}

	public void setImportance(int importance) {
		mImportance = importance;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getSummary() {
		return mSummary;
	}

	public void setSummary(String summary) {
		mSummary = summary;
	}

	@Override
	public String toString() {
		return mTitle.toUpperCase() + '\n' + mSummary + '\n';
	}

	@Override
	public int compareTo(@NonNull Object o) {
		return  Integer.compare(this.getImportance(), ((Task) o).getImportance());
	}
}
