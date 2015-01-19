package com.miui.player.v6;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0003_Search extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private static int connection;
	private UiDevice mDevice= null;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();	
	}
	
	public void test_Search_OnlineMusic() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.waitFor(2);
			mm.move(540, 1750, 540, 1400);
			mm.pressHome();
		}
		
		mm.log("Step 1 : Launch Player Activity.");
		mm.launchActivity("com.miui.player/.ui.MusicBrowserActivity");
		found_FC("打开音乐");
		judge_Connection();
		if (connection == 5){
			mm.saveScreenshot("Errorshot");
			mm.log("Error:no network.");				
		}
		else{
			mm.waitFor(1);
			
			mm.log("Step 2 : Enter Search Page.");
			UiObject search = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(0).instance(0))
			  				.getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
			  				.getChild(new UiSelector().className("android.widget.ImageView").index(2));
			search.click();
			found_FC("进入搜索页");
			mm.waitFor(1);
			
			mm.log("Step 3 : Search a singer.");
			mm.getObjectByClass("android.widget.EditText").click();
			mm.click(1000, 1400);
			mm.click(375, 1450);
			mm.click(675, 1450);
			mm.click(1010, 1130);
			mm.waitFor(1);
			if(mm.getObjectByText("网络不可用，请联网后重试") == null){
				mm.log("Step 4 : Play Search Song.");
				UiObject playsong = new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(7));
				playsong.click();
				found_FC("播放搜索歌曲");
				mm.waitFor(1);
				
				mm.log("Step 5 : End.");
				mm.pressBack(2);
				
			}else{
				mm.saveScreenshot("Errorshot");
				mm.log("Error:no network.");	
			}
		}
	}
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("报告 MIUI") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("确定");
		}
	}
	
	public void judge_Connection() throws UiObjectNotFoundException {
		int i = 0;
		while(mm.getObjectByClass("android.widget.ProgressBar") != null)
		{
			mm.waitFor(2);
			i+=2;
			if( i>=10 ){
				connection = 5;
				break;
			}	
		}
		
		if( mm.getObjectByText("重试", "android.widget.TextView") != null) {
			mm.getObjectByText("重试", "android.widget.TextView").click();
			if (connection < 5){
				connection += 1;
				judge_Connection();
			}
		}
	}
	
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
	
}

