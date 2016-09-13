package com.example.loopviewpager;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	
	private ViewPager mViewPager;
	private int[] mImages = {R.drawable.icon_1, R.drawable.icon_2, 
			R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(mPagerAdapter);
        
        mViewPager.setCurrentItem(1);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
    }
    
    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			//当页面停稳，做一次跳转
			if (arg0 == ViewPager.SCROLL_STATE_IDLE) {
				int position = mViewPager.getCurrentItem();
				if (position == 0 || position == mViewPager.getAdapter().getCount() - 1) {
					int realPostion = getRealPosition(position);
//					mViewPager.setCurrentItem(realPostion+1);
					mViewPager.setCurrentItem(realPostion+1, false);
				}
			}
 		}
	};
    
    private PagerAdapter mPagerAdapter = new PagerAdapter() {

		@Override
		public int getCount() {
			return mImages.length + 2;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		public Object instantiateItem(android.view.ViewGroup container, int position) {
			int newPosition = getRealPosition(position);
			
			ImageView page = new ImageView(MainActivity.this);
			page.setImageResource(mImages[newPosition]);
			page.setScaleType(ScaleType.FIT_XY);
			container.addView(page);
			return page;
		}


		
		public void destroyItem(android.view.ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		};
    	
    };
    
	private int getRealPosition(int position) {
		//0 --> 4
		//1 --> 0
		//2 --> 1
		//3 -- > 2
		//4-->3
		//5--> 4
		//6--> 0
		int newPosition = (position - 1) % mImages.length;
		if (newPosition < 0) {
			newPosition = newPosition + mImages.length;
		}
		return newPosition;
	};


    
}
