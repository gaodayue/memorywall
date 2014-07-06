package com.gaodayue.memorywall;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class TagsAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mTags;
	private LayoutInflater mInflater;

	public TagsAdapter(Context context, List<String> tags) {
		mContext = context;
		mTags = tags;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mTags.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (position == mTags.size())
			return -1; // the last one is an add button
		else
			return 0; // other tag buttons
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Button btn = (Button) mInflater.inflate(R.layout.tags_item, parent, false);
		if (position < mTags.size()) {
			btn.setText(mTags.get(position));
		} else {
			btn.setText("+");
		}
		return btn;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}
	
	@Override
	public boolean isEnabled(int position) {
		return true;
	}
}
