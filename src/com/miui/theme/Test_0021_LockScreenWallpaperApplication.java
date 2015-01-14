package com.miui.theme;
//hu

import android.os.RemoteException;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0021_LockScreenWallpaperApplication extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0140_LockScreenWallpaperApplication() throws UiObjectNotFoundException{
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
//		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();
		mm.waitFor(2);
		
		mm.log("Step 4 : Apply LockScreenWallpaper");
		mm.clickOnImage(0);
//		mm.click(60, 127);
		mm.waitFor(2);
	/*	mm.click(197, 491);
		mm.waitFor(1);
		mm.move(829, 890, 273, 890);
		mm.waitFor(1);*/
//		UiObject scroll = new UiObject(new UiSelector());
//		scroll.swipeLeft(5);
//		mm.waitFor(1);
//		scroll.swipeLeft(5);
//		mm.waitFor(1);
		
		//have a problem
		UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		View.click();
		mm.waitFor(3);
	    
	/*	if(mm.isTextExist("下载")){
			mm.getObjectByText("下载", "android.widget.Button").click();
//			cc.assertTrue("CheckDownload", CheckSelectedByText("下载", "android.widget.Button"));
			mm.waitFor(3);	
		}*/
		
		if(mm.getObjectByText("应用", "android.widget.Button").isClickable()){
			mm.clickOnButton("应用");
			mm.waitFor(3);
			mm.saveScreenshot("apply.png");
		}
		try {
			getUiDevice().sleep();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mm.waitFor(2);
		try {
			getUiDevice().wakeUp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
