package com.miui.player;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0005_EditMode extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	public static int device_type;
	private UiDevice mDevice= null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		device_type = get_Model();
		mDevice = UiDevice.getInstance();
	}	
	
	public void test_Edit_Mode() throws UiObjectNotFoundException {
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
		
		mm.log("Step 2 : Press menu on the home page.");
		mm.getObjectByText("歌曲", "android.widget.TextView").click();
		found_FC("点击歌曲");
		mm.pressMenu();
		mm.clickOnText("连网自动校准歌曲信息(ID3)");
		found_FC("点击连网自动校准歌曲信息");
		mm.pressMenu();
		mm.clickOnText("添加到播放列表");
		found_FC("点击添加到播放列表");
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("刷新");
		found_FC("点击刷新");
		mm.pressMenu();
		mm.clickOnText("搜索");
		found_FC("点击搜索");
		mm.waitFor(1);
		if(device_type==2){
			mm.click(80, 1000);
			mm.click(715,1275);
		}else if(device_type == 3){
			mm.click(100, 1450);
			mm.click(1050, 1900);
		}
		found_FC("搜索歌曲");
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("超高品质音乐(包月服务)");
		found_FC("点击超高品质音乐(包月服务)");
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("发现KTV");
		found_FC("点击发现KTV");
		mm.waitFor(1);
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("进入睡眠模式");
		found_FC("点击进入睡眠模式");
		mm.pressBack();
		mm.pressMenu();
		mm.clickOnText("设置");
		found_FC("点击设置");
		mm.pressBack();
		mm.waitFor(1);
		
		mm.log("Step 3 : Edit mode.");
		mm.getObjectByText("歌曲", "android.widget.TextView").click();
		found_FC("点击歌曲");
		int i = 0;
		while(( i = mm.getObjectByClass("android.widget.ListView").getChildCount()) <= 2)
			mm.waitFor(2);
		if(device_type==2){
			mm.getObjectByClass("android.widget.ListView").longClick();
		}else if(device_type == 3){
			mDevice.swipe(540, 1400, 540, 1400, 400);
		}
		found_FC("进入编辑模式");
		mm.waitFor(1);
		
		mm.log("Step 4: Click 'more'.");
		mm.clickOnButton("更多");
		found_FC("点击更多");
		mm.pressBack();
		
		mm.log("Step 5 : Delete a song.");
		mm.clickOnButton("删除");
		found_FC("点击删除");
		mm.waitFor(1);
		mm.clickOnButton("确定");
		found_FC("删除歌曲");
		mm.waitFor(1);
		
		mm.log("Step 6 : End.");
		mm.pressBack(1);
		
		
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
