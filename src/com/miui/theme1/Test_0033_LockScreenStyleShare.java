package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0033_LockScreenStyleShare extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;


	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_LockScreenStyleShare() throws UiObjectNotFoundException{
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


		mm.log("Step 2 : Check XiaoMiAccount");
		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击进入我的按钮
		mm.clickOnImage(0);
		mm.waitFor(2);
		mm.pressBack();
		//点击混搭

		mm.log("Step 2 :Check MashUp");
		mm.getObjectByText("混搭", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("MashUp.png");
		mm.waitFor(1);

		// 点击锁屏样式
		mm.log("Step 4: Check ScreenStyle");
		UiObject screenstyle = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.GridView").index(0)) 
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));	
		screenstyle.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("screenstyle.png");
		mm.waitFor(1);


		//分别点击排行,分享
		mm.log("Step 5: Share and Collect theme");
		UiObject ranking = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		ranking.click() ;
		mm.waitFor(2);
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("free.png");
		mm.waitFor(1);
		UiObject theme1 = new UiObject (new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		theme1.click() ;
		mm.waitFor(1);
		//分享主题
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

		//收藏主题
		UiObject Collect = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(2));
		Collect.click() ;
		mm.waitFor(2);
		mm.saveScreenshot("Collect.png");
		mm.waitFor(1);
		mm.pressBack(5);
	}

	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
