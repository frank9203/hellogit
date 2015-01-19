package com.miui.theme;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0006_MyPreview extends UiAutomatorTestCase{
	public Marmot mm ;
	public Checker cc ;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	
	public void test_0001_MyPreview() throws UiObjectNotFoundException{
		
		mm.log("Step 1 : Unlock Open");
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
		//	mm.move(500, 1500, 500, 700);
			mm.waitFor(2);
		}	
		
		mm.log("Step 2 :My theme Preview");
		mm.clickOnText("主题风格");

		mm.waitFor(2);
		mm.clickOnImage(0);
		mm.waitFor(2);

		UiObject Default = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0));
		Default.click() ;
		mm.waitFor(2);
		mm.saveScreenshot("Default.png");
		 
		UiObject scroll = new UiObject(new UiSelector()) ;
		scroll.swipeLeft(3);
		mm.waitFor(2);
		scroll.swipeLeft(3);
		mm.waitFor(2);
		mm.saveScreenshot("scroll.png");
		scroll.swipeLeft(3);
		mm.waitFor(2);
		scroll.swipeLeft(3);
		mm.waitFor(2);
		scroll.swipeLeft(3);
		mm.waitFor(2);
		scroll.swipeLeft(3);
		mm.waitFor(2);
		scroll.swipeRight(3);
		mm.waitFor(2);
		scroll.swipeRight(3);
		mm.waitFor(2);
		scroll.swipeRight(3);
		mm.waitFor(2);
		scroll.swipeRight(3);
		mm.waitFor(2);
		scroll.swipeRight(3);
		mm.waitFor(2);
		scroll.swipeRight(3);
		mm.waitFor(2);
		mm.pressBack(4);
    		
	}
	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
