package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0023_RankingGiftExchange extends UiAutomatorTestCase{
	Marmot mm ;
	Checker cc ;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;
	}
	
	public void test_0001_RankingGiftExchange() throws UiObjectNotFoundException{
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

		//点击我的主题
		mm.pressHome();
		mm.clickOnText("主题风格");
		//点击排行
		mm.log("Step 2 : Check Ranking");
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.saveScreenshot("Ranking.png");
		mm.waitFor(2);
		mm.getObjectByText("收费", "android.widget.TextView").click();
		mm.waitFor(2);
		UiObject theme = new UiObject (new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.ListView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		theme.click() ;
		mm.move(300, 1000, 300, 500,2);
		mm.waitFor(2);
		mm.log("Step 3 :GiftExchange Ranking Theme");
		mm.getObjectByText("赠送", "android.widget.Button").click();
		mm.waitFor(3);
		mm.saveScreenshot("Gift.png");
		mm.waitFor(2);
		mm.pressBack();
		mm.getObjectByText("兑换", "android.widget.Button").click();
		mm.waitFor(3);
		mm.saveScreenshot("Exchange.png");
		mm.waitFor(1);
		mm.pressBack(2);
	
		mm.waitFor(2);
		mm.pressBack(6);
	}

	
	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}
}
