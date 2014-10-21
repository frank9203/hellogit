package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0037_DesktopWallpaperDownload extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0188_DesktopWallpaperDownload() throws UiObjectNotFoundException{
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
//		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check DesktopWallpaper.");
		mm.getObjectByText("桌面壁纸", "android.widget.TextView").click();
		mm.waitFor(2);
		
		mm.log("Step 3 : Download.");
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(3);
		mm.click(282, 450);//点击第一张图片
		mm.waitFor(3);
		String text = new UiObject(new UiSelector().className("android.widget.TextView")).getText();
		new UiObject(new UiSelector().className("android.widget.Button").clickable(true)).waitForExists(5);
		if(mm.isTextExist("下载"))
		{
			mm.clickOnButton("下载");
			mm.waitFor(2);
		}
		if(mm.isTextExist("应用")&& mm.getObjectByText("应用", "android.widget.Button").isClickable()){
			mm.saveScreenshot("Wallpaper.png");
			mm.pressBack();
			mm.click(60, 127);//点击我的列表
			mm.waitFor(1);
			mm.click(282, 450);
			mm.waitFor(1);
			mm.saveScreenshot("WallpaperDownload.png");
			cc.assertTrue("Check My List", mm.isTextExist(text));
		}		
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
