package com.miui.gallery;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0009_Picker extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_Picker() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		
		mm.log("Step 1 : Launch Mms Activity.");
		mm.pressHome();
		mm.launchActivity("com.android.mms/.ui.MmsTabActivity");
		mm.waitFor(1);
		found_FC("Launch");
		mm.waitFor(1);
		
		mm.log("Step 2 : 写短信添加一张图片.");
		mm.clickOnButton("写短信");
		found_FC("写短信");
		mm.getObjectByDescription("添加附件").click();
		found_FC("添加附件");
		mm.clickOnText("图片");
		mm.waitFor(1);
		UiObject gallery = new UiObject(new UiSelector().className("android.widget.GridView").index(0))
	             .getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
	             .getChild(new UiSelector().className("android.widget.ImageView").index(0));
		gallery.click();
		found_FC("打开图库");
		mm.waitFor(1);
		mm.click(100, 400);
		mm.waitFor(1);
		found_FC("选择一张图片添加");
		mm.waitFor(1);
		UiObject picture = new UiObject(new UiSelector().className("android.widget.ImageView").index(0).instance(1));
		picture.click();
		found_FC("点击添加的图片");
		mm.click(100, 400);
		found_FC("查看大图");
		mm.waitFor(1);
		mm.pressBack();
		mm.pressHome();
		mm.waitFor(1);
		
		mm.log("Step 3 : 桌面添加图册.");
		mm.moveRight();
		mm.pressMenu();
		mm.clickOnText("编辑模式");
		found_FC("桌面编辑模式");
		mm.clickOnText("添加小工具");
		found_FC("添加小工具");
		mm.move(540, 1100, 100, 1100, 3);
		mm.clickOnText("相册");
		UiObject atlas = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
	           .getChild(new UiSelector().className("miui.widget.CheckedTextView").index(0));
		atlas.click();
		found_FC("选择图册");
		mm.waitFor(2);
		mm.click(180, 450);
		found_FC("桌面添加图册");
		mm.pressHome();
		mm.waitFor(1);
		
		mm.log("Step 4 : 便签中添加图片.");
		mm.moveLeft();
		mm.moveLeft();
		mm.clickOnText("便签");
		found_FC("打开便签");
		mm.clickOnButton("新建便签");
		UiObject attach = new UiObject(new UiSelector().className("android.view.View").index(0))
                .getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
                .getChild(new UiSelector().className("android.widget.ImageView").index(3));
		attach.click();
		mm.waitFor(1);
		mm.click(300, 220);
		found_FC("便签中打开图库");
		mm.click(100, 400);
		found_FC("便签中添加图片");
		mm.waitFor(1);
		mm.pressBack(2);
		
		mm.log("Step 5 : End.");
		mm.pressHome();
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
