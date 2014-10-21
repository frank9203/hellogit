package com.miui.player;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;


public class Test_0003_BackgroundPlay extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private UiDevice mDevice= null; 
	public static int device_type;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		mDevice = UiDevice.getInstance();
		device_type = get_Model();
	}	
	
	public void test_Background_Play() throws UiObjectNotFoundException {
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
		
		mm.log("Step 2 : Click '一首歌'.");
		mm.getObjectByText("歌曲", "android.widget.TextView").click();
		found_FC("点击歌曲");
		int i = 0;
		while(( i = mm.getObjectByClass("android.widget.ListView").getChildCount()) <= 1)
			mm.waitFor(2);
		UiObject play = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
  		      .getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));		
		play.click();
		found_FC("播放音乐");
		mm.waitFor(1);
		
		mm.log("Step 3 : Quit from music.");
		mm.pressBack(2);
		mm.waitFor(1);
		
		mm.log("Step 4 : Background play music");
		try{
			mDevice.sleep();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		mm.log("Step 5 : Wake up.");
		mm.wakeUp();
		
		mm.log("Step 6 : Click 'pause'.");
		if(device_type==2){
			mm.click(360, 680, 5);
		}else if(device_type==3){
			mm.click(540, 1240, 5);
		}
		found_FC("后台暂停");
		mm.waitFor(1);
		
		mm.log("Step 7 : Click 'Last'.");
		if(device_type==2){
			mm.click(150, 900);
		}else if(device_type==3){
			mm.click(250, 1460);
		}
		found_FC("后台上一曲");
		mm.waitFor(1);
		
		mm.log("Step 8 : Click 'Next'.");
		if(device_type==2){
			mm.click(570, 900, 2);
		}else if(device_type==3){
			mm.click(830, 1460, 2);
		}
		found_FC("后台下一曲");
			
		mm.log("Step 9: End.");
		mm.waitFor(1);
		if(device_type==2){
			mm.move(360, 1000, 360, 1280);
		}else if(device_type==3){
			mm.move(540, 1460, 540, 1900);
		}
		mm.pressHome();
		
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
