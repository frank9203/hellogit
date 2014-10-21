package com.miui.player;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0004_Search extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;
	private static int connection;
	public static int device_type;
	
	@Override
	protected void setUp() throws Exception {
		connection = 0;
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		device_type = get_Model();
	}	

	public void test_Music_Search() throws UiObjectNotFoundException {
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
		
		mm.log("Step 2 : Click '在线'.");
		mm.getObjectByText("在线", "android.widget.TextView").click();
		found_FC("点击在线");
		mm.waitFor(1);
		
		mm.log("Step 3 : Click '精品推荐'.");
		mm.clickOnText("精品推荐");
		found_FC("点击精品推荐");
		mm.waitFor(1);
		
		mm.log("Step 4 : Click 'Search'.");
		judge_Connection();
		if (connection == 5){
			mm.saveScreenshot("Errorshot");
			mm.log("Error:no network.");				
		}
		else{
			mm.getObjectByText("搜索在线歌曲", "android.widget.TextView").click();
			found_FC("点击搜索在线歌曲框");
			mm.waitFor(1);
			if(device_type==2){
				mm.click(485, 1076);
				mm.click(485, 994);
				mm.waitFor(1);
				mm.click(710,1270);
			}else if(device_type == 3){
				mm.click(730, 1720);
				mm.click(730, 1450);
				mm.waitFor(1);
				mm.click(1000, 1800);
			}
			found_FC("在线搜索歌曲");
			mm.waitFor(1);
			judge_Connection();
			if (connection == 5){
				mm.saveScreenshot("Errorshot");
				mm.log("Error:no network.");				
			}else{
				mm.getObjectByClass("android.widget.ListView").getChild(new UiSelector().className("android.widget.RelativeLayout").index(1)).click();
				found_FC("播放搜索的歌曲");
				mm.waitFor(1);
				
				mm.log("Step 5 : End.");
				mm.pressBack(4);
			}
		}
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
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}
