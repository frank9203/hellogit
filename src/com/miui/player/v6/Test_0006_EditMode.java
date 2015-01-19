package com.miui.player.v6;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0006_EditMode extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private static int connection;
	private UiDevice mDevice= null;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();	
	}
	
	public void test_AllSongs_EditMode() throws UiObjectNotFoundException {
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
			
			mm.log("Step 2 : Enter Allsongs List.");
			mm.getObjectByText("我的音乐", "android.widget.TextView").click();
			mm.waitFor(1);
			UiObject allSongsList = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
						  .getChild(new UiSelector().className("android.widget.RelativeLayout").index(1));
			allSongsList.click();
			found_FC("进入全部歌曲列表");
			mm.waitFor(1);
			
			mm.log("Step 3 : Enter EditMode.");
			UiObject lc = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
					   .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));
			lc.longClick();
			mm.waitFor(1);

			mm.log("Step 4 : Click 全选|全不选.");
			UiObject song1 = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
			   		            .getChild(new UiSelector().className("android.widget.RelativeLayout").index(1));
			song1.click();
			UiObject song2 = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
	   		       				.getChild(new UiSelector().className("android.widget.RelativeLayout").index(2));
			song2.click();
			mm.clickOnButton("全选");
			found_FC("点击全选");
			mm.clickOnButton("全不选");
			found_FC("点击全不选");
			UiObject song = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
	            .getChild(new UiSelector().className("android.widget.RelativeLayout").index(1));
			song.click();
			mm.waitFor(1);
			
			mm.log("Step 5 : Click 添加到|发送|更多.");
			mm.clickOnButton("添加到");
			found_FC("点击添加到");
			mm.pressBack();
			mm.clickOnButton("删除");
			found_FC("点击删除");
			mm.pressBack();
			mm.clickOnButton("更多");
			found_FC("点击更多");
			mm.waitFor(1);
			mm.getObjectByText("发送", "android.widget.TextView").click();
			found_FC("点击发送");
			if(mm.getObjectByText("蓝牙")!=null){
				mm.pressBack();
			}
			mm.waitFor(1);
					
			mm.log("Step 6 : End.");
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
