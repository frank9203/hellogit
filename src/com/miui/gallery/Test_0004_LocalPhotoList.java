package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0004_LocalPhotoList extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private UiDevice mDevice= null; 
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		mDevice = UiDevice.getInstance();
	}
	
	public void test_Photo_List() throws UiObjectNotFoundException{
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
		found_FC("进入本地图册列表页");
		mm.waitFor(1);
		
		mm.log("Step 3 : 进入一个图册.");
		mm.click(180, 320);
		found_FC("进入图册");
		mm.waitFor(1);
		
		mm.log("Step 4 : slide.");
		mm.moveUp();
		found_FC("slide");
		mm.move(360, 200, 360, 500);
		mm.waitFor(1);
		
		mm.log("Step 5 : Edit mode.");
		mDevice.swipe(90, 200,90, 200,400);
		found_FC("编辑模式");
		mm.click(90, 400);
		mm.click(270, 200);
		mm.clickOnText("全选");
		mm.clickOnText("全不选");
		found_FC("编辑模式选择");
		mm.waitFor(1);
		mm.click(90, 200);
		mm.clickOnButton("发送");
		found_FC("发送图片");
		mm.waitFor(1);
		mm.pressBack();
		mm.clickOnButton("加到相册");
		found_FC("加到相册");
		while(mm.getObjectByClass("android.widget.ListView") == null)
			mm.waitFor(2);
		mm.waitFor(1);
		mm.pressBack(2);
		mm.waitFor(1);
		
		mm.log("Step 6 : Press menu.");
		mm.pressMenu();
		found_FC("pressmenu");
		mm.clickOnText("排序");
		mm.clickOnText("名称（升序）");
		mm.pressMenu();
		mm.clickOnText("排序");
		mm.clickOnText("时间（降序）");
		found_FC("排序");
		mm.waitFor(1);
		mm.pressMenu();
		mm.clickOnText("刷新");
		found_FC("刷新");
		mm.pressMenu();
		mm.clickOnText("设置");
		found_FC("设置");
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 7 : Press back.");
		UiObject back = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		back.click();
		found_FC("列表返回");
		mm.waitFor(1);
		
		mm.log("Step 8 : End.");
		mm.pressBack(2);
		
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
