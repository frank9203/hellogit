package com.miui.shell;

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
	    	mm.log("Step 1 : Connect with WLAN.");
			mm.pressBack(3);
			mm.pressHome();
			cc.assertTrue("Connect to WLAN", connectWLAN("Public","miguest#1"));
			mm.pressBack(3);
			
			mm.log("Step 2 : Launch theme.");
			mm.clickOnText("主题风格");
			mm.waitFor(3);
			
			mm.log("Step 3 : Check selected.");
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
			}
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
		}
	    

		private boolean CheckSelected(String TabClass, int i) throws UiObjectNotFoundException {
			boolean checkSelected = false;
			if (mm.getObjectByClass(TabClass, i).isSelected() == true) {
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

