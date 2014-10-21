package com.miui.shell;

import android.os.RemoteException;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0027_LockScreenWallpaperSetWallpaperBoth extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0157_LocoScreenWallpaperSetWallpaperBoth() throws UiObjectNotFoundException{
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
//		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Check My List.");
		mm.click(60, 127);//我的列表坐标
		mm.waitFor(1);
		
		mm.click(197, 491);//第一张图片坐标
		mm.waitFor(1);
		UiScrollable scroll = new UiScrollable(new UiSelector());
		scroll.swipeLeft(5);
		mm.waitFor(1);
		mm.click(978, 1819);//点击图片详细信息
		mm.waitFor(1);
		mm.click(671,1491);//点击同时设置成桌面和锁屏
		mm.saveScreenshot("Wallpaper.png");
		mm.waitFor(2);
		mm.pressBack(3);
		mm.pressHome();
		mm.saveScreenshot("DesktopWallpaper.png");
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
		mm.waitFor(1);
		mm.saveScreenshot("LockScreenWallpaper.png");

	}
	
	private boolean CheckSelectedByText(String textName,String className) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, className).isSelected()){
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
