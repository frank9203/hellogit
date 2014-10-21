package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0019_LockScreenWallpaperDownload extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0138_LockScreenWallpaperDownload() throws UiObjectNotFoundException{
		
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(3);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(2);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();

		mm.waitFor(2);
		mm.getObjectByText("精品", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.click(197, 1222);
		mm.waitFor(3);
		
		if(mm.isTextExist("下载")){
			mm.getObjectByText("下载", "android.widget.Button").click();
//			cc.assertTrue("CheckDownload", CheckSelectedByText("下载", "android.widget.Button"));
			mm.waitFor(3);	
		}
		
		if(mm.isTextExist("应用")&&mm.getObjectByText("应用").isClickable()){
			mm.pressBack();
		}
		mm.click(60, 127);
		mm.waitFor(2);
		mm.click(197, 491);
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
