package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0002_LocalList extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private UiDevice mDevice= null; 
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		mDevice = UiDevice.getInstance();
	}
	
	public void test_Local_List() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(540, 960, 540, 1280);
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
		
		mm.log("Step 3 : Edit mode.");
		mDevice.swipe(180, 320,180, 320,400);
		found_FC("编辑模式");
		mm.click(540, 320);
		mm.click(180, 640);
		mm.clickOnText("全选");
		mm.clickOnText("全不选");
		found_FC("编辑模式选择");
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 4 : Slide.");
		mm.moveUp();
		found_FC("slide");
		mm.waitFor(1);
		
		mm.log("Step 5 : Press menu.");
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
		
		mm.log("Step 6 : Press back.");
		UiObject back = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
		      .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		back.click();
		found_FC("列表返回");
		mm.waitFor(1);
		
		mm.log("Step 7 : End.");
		mm.pressBack();
		
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
