package com.miui.shell;
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0043_DesktopWallpaperSelectOther extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0205_DesktopWallpaperSelectOther() throws UiObjectNotFoundException{
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
		
		mm.log("Setp 3 : Check My List.");
		mm.click(60,127);
		mm.waitFor(1);
		mm.getObjectByText("通过其他程序选择", "android.widget.Button").click();
		mm.waitFor(1);
		mm.getObjectByText("图库", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.click(540, 97);
		mm.waitFor(1);
		mm.click(169,602);
		mm.waitFor(1);
		mm.getObjectByText("应用", "android.widget.Button").click();
//		cc.assertTrue("CheckButton", CheckSelectedByText("应用", "android.widget.Button"));
		mm.waitFor(1);
		mm.saveScreenshot("DesktopWallpaper.png");
		mm.waitFor(1);
		
		mm.pressBack(4);
		mm.saveScreenshot("DesktopWallpaperEffect.png");
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
