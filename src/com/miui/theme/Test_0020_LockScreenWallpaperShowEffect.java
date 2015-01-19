package com.miui.theme;
//hu

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0020_LockScreenWallpaperShowEffect extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0139_LockScreenWallpaperShowEffect() throws UiObjectNotFoundException{
		
		mm.log("Step 1 : Open Unlock");

		int DisplayWidth ;
		DisplayWidth = this.getUiDevice().getDisplayWidth() ;
		if(!mm.isScreenOn()){   
			mm.wakeUp();
			mm.waitFor(1);
			if(DisplayWidth <= 480){
				mm.move(300, 700, 300, 200);
			}
			if(DisplayWidth <=540){
				mm.move(300, 900, 300, 200);
			}
			if(DisplayWidth <=720){
				mm.move(300, 1000, 300, 500);
			}
			if(DisplayWidth <=1080){
				mm.move(500, 1700, 500, 500);
			}else{
				mm.move(700, 2000, 700, 500);
			}
		//	mm.move(500, 1500, 500, 700);
			mm.waitFor(2);
		}	
		
		mm.log("Step 2 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();

		mm.waitFor(2);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(2);
		
		mm.log("Step 4 : Slip LockScreenWallpaper.");
		UiObject scroll = new UiObject(new UiSelector());
		
		UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)) ;
		View.click() ;
		
//		mm.click(197, 420);
		mm.waitFor(2);
		mm.saveScreenshot("scroll.png");
		scroll.swipeLeft(2);
		mm.waitFor(2);
		mm.saveScreenshot("scroll1.png");
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		mm.waitFor(2);

		scroll.swipeRight(2);
		mm.waitFor(2);
		scroll.swipeRight(2);
		mm.waitFor(2);
		scroll.swipeRight(2);
		
		
		
		/*mm.click(540, 960);
		
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		mm.waitFor(2);
		scroll.swipeRight(2);	
		mm.waitFor(2);
		scroll.swipeRight(2);	
		mm.waitFor(2);
		scroll.swipeRight(2);	
		mm.waitFor(2);*/
		
		mm.waitFor(1);
		mm.pressBack(4);
		mm.pressHome();
		
	}
	
	private boolean CheckSelectedByText(String textName, String classNmae) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, classNmae).isSelected() == true){
			checkSelected = true;
		}
		return checkSelected;
	}
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
