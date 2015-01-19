package com.miui.theme;
//hu

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0013_FeaturedUICheck extends UiAutomatorTestCase {
	 public Marmot mm;
	    public Checker cc;

	    @Override
	    protected void setUp() throws Exception {
	          super. setUp();
	          mm = new Marmot( this. getClass());
	          cc = new Checker() ;
	    }
	    public void test_0019_FeaturedUiCheck1() throws UiObjectNotFoundException{
	    	
	    	mm.log("Step 1 : Open Unlock");

			int DisplayWidth ;
			DisplayWidth = this.getUiDevice().getDisplayWidth() ;
			if(!mm.isScreenOn()){   
				mm.wakeUp();
				mm.waitFor(1);
				if(DisplayWidth <= 480){
					mm.move(300, 700, 300, 200);
				}
				if(DisplayWidth <=540){
					mm.move(300, 900, 300, 200);
				}
				if(DisplayWidth <=720){
					mm.move(300, 1000, 300, 500);
				}
				if(DisplayWidth <=1080){
					mm.move(500, 1700, 500, 500);
				}else{
					mm.move(700, 2000, 700, 500);
				}
			//	mm.move(500, 1500, 500, 700);
				mm.waitFor(2);
			}	
	    	
	    	/*mm.log("Step 2 : Connect with WLAN.");
			mm.pressBack(3);
			mm.pressHome();
	//		cc.assertTrue("Connect to WLAN", connectWLAN("Public","miguest#1"));
*/			//mm.pressBack(3);
			
			mm.log("Step 2 : Launch theme.");
			mm.pressHome();
			mm.clickOnText("主题风格");
			mm.waitFor(3);
	/*		mm.log("Step 3 : Check selected.");
			mm.waitFor(2);*/
			mm.saveScreenshot("FeaturedUICheck.png");
			
			//活动改变找不到图片了：
			
			/*for(int i=0;i<4;i++){
				UiObject View = new UiObject(new UiSelector().className("android.widget.FrameLayout").index(1))
				.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
				.getChild(new UiSelector().className("android.widget.ImageView").index(i));
				View.click() ;
				mm.waitFor(3);
				mm.pressBack();
			}*/
			
			
			
			mm.pressBack(3);
			
		
			
			/*
			mm.getObjectByClass("android.app.ActionBar$Tab", 0).click();
			cc.assertTrue("CheckSelected", CheckSelected("android.app.ActionBar$Tab",0));
			
			UiScrollable collectionObject = new UiScrollable(new UiSelector().scrollable(true));
			if(collectionObject.exists()){
				UiObject scrollableObject = collectionObject.getChild(new UiSelector().className("android.widget.ListView")).getChild(new UiSelector().className("android.widget.LinearLayout").instance(0));
				int count = scrollableObject.getChild(new UiSelector().className("android.view.View")).getChildCount();

				cc.assertTrue("Check Up Location", collectionObject.scrollIntoView(new UiSelector().className("android.view.View")));
				cc.assertTrue("Scroll",count == 4);

			}
			else {
				mm.pressBack(1);
				cc.assertUiObejctExist(collectionObject);		
			}*/
	    }
    
		/*private boolean connectWLAN(String wlanName, String password) throws UiObjectNotFoundException{
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
				mm.waitFor(6);
				if(mm.getObjectByText("连接", "android.widget.Button").isEnabled()){
					mm.getObjectByText("连接", "android.widget.Button").click();
				}
				mm.waitFor(15);
			}
			if(mm.isTextExist("已连接")){
				checkSelected = true;
			}
			return checkSelected;
		}*/
	    
/*
		private boolean CheckSelected(String TabClass, int i) throws UiObjectNotFoundException {
			boolean checkSelected = false;
			if (mm.getObjectByClass(TabClass, i).isSelected() == true) {
				checkSelected = true;		
			
			}
			
			return checkSelected;
		} */
	   
	    @Override
	    protected void tearDown() throws Exception {
	          mm .finish() ;
	           super. tearDown();
	    }

}

