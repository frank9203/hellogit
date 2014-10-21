package com.miui.shell;

import android.os.RemoteException;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Text_0021_LockScreenWallpaperApplication extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	
	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0140_LockScreenWallpaperApplication() throws UiObjectNotFoundException{
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();

		mm.waitFor(2);
		mm.click(60, 127);
		mm.waitFor(2);
		mm.click(197, 491);
		mm.waitFor(1);
		UiObject scroll = new UiObject(new UiSelector());
		scroll.swipeLeft(5);
		mm.waitFor(1);
		scroll.swipeLeft(5);
		mm.waitFor(1);
		if(mm.getObjectByText("应用", "android.widget.Button").isClickable()){
			mm.clickOnButton("应用");
			mm.waitFor(3);
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
