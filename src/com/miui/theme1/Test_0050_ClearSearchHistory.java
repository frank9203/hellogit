package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0050_ClearSearchHistory extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	public void test_0001_BootAnimaltionShareCollect() throws UiObjectNotFoundException{
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

		//搜索专题
		mm.log("Step 2 : Search SpecialTopic");
		UiObject search = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ImageView").index(2)) ;
		search.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("SearchTheme.png");
		mm.waitFor(1);
		UiObject view = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.view.View").index(0))
		.getChild(new UiSelector().className("android.widget.TextView").index(0)) ;
		view.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("view.png");
		mm.waitFor(1);
		mm.pressBack();
		
		//点击分类
		mm.log("Step 3:check theme launcher");
		if(DisplayWidth ==720){
			mm.pressBack(3);
		}else{
			for(int i=0;i<5;i++){
				UiObject theme = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
				.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
				.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
				.getChild(new UiSelector().className("android.widget.LinearLayout").index(i));
				theme.click() ;
				mm.waitFor(1);
				mm.saveScreenshot(i+"theme.png");
				mm.waitFor(1);
				mm.pressBack();
			}
		}
		
		mm.pressBack(2);

	}

	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
