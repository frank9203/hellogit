package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0008_CloudPhotos extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_Photoes() throws UiObjectNotFoundException {
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
		mm.clickOnText("云相册");
		found_FC("云相册");
		mm.waitFor(1);
		
		mm.log("Step 3 : 打开一个图片.");
		mm.click(500, 1000);
		found_FC("进入图册");
		mm.waitFor(1);
		mm.click(90, 200);
		found_FC("点大图");
		mm.waitFor(1);
		
		mm.log("Step 4 : Turn left/right.");
		mm.moveLeft();
		found_FC("切换图片");
		mm.waitFor(1);
		mm.moveRight();
		mm.waitFor(1);
		
		mm.log("Step 5 : Send.");
		mm.click(360, 640);
		UiObject send = new UiObject(new UiSelector().className("android.widget.Button").index(0));
		send.click();
		found_FC("发送图片");
		mm.waitFor(1);
		mm.pressBack();
		
		mm.log("Step 6 : 点击更多并加到相册.");
		mm.click(360, 640);
		UiObject more = new UiObject(new UiSelector().className("android.widget.Button").index(3));
		more.click();
		UiObject list = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2));
		int n = list.getChildCount();
		mm.clickOnText("加到相册");
		found_FC("加到相册");
		while(mm.getObjectByClass("android.widget.ListView") == null)
			mm.waitFor(2);
		mm.waitFor(1);
		mm.pressBack(1);
		
		mm.log("Step 7 : 幻灯片播放.");
		UiObject play = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(0).instance(1))
			  .getChild(new UiSelector().className("android.widget.ImageView").index(1));
		play.click();
		found_FC("幻灯片播放");
		mm.waitFor(4);
		mm.pressBack(1);
		mm.waitFor(1);
		
		mm.log("Step 8 : 下载原图.");
		mm.click(360, 640);
		mm.clickOnButton("更多");
		UiObject dl = new UiObject(new UiSelector().className("android.widget.TextView").index(n-1));
		dl.click();
		found_FC("下载原图");
		
		
		mm.log("Step 9 : Press back.");
		UiObject back = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(0).instance(2))
			  .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		if(mm.getObjectByText("更多") == null)
			mm.click(360, 640);
		back.click();
		found_FC("返回");
		mm.waitFor(1);
		
		mm.log("Step 10 : 查看共享视频并下载.");
		mm.click(200, 850);
		found_FC("查看一个共享视频");
		mm.waitFor(1);
		mm.click(360, 640);
		mm.clickOnButton("下载");
		found_FC("下载视频");
		mm.pressBack();
		
		mm.log("Step 11 : End.");
		mm.pressBack(3);
	}
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("报告 MIUI") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("确定");
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}
