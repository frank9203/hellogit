package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0042_NotificationRingtoneDownload extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	public void test_0001_NotificationRingtoneDownload() throws UiObjectNotFoundException{
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
		
		mm.log("Step 2: Check MashUp");
		UiObject mashup = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(3));
		 mashup.click() ;
		mm.saveScreenshot("MashUp.png");
		mm.waitFor(1);
		
		// 点击通知铃声
		mm.getObjectByText("通知铃声", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("screenstyle.png");
		mm.waitFor(1);
		
		mm.log("Step 3: Download Ringtones");
		UiObject ImageButton = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1)) 
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageButton").index(1));
		
		if(ImageButton.exists()){
			ImageButton.click() ;
			mm.waitFor(20);
			mm.saveScreenshot("Download.png");
		}

		mm.log("Step 4 :Delete Local ");
		UiObject  Local = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(2));
		Local.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("Local.png");
		mm.waitFor(1);
		if(DisplayWidth <=720){
			mm.move(300, 500, 300, 1000);
		}
		if(DisplayWidth <=1080){
			mm.move(500, 500, 500, 1500);
		}else{
			mm.move(700, 500, 700, 2000);
		}
		mm.waitFor(2);
		mm.getObjectByText("全部铃声", "android.widget.TextView").click();
		mm.waitFor(2);
		UiObject bar = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(2)) ;
		if(bar.exists()){
			bar.longClick() ;
			mm.waitFor(2);
			 if(mm.isTextExist("全选")){
				 mm.clickOnButton("全选"); 
			 }
			 mm.saveScreenshot("delete.png");
			 mm.waitFor(1);
			 mm.clickOnButton("删除");
			 mm.waitFor(1);
			 mm.clickOnButton("确定");
			 mm.waitFor(3); 
		}
		mm.pressBack(4);	
			
	}
	
	
	
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
