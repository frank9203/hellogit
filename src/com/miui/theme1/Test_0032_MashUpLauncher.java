package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0032_MashUpLauncher extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_MashUpLauncher() throws UiObjectNotFoundException{
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
		mm.log("Step 2 :Check MashUp");
		mm.getObjectByText("混搭", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("MashUp.png");
		mm.waitFor(1);

		mm.log("Step 3: MashUp Launcher");
		for(int i=0;i<7;i++){
			UiObject theme = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.GridView").index(0)) 
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(i));	
			theme.click() ;
			mm.waitFor(1);
			mm.saveScreenshot(i+"theme.png");
			mm.waitFor(1);
			mm.pressBack();
		}
		
		if(DisplayWidth <=720){
			mm.move(300, 1000, 300, 500);
		}
		if(DisplayWidth <=1080){
			mm.move(500, 1700, 500, 500);
		}else{
			mm.move(700, 2000, 700, 500);
		}
		for(int j=0;j<6;j++){
			
			UiObject theme1 = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
			.getChild(new UiSelector().className("android.widget.FrameLayout").index(1)) 
			.getChild(new UiSelector().className("android.widget.GridView").index(0))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(j));
			theme1.click() ;
			mm.waitFor(1);
			mm.saveScreenshot(j+"theme1.png");
			mm.waitFor(1);
			mm.pressBack();
		}
		mm.pressBack(3);

	}


	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
