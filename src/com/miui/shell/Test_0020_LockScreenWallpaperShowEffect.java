package com.miui.shell;

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
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();

		mm.waitFor(2);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(2);
		
		UiObject scroll = new UiObject(new UiSelector());
		mm.click(197, 420);
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
		mm.waitFor(2);
		
		mm.click(540, 960);
		
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
		mm.waitFor(2);
		
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
