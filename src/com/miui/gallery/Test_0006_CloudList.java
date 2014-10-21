package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0006_CloudList extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private UiDevice mDevice= null; 
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		mDevice = UiDevice.getInstance();
	}
	
	public void test_Cloud_List() throws UiObjectNotFoundException {
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
		
		mm.log("Step 2 : Click in the cloud photo list.");
		mm.clickOnText("云相册");
		found_FC("进入云相册列表页");
		mm.waitFor(1);
		
		mm.log("Step 3 : Slide.");
		mm.move(360, 500, 360, 200);
		found_FC("slide");
		mm.move(360, 200, 360, 800);
		mm.waitFor(1);
		
		mm.log("Step 4 : Edit mode.");
		mDevice.swipe(180, 320,180, 320,400);
		found_FC("编辑模式");
		mm.click(540, 320);
		mm.click(180, 640);
		mm.clickOnText("全选");
		mm.clickOnText("全不选");
		found_FC("编辑模式选择");
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 5 : 新建云相册.");
		mm.clickOnText("新建相册");
		found_FC("新建云相册");
		while(mm.getObjectByText("新建云相册") == null)
			mm.waitFor(2);
		String time = mm.getSystemTime();
		mm.getObjectByText("输入相册名称").setText(time);
		mm.clickOnButton("确定");
		found_FC("新建云相册");
		mm.waitFor(2);
		mm.click(100, 350);
		mm.waitFor(1);
		mm.clickOnText("确定");
		found_FC("新建云相册");
		mm.waitFor(1);
		
		mm.log("Step 6 : 扫描二维码.");
		mm.clickOnText("扫描二维码");
		found_FC("扫描二维码");
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 7 : Press back.");
		UiObject back = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
	      .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		back.click();
		found_FC("列表返回");
		mm.waitFor(1);
		
		mm.log("Step 8 : End.");
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
