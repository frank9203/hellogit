package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0047_SmsThemeDownload extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	public void test_0001_SmsThemeDownload() throws UiObjectNotFoundException{
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

		// 点击混搭
		mm.log("Step 2 :Check MashUp");
		mm.getObjectByText("混搭", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("MashUp.png");
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
		mm.waitFor(1);
		mm.getObjectByText("短信主题", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("SmsTheme.png");
		mm.waitFor(1);
	
		mm.log("Step 3:Check SmsThemeDownload");
		//点击本地
		mm.log("Step 7 :Checl Loacl");
		mm.waitFor(2);
		mm.clickOnText("本地");
		mm.waitFor(2);
		mm.saveScreenshot("Local.png");
		mm.waitFor(1);
		UiObject view = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0)) ;
		if(view.exists()){
			view.longClick() ;
			mm.waitFor(2);
			 if(mm.isTextExist("全选")){
				 mm.clickOnButton("全选"); 
			 }
			 mm.saveScreenshot("delete.png");
			 mm.waitFor(1);
			 mm.clickOnButton("删除");
			 mm.waitFor(1);
			 mm.clickOnButton("确定");
			 mm.waitFor(2);
		}
		 mm.pressBack();
		
		//点击排行
		mm.log("Step 6: Check Ranking");
		mm.clickOnText("排行");
		mm.waitFor(1);
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("free.png");
		mm.waitFor(1);
		
		mm.log("Step 4: SmsTheme Download");
		UiObject view1 = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0)) ;
		view1.click() ;
		mm.waitFor(2);
		mm.clickOnButton("下载");
		mm.saveScreenshot("Download.png");
		mm.waitFor(3);
		mm.pressBack(4);
	}

	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
