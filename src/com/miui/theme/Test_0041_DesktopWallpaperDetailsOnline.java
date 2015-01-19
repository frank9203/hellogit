package com.miui.theme;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0041_DesktopWallpaperDetailsOnline extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0200_DesktopWallpaperDetailsOnline() throws UiObjectNotFoundException{
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
		
		
		
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check DesktopWallpaper.");
		mm.getObjectByText("桌面壁纸", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("desktopwallpaper.png");
		mm.log("Step 3 : Check DesktopWallpaper OnlinePage.");
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(2);
		}
		mm.click(282, 1193);
		new UiObject(new UiSelector().className("android.widget.Button").clickable(true)).waitForExists(5);
		mm.saveScreenshot("DesktopWallpaperOnlineDetail1.png");
		mm.click(60, 68);
		mm.waitFor(1);
		mm.click(282, 1193);
		mm.waitFor(1);
		mm.click(102, 1819);
		mm.saveScreenshot("DesktopWallpaperOnlineDetail2.png");
		mm.getObjectByText("关闭", "android.widget.Button").click();
		mm.waitFor(2);
		mm.saveScreenshot("close.png");
		if(mm.isTextExist("下载")){
			mm.getObjectByText("下载", "android.widget.Button").click();
			mm.waitFor(3);
		}
		mm.click(978, 1819);
		mm.saveScreenshot("DesktopWallpaperOnlineDetail3.png");
		mm.pressBack(4);
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
