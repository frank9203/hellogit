package com.miui.browser;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_OpenTheWeb extends UiAutomatorTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this.getClass());
        cc = new Checker();
    }
    
    public void test_Settings_0001_OpenWLAN() throws UiObjectNotFoundException{
        mm.log("Step 1 : Connect with WLAN.");
        mm.pressHome();
        cc.assertTrue("Connect with WLAN", connectWLAN("Public", "miguest#1"));
        mm.pressBack(3);
        
        mm.log("Step 2 : Open the web 'www.baidu.com'.");
        mm.openWebPage("http://www.baidu.com");
        cc.assertUiObejctExist(mm.getObjectByText("百度一下"));
        
        mm.log("Step 3 : Quit from browser app.");
        mm.pressBack(3);
    }
    
    private boolean connectWLAN (String wlanName, String password) throws UiObjectNotFoundException{
        boolean connected = false;
        
        mm.launchActivity("com.android.settings/.MiuiSettings");
        mm.clickOnText("WLAN");
            
        //Turn on WLAN.
        if(!mm.isChecked(0)){
            mm.clickOnCheckBox(0);
            mm.waitFor(3);
        }
        
        //Connect with WLAN which named wlanName.
        if( !mm.isTextExist("已连接") ){
            mm.clickOnText(wlanName);
            mm.waitFor(2);
            mm.shell("input text " + password);
            mm.waitFor(3);
            mm.pressBack();
            mm.clickOnButton("连接");
            mm.waitFor(6);
        }
        
        if( mm.isTextExist("已连接") ){
            connected = true;
        }
                
        return connected;
    }

    @Override
    protected void tearDown() throws Exception {
        mm.finish();
        super.tearDown();
    }

}
