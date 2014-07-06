package com.gaodayue.memorywall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends FragmentActivity {
	private static final String TAG ="MainActivity";
	
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	
	private GridView mTagsGrid;
	private TagsAdapter mTagsAdapter;
	private List<String> mTags;
	
//	private AlertDialog mDialog;
//	private View mPromptView;
//	private String mInputResult;
	
	private int NUM_IMG = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mPager = (ViewPager) this.findViewById(R.id.viewpager);
        mPagerAdapter = new PhotoPagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        
        mTags = new ArrayList<String>(
        		Arrays.asList("二十年前", "老厂长", "下乡插队", "战友", "火车站"));
        
        mTagsGrid = (GridView) findViewById(R.id.main_tags_gridview);
        mTagsAdapter = new TagsAdapter(this, mTags);
        mTagsGrid.setAdapter(mTagsAdapter);
        
        mTagsGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Log.v(TAG, "get id: " + id + ", position:" + position);
            	if (id == -1) { // add tag
                    final View promptView = LayoutInflater.from(MainActivity.this).inflate(R.layout.prompts, null);
            		new AlertDialog.Builder(MainActivity.this)
            			.setView(promptView)
            			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int id) {
    							EditText input = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
    							mTags.add(input.getText().toString());
    							mTagsAdapter.notifyDataSetChanged();
    						}
            			})
            			.setNegativeButton("取消",
    					  new DialogInterface.OnClickListener() {
    					    public void onClick(DialogInterface dialog,int id) {
    					    	dialog.cancel();
    					    }
    					  })
            			.show();
            	} else { // choose tag, switch color
            		int normalColor  = getResources().getColor(R.color.tag_text_normal);
            		int pressedColor = getResources().getColor(R.color.tag_text_pressed);
            		Button btn = (Button) v;
            		if (btn.getTextColors().getDefaultColor() == normalColor) {
            			Log.v(TAG, "try to change color");
            			btn.setTextColor(pressedColor);
            		} else {
            			btn.setTextColor(normalColor);
            		}
            	}
            }
        });
    }
    
    private class PhotoPagerAdapter extends FragmentStatePagerAdapter {
    	
    	private int[] mImages;
    	
        public PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
            mImages = new int[NUM_IMG];
            mImages[0] = R.drawable.img1;
            mImages[1] = R.drawable.img2;
            mImages[2] = R.drawable.img3;
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoFragment.create(mImages[position]);
        }

        @Override
        public int getCount() {
            return NUM_IMG;
        }
    }
    
    public void onClickScan(View view) {
    	Intent intent = new Intent();
    	intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
    	startActivity(intent);
    }
    
    public void onClickGallery(View view) {
    	Intent intent = new Intent(this, GalleryActivity.class);
    	startActivity(intent);
    }
    
    public void onClickListen(View view) {
    	View titlebar = findViewById(R.id.photo_titlebar);
    	View playbar  = findViewById(R.id.photo_playbar);
    	
    	if (titlebar.getVisibility() == View.VISIBLE) {
    		titlebar.setVisibility(View.INVISIBLE);
    		playbar.setVisibility(View.VISIBLE);
    		
    	} else {
    		titlebar.setVisibility(View.VISIBLE);
    		playbar.setVisibility(View.INVISIBLE);
    	}
    }
}
