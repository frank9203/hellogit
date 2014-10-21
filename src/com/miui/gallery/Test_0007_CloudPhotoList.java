package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0007_CloudPhotoList extends UiAutomatorTestCase {
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
	
	public void test_Photo_List() throws UiObjectNotFoundException {
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
		
		mm.log("Step 3 : 进入相机图册.");
		mm.click(180, 320);
		found_FC("进入图册");
		mm.waitFor(1);
		
		mm.log("Step 4 : slide.");
		mm.move(360, 800, 360, 200);
		found_FC("slide");
		mm.move(360, 200, 360, 1000);
		mm.waitFor(1);
		
		mm.log("Step 5 : Press menu.");
		mm.pressMenu();
		mm.clickOnText("设置为屏保");
		found_FC("设置为屏保");
		if(mm.getObjectByText("确定") != null)
			mm.pressBack();
		mm.waitFor(1);
		mm.pressMenu();
		mm.clickOnText("取消屏保");
		found_FC("取消屏保");
		mm.waitFor(1);
		
		mm.log("Step 6 : Edit mode.");
		mDevice.swipe(100, 200,100, 200,400);
		found_FC("编辑模式");
		mm.click(100, 400);
		mm.click(300, 200);
		mm.clickOnText("全选");
		mm.clickOnText("全不选");
		found_FC("编辑模式选择");
		mm.waitFor(1);
		mm.click(100, 200);
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
		
		mm.log("Step 7 : Press back.");
		UiObject back = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
	          .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		back.click();
		found_FC("返回列表");
		mm.waitFor(1);
		
		mm.log("Step 8 : 进入自建图册并分享");
		mm.click(180, 640);
		found_FC("进入自建相册");
		UiObject share = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
	           .getChild(new UiSelector().className("android.widget.ImageView").index(2));
		share.click();
		found_FC("共享相册");
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 9 : 自建图册Press menu");
		mm.pressMenu();
		found_FC("pressmenu");
		mm.clickOnText("设置为屏保");
		found_FC("设置为屏保");
		mm.pressMenu();
		mm.clickOnText("取消屏保");
		found_FC("取消屏保");
		mm.waitFor(1);
		mm.pressMenu();
		mm.clickOnText("分享相册");
		found_FC("共享相册");
		mm.waitFor(1);
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("添加照片");
		found_FC("添加照片");
		mm.waitFor(1);
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("重命名");
		while(mm.getObjectByClass("android.widget.EditText") == null)
			mm.waitFor(2);
		found_FC("重命名");
		mm.waitFor(1);
		mm.pressBack(2);
		mm.pressMenu();
		mm.clickOnText("删除");
		found_FC("删除");
		mm.pressBack();
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 10 : 接收的共享相册。");
		mm.click(500, 1000);
		found_FC("进入共享相册");
		UiObject share1 = new UiObject(new UiSelector().className("android.widget.TableRow").index(0))
	            .getChild(new UiSelector().className("android.widget.ImageView").index(2));
		share1.click();
		found_FC("共享相册");
		mm.waitFor(1);
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 11 : 共享图册Pressmenu.");
		mm.pressMenu();
		found_FC("pressmenu");
		mm.clickOnText("设置为屏保");
		found_FC("设置为屏保");
		mm.pressMenu();
		mm.clickOnText("取消屏保");
		found_FC("取消屏保");
		mm.waitFor(1);
		mm.pressMenu();
		mm.clickOnText("查看成员");
		found_FC("查看成员");
		mm.waitFor(1);
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("添加照片");
		mm.waitFor(1);
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("退出共享");
		found_FC("退出共享");
		mm.pressBack();
		
		mm.log("Step 12 : End.");
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
