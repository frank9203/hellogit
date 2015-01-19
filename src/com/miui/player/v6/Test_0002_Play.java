package com.miui.player.v6;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0002_Play extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private static int connection;
	private UiDevice mDevice= null;
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();	
	}
	
	public void test_Download_Music() throws UiObjectNotFoundException {
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
			mm.log("Step 2 : Click an album");
			UiObject album = new UiObject(new UiSelector().className("android.widget.GridLayout").index(1))
							.getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));
			album.click();
			found_FC("点击一个专题");
			connection = 0;
			judge_Connection();
			if (connection == 5){
				mm.saveScreenshot("Errorshot");
				mm.log("Error:no network.");				
			}
			else{
				mm.waitFor(2);
				
				mm.log("Step 3 : Play a song. ");
				UiObject Play = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
					  				.getChild(new UiSelector().className("android.widget.LinearLayout").index(1));
				Play.click();
				found_FC("播放音乐");
				UiObject Enter = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(2));
				Enter.click();
				found_FC("进入正在播放页");
				mm.waitFor(2);
				
				mm.log("Step 4 : Slide.");
				mm.move(900, 1000, 100, 1000);
				mm.waitFor(1);
				mm.move(900, 1000, 100, 1000);
				mm.waitFor(1);
				mm.move(100, 1000, 900, 1000);
				mm.waitFor(1);
				
				mm.log("Step 5 : Click Pause|Last|Next.");
				UiObject pause = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(2))
									 .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									 .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									 .getChild(new UiSelector().className("android.widget.ImageView").index(1));
				pause.click();
				found_FC("暂停");
				mm.waitFor(1);
				UiObject last = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(2))
									 .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									 .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									 .getChild(new UiSelector().className("android.widget.ImageView").index(0));
				last.click();
				found_FC("上一曲");
				mm.waitFor(1);
				UiObject next = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(2))
									 .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									 .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									 .getChild(new UiSelector().className("android.widget.ImageView").index(2));
				next.click();
				found_FC("下一曲");
				mm.waitFor(1);
				
				mm.log("Step 6 : Click favorite.");
				UiObject fav = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(1))
									.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
									.getChild(new UiSelector().className("android.widget.RelativeLayout").index(0))
									.getChild(new UiSelector().className("android.widget.ImageView").index(3));
				fav.click();
				found_FC("收藏");
				if(mm.getObjectByText("我知道了") != null){
					mm.pressBack();
				}
				
				if(fav.isSelected()){
					mm.log("收藏歌曲成功");
				}else{
					mm.log("收藏歌曲失败");
				}
				if(mm.getObjectByText("我知道了") != null){
					mm.pressBack();
				}
				
				mm.log("Step 7 : End.");
				mm.waitFor(1);
				mm.pressBack(3);
			
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

