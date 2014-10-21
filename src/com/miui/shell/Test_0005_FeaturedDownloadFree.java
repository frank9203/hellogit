package com.miui.shell;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

import com. android.uiautomator .testrunner. UiAutomatorTestCase;
import com. miui.marmot .lib. Checker;
import com. miui.marmot .lib. Marmot;

public class Test_0005_FeaturedDownloadFree extends UiAutomatorTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
          super. setUp();
          mm = new Marmot( this. getClass());
          cc = new Checker() ;
    }
    public void test_0034_FeaturedDownloadFree() throws UiObjectNotFoundException{
		mm.log("Step 1 : Connect with WLAN.");
		mm.pressHome();
		cc.assertTrue("Connect with WLAN", connectWLAN("Public", "miguest#1"));
		mm.pressBack(3);
		
		mm.log("Step 2 : lanuch theme.");
		mm.clickOnText("主题风格");
		mm.waitFor(3);
//		mm.getObjectByClass("android.app.ActionBar$Tab", 1).click();
		mm.getObjectByText("排行", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("排行", "android.widget.TextView"));
		mm.getObjectByText("免费", "android.widget.TextView").click();
		cc.assertTrue("CheckFreePage",CheckSelectedByText("免费","android.widget.TextView"));
		mm.waitFor(2);
		
		mm.log("Step 3 : Download Page.");
		mm.click(188, 716);
		mm.waitFor(2);
		String text = mm.getObjectByClass("android.widget.ScrollView").getChild(new UiSelector().className("android.widget.TextView").instance(0)).getText();
		if(mm.isTextExist("下载")){
			mm.getObjectByText("下载", "android.widget.Button").click();
			for(int i = 0; i < 4; i++ ){
				mm.waitFor(60);
				if(mm.getObjectByText("应用").isEnabled() == true)
					cc.assertTrue("DownloadFinished", true);
					break;				
			}
		}
		mm.pressBack(1);
		mm.waitFor(2);
		
		mm.log("Step 4 : Show In My List.");	
//		mm.getObjectByClass("android.app.ActionBar$Tab", 3).click();
		mm.click(60, 127);
		mm.waitFor(2);
		cc.assertTrue("Show In List", mm.isTextExist(text));
		mm.pressHome();
	}
	private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.clickOnText("WLAN");
		
		if(!mm.isChecked(0)){
			mm.clickOnCheckBox(0);
			mm.waitFor(3);
		}
		if(!mm.isTextExist("已连接")){
			UiScrollable collectionObject  = new UiScrollable(new UiSelector().scrollable(true));
			if(collectionObject.exists()){
				UiObject scrollableObject = collectionObject.getChildByText(new UiSelector().className("android.widget.CheckedTextView"), wlanName);
				scrollableObject.click();
			}
			else{
				mm.clickOnText(wlanName);
			}
			mm.waitFor(2);
			mm.pressBack();
			mm.shell("input text " + password);
			mm.waitFor(4);
			if(mm.getObjectByText("连接", "android.widget.Button").isEnabled()){
				mm.getObjectByText("连接", "android.widget.Button").click();
			}
			mm.waitFor(10);
		}
		if(mm.isTextExist("已连接")){
			checkSelected = true;
		}
		return checkSelected;
	} 
      
   
	private boolean CheckSelectedByText(String textName, String classNmae) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, classNmae).isSelected() == true){
			checkSelected = true;
		}
		return checkSelected;
	}
   
    @Override
    protected void tearDown() throws Exception {
          mm .finish() ;
           super. tearDown();
    }

}
