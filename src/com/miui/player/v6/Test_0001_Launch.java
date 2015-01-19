package com.miui.player.v6;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;

public class Test_0001_Launch extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private static int connection;
	private String name;
	private UiDevice mDevice= null;
	private static final int NON_SUMMARY = 5;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();	
	}
	
	private UiObject findFavor() throws UiObjectNotFoundException {
        UiObject ralat_layput;
        ralat_layput = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
                .getChild(new UiSelector().className("android.widget.FrameLayout").index(0))
                .getChild(new UiSelector().className("android.widget.RelativeLayout").instance(0));
        
        int count;
        UiObject table_row = ralat_layput.getChild(new UiSelector().className("android.widget.TableLayout"))
                .getChild(new UiSelector().className("android.widget.TableRow"));

        count = table_row.getChildCount();

        UiObject favor;
        if (count == NON_SUMMARY) {
            favor = ralat_layput.getChild(new UiSelector().className("android.widget.ImageView").instance(3));
        } else {
            favor = ralat_layput.getChild(new UiSelector().className("android.widget.ImageView").instance(4));
        }
  

        return favor.exists() ? favor : null;
    }
	
	public void test_Launch_Music() throws UiObjectNotFoundException {
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
				
				mm.log("Step 3 : Collect an album.");
				UiObject Collector = findFavor();
				Collector.click();
				found_FC("点击收藏在线专辑");
				if(Collector.isSelected()){
					mm.waitFor(1);
					Collector.click();
					found_FC("取消收藏在线专辑");
					
					mm.log("Step 4 : Play online songs.");
					UiObject Play = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
									.getChild(new UiSelector().className("android.widget.LinearLayout").index(1));
					Play.click();
					found_FC("播放音乐");
					mm.waitFor(1);
					
					mm.log("Step 5 : Edit mode.");
					UiObject Song = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
						.getChild(new UiSelector().className("android.widget.RelativeLayout").index(2));
					Song.longClick();
					found_FC("在线专辑的编辑模式");
					mm.clickOnButton("添加到");
					mm.pressBack(1);
					mm.waitFor(1);
					mm.clickOnButton("下载");
					mm.waitFor(1);
					if(mm.getObjectByText("确定") != null){
						int count1;
						count1 = new UiObject(new UiSelector().className("android.widget.ListView").index(0)).getChildCount();
						UiObject Quality = new UiObject(new UiSelector().className("android.widget.ListView").index(0))
								.getChild(new UiSelector().className("android.widget.RelativeLayout").index(count1-1));
						Quality.click();
						mm.clickOnButton("确定");
					}
					mm.waitFor(1);
					
					mm.log("Step 7 : End.");
					mm.pressBack(2);
				}else{
					mm.log("collect not successful.");
				}
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

