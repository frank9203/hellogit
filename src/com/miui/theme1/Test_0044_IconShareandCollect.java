package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0044_IconShareandCollect extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_IconShareandCollec() throws UiObjectNotFoundException{
		mm.log("Step 1 : OpenUnlock");
		int DisplayWidth ;
		DisplayWidth = this.getUiDevice().getDisplayWidth() ;
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.waitFor(1);
			if(DisplayWidth <= 480){
				mm.move(300, 700, 300, 200);
			}
			if(DisplayWidth <=540){
				mm.move(300, 900, 300, 200);
			}
			if(DisplayWidth <=720){
				mm.move(300, 1000, 300, 500);
			}
			if(DisplayWidth <=1080){
				mm.move(500, 1700, 500, 500);
			}else{
				mm.move(700, 2000, 700, 500);
			}
			mm.waitFor(2);
		}

		mm.pressHome();
		mm.clickOnText("主题风格");

		//点击混搭

		mm.log("Step 2 :Check MashUp");
		mm.getObjectByText("混搭", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("MashUp.png");
		mm.waitFor(1);

		// 点击图标
		mm.log("Step 3: Check ScreenStyle");
		mm.getObjectByText("图标", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("icon.png");
		mm.waitFor(2);

		//分享收藏
		mm.log("Step 4 : Share and Collect Icon");
		UiObject view = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.view.View").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(0)) ;
		view.click() ;

		//分享
		UiObject share = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(0));
		share.click() ;
		mm.waitFor(2);
		mm.saveScreenshot("share.png");
		mm.waitFor(1);
		mm.pressBack();

		//收藏
		UiObject Collect = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(2));
		Collect.click() ;
		mm.waitFor(2);
		mm.saveScreenshot("Collect.png");
		mm.waitFor(1);
		mm.pressBack(4);
	}

	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}


}
