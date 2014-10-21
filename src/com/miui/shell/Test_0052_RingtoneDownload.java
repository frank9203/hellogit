package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0052_RingtoneDownload extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0225_RingtoneDownload() throws UiObjectNotFoundException{
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : check ringtone page.");
		mm.getObjectByText("来电铃声", "android.widget.TextView").click();
		new UiObject(new UiSelector().className("android.widget.ImageButton")).waitForExists(10);
		
		mm.log("Step 3 : download ringtone.");
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(2);
		}
	
		UiObject object = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(0));
		UiScrollable scroll = new UiScrollable(new UiSelector());
		scroll.scrollIntoView(new UiSelector().className("android.widget.ImageButton").instance(0));
		object.click();//点击第一个可下载资源
		mm.waitFor(0.5);
		mm.saveScreenshot("RingtoneDownloading.png");
		object.waitUntilGone(10);
		mm.waitFor(1);
		
		mm.log("Set 4 : Show in my list.");
		mm.click(60, 127);//点击我的列表
		mm.waitFor(2);
		mm.saveScreenshot("RingtoneDownloaded.png");
		mm.waitFor(1);
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
