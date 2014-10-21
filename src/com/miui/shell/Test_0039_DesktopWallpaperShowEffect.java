package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0039_DesktopWallpaperShowEffect extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0190_DesktopWallpaperShowEffect() throws UiObjectNotFoundException{
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
		
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(3);
		}
		mm.click(282, 1193);
		
//		mm.getObjectByText("分类", "android.widget.TextView").click();
//		mm.click(285, 382);
//		mm.waitFor(1);
//		mm.click(282, 450);
		UiObject object = new UiObject(new UiSelector().className("android.widget.Button").clickable(true));
		object.waitForExists(5);
		
		UiScrollable scroll = new UiScrollable(new UiSelector());
		
		scroll.swipeLeft(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeRight(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeRight(2);
		object.waitForExists(5);
		mm.waitFor(2);
		
		mm.click(540, 960);
		
		scroll.swipeLeft(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeLeft(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeRight(2);
		object.waitForExists(5);
		mm.waitFor(2);
		scroll.swipeRight(2);
		object.waitForExists(5);
		mm.waitFor(2);
		
		mm.pressBack(5);
		
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
