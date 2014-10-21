package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0036_DesktopWallpaperUiCheck extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0186_DesktopWallpaperUiCheck() throws UiObjectNotFoundException{
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
		UiScrollable scroll = new UiScrollable(new UiSelector().scrollable(true));
		cc.assertTrue("Check Featured Tab", CheckSelectedByText("精品", "android.widget.TextView"));
		scroll.scrollToEnd(10);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		cc.assertTrue("Check Top Tab", CheckSelectedByText("排行", "android.widget.TextView"));
		mm.waitFor(3);
		scroll.scrollToEnd(10);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("Check Category Tab",CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.click(285, 382);
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(3);
		}
		cc.assertTrue("Check Featured Tab", CheckSelectedByText("精品", "android.widget.TextView"));
		scroll.scrollToEnd(10);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		cc.assertTrue("Check Top Tab", CheckSelectedByText("排行", "android.widget.TextView"));
		mm.waitFor(3);
		scroll.scrollToEnd(10);
		mm.getObjectByText("新品", "android.widget.TextView").click();
//		cc.assertTrue("Check New Tab",CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(3);
		scroll.scrollToEnd(10);
		mm.pressBack();
		mm.click(60, 127);//点击我的列表
		scroll.scrollToEnd(10);
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
