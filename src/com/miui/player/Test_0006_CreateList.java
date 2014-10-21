package com.miui.player;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0006_CreateList extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	public static int device_type;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		device_type = get_Model();
	}	
	
	public void test_Create_List() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			if(device_type==2){
				mm.move(360, 1000, 360, 1280);
			}else if(device_type == 3){
				mm.move(540, 1460, 540, 1900);
			}
			mm.pressHome();
		}
		
		mm.log("Step 1 : Launch player activity.");
		mm.pressHome();
		mm.launchActivity("com.miui.player/.ui.MusicBrowserActivity");
		found_FC("打开音乐");
		mm.waitFor(2);
		
		mm.log("Step 2 : Click 'List'.");
		mm.getObjectByText("列表", "android.widget.TextView").click();
		found_FC("点击列表");
		mm.waitFor(1);
		mm.move(360, 1200, 360, 900);
		mm.waitFor(1);
		mm.move(360, 1200, 360, 100);
		mm.waitFor(1);
		
		mm.log("Step 3 : Create a List.");
		while(mm.getObjectByText("新建播放列表") == null){
			mm.move(360, 1200, 360, 900);
			mm.move(360, 1200, 360, 100);
		}
		mm.clickOnText("新建播放列表");
		found_FC("点击新建列表");
		mm.getObjectByClass("android.widget.EditText").clearTextField();
		mm.waitFor(1);
		if(device_type==2){
			mm.click(180,343);
			mm.click(180,343);
			mm.getObjectByClass("android.widget.EditText").click();
			mm.waitFor(1);
			mm.click(360, 1050);
		}else if(device_type == 3){
			mm.click(270, 560);
			mm.click(270, 560);
			mm.getObjectByClass("android.widget.EditText").click();
			mm.waitFor(1);
			mm.click(540, 1700);
		}
		mm.clickOnButton("确定");
		found_FC("新建一个列表");
		mm.waitFor(1);
		mm.clickOnCheckBox(1);
		mm.clickOnButton("完成");
		found_FC("给列表添加一首歌");
		mm.waitFor(1);
		
		mm.log("Step 4 : Play the song in self_list.");
		mm.getObjectByText("v", "android.widget.TextView").click();
		found_FC("点击新建好的列表");
		mm.waitFor(1);
		UiObject play = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
  		      .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));		
		play.click();
		found_FC("播放列表里的歌曲");
		mm.waitFor(1);
		
		mm.log("Step 5 : Quit from music");
		mm.pressBack(3);
	}
	
	public int get_Model() throws UiObjectNotFoundException{
		String model = mm.shellReturn("getprop ro.product.name").replace("\n", "");
		mm.log(model);
	
		if(model.compareTo("mione_plus")==0){
			return 1;
		}else if(model.compareTo("taurus")==0 || model.compareTo("aries")==0){
			return 2;
		}else if(model.compareTo("cancro")==0 || model.compareTo("pisces")==0){
			return 3;
		}else{
			return 0;
		}
	}
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("报告 MIUI") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("确定");
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}
