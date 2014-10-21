package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_Homepage extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_Launch_gallery() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		
		mm.log("Step 1 : Launch Gallery Activity.");
		mm.pressHome();
		mm.launchActivity("com.miui.gallery/.app.Gallery");
		mm.waitFor(1);
		found_FC("Launch");
		mm.waitFor(1);
		
		mm.log("Step 2 : Click in the local photo list.");
		mm.clickOnText("本地图片");
		found_FC("点击本地图片");
		mm.waitFor(1);
		mm.pressBack();
		
		mm.log("Step 3 : Press menu.");
		mm.pressMenu();
		found_FC("pressmenu");
		mm.clickOnText("刷新");
		found_FC("刷新");
		mm.pressMenu();
		mm.clickOnText("设置");
		found_FC("设置");
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(2);
		
		
		mm.log("Step 4 : 点击查看大图/视频");
		mm.click(100, 200);
		found_FC("查看大图");
		mm.waitFor(2);
		mm.pressBack();
		mm.waitFor(2);
		mm.move(360, 700, 360, 300);;
		mm.click(300, 640);
		found_FC("查看视频");
		mm.waitFor(1);
		mm.click(360, 640);
		found_FC("播放视频");
		mm.waitFor(2);
		mm.pressBack(2);
		mm.waitFor(1);
		mm.pressBack();
		mm.move(360, 300, 360, 700);
		
		mm.log("Step 5 : Click in the cloud photo list.");
		mm.clickOnText("云相册");
		found_FC("打开云相册");
		mm.clickOnText("开始使用云相册");
		mm.waitFor(2);
		mm.pressBack();
		mm.waitFor(1);
		mm.clickOnText("云相册");
		found_FC("打开云相册");
		mm.waitFor(4);
		mm.pressBack(2);
		
		mm.log("Step 6: End.");
		mm.pressBack();
		mm.pressHome();
		
		
	}
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("鎶ュ憡 MIUI") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("纭畾");
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}
