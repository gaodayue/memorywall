package com.gaodayue.memorywall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PhotoFragment extends Fragment {
	
	public static final String ARG_IMG = "img";
	private int m_imgres;
	
	public static PhotoFragment create(int imageResId) {
		PhotoFragment frag = new PhotoFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(ARG_IMG, imageResId);
		frag.setArguments(bundle);
		return frag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m_imgres = getArguments().getInt(ARG_IMG);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ImageView view = (ImageView) inflater.inflate(
				R.layout.photo_fragment, container, /*attachToRoot=*/false);
		view.setImageBitmap(
				GalleryActivity.decodeSampledBitmapFromResource(getResources(), m_imgres, 697, 460));
		
		return view;
	}
}
