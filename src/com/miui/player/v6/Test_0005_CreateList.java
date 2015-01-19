package com.miui.player.v6;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0005_CreateList extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private static int connection;
	private UiDevice mDevice= null;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();	
	}
	
	public void test_Create_List() throws UiObjectNotFoundException {
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
			
			mm.log("Step 2 : Create a list.");
			mm.getObjectByText("我的音乐", "android.widget.TextView").click();
			mm.waitFor(1);
			mm.move(540, 1000, 540, 800);
			while(mm.getObjectByText("添加歌单") == null){
				mm.move(540, 1000, 540, 800);
			}
			mm.clickOnText("添加歌单");
			found_FC("添加歌单");
			mm.waitFor(1);
			mm.clickOnButton("新建歌单");
			mm.waitFor(2);
			mm.clickOnButton("确定");
			found_FC("新建歌单");
			mm.waitFor(1);
			
			mm.log("Step 3 : Add song.");
			UiObject song = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
				  .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));
			song.click();
			mm.clickOnButton("完成");
			found_FC("添加歌曲");
			mm.waitFor(1);
			
			mm.log("Step 4 : Play CreateList.");
			while(mm.getObjectByText("新播放列表 1") == null){
				mm.move(360, 900, 360, 1200);
			}
			mm.clickOnText("新播放列表 1");
			mm.waitFor(1);
			UiObject play = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
			  	  .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));
			play.click();
			found_FC("播放歌曲");
			mm.waitFor(1);
			
			mm.log("Step 5 : End.");
			mm.pressBack(2);
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

