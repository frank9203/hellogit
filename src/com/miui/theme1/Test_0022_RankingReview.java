package com.miui.theme1;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0022_RankingReview extends UiAutomatorTestCase {
	Marmot mm ;
	Checker cc ;

	@Override 
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass()) ;
		cc = new Checker() ;

	}

	//排行里面主题的评论
	public void test_0001_RankingReview() throws UiObjectNotFoundException{
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
		mm.log("Step 2 :Check Ranking");
		UiObject ranking = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0));
		ranking.click() ;
		mm.waitFor(2);
		mm.getObjectByText("免费", "android.widget.TextView").click();
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
		mm.log("Step 3 :Review Ranking Theme");
	//	mm.getObjectByText("评论", "android.widget.TextView").click();
	//	mm.getObjectByClass("android.widget.TextView", 5).click() ;
		UiObject review = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
		.getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
		.getChild(new UiSelector().className("android.widget.ScrollView").index(0))
		.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
		.getChild(new UiSelector().className("android.widget.TextView").index(5).resourceId("com.android.thememanager:id/resource_detail_comment_entry")) ;
				
		review.click() ;
		
		mm.waitFor(2);
		mm.saveScreenshot("both.png");
		mm.waitFor(1);
		mm.getObjectByText("好评", "android.widget.Button").click();
		mm.saveScreenshot("goodRevies.png");
		mm.waitFor(1);
		mm.getObjectByText("差评", "android.widget.Button").click();
		mm.saveScreenshot("badReview.png");
		mm.waitFor(1);
		
		mm.log("Step 4 : Write a review");
		mm.getObjectByText("撰写评论", "android.widget.Button").click();
		mm.saveScreenshot("Writereview.png");
		mm.waitFor(2);
		mm.pressBack(6);
	}



	@Override
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
