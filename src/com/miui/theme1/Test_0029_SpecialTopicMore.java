package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0029_SpecialTopicMore extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}

	public void test_0001_MoreSpecialTopic() throws UiObjectNotFoundException{
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


		//点击主题风格
		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击专题
		mm.log("Step 2 :Check SpecialTopic");
		mm.getObjectByText("专题", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.saveScreenshot("SpecialTopic.png");
		mm.waitFor(1);
		
		/*mm.log("Step 2 :Check SpecialTopic");
		UiObject SpecialTopic = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(2));
		SpecialTopic.click() ;
		mm.waitFor(1);
		mm.saveScreenshot("SpecialTopic.png");
		mm.waitFor(1);*/
		//
		mm.log("Step 3 :Check MoreTopic");
		mm.click(285, 383);
		mm.waitFor(2);
	    UiScrollable scroll = new UiScrollable(new UiSelector().scrollable(true)) ;
	    scroll.scrollToEnd(10) ;
	    mm.saveScreenshot("scroll.png");
	    mm.waitFor(2);
	    mm.clickOnButton("搭配热门主题");
	    mm.saveScreenshot("MoreTopic.png");
	    mm.waitFor(2);
	    mm.pressBack(5);

	}


	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
