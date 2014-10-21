package com.miui.settings;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_SetPhoneRingtone extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0001_SetPhoneRingtone() throws UiObjectNotFoundException {
		mm.log("Step 1 : Launch Settings activity.");
		mm.pressHome();
		mm.launchActivity("com.android.settings/.MiuiSettings");
		mm.waitFor(3);
		
		mm.log("Step 2 : Click 'common'.");
		mm.clickOnText("常用设置");
		
		mm.log("Step 3 : Click 'phone ringtone'.");
		mm.clickOnText("手机铃声");
					
		mm.log("Step 4 : Set 'Cuckoo' as phone ringtone");
		mm.clickOnText("我的");
		mm.waitFor(1);
		mm.getObjectByTextContain("Cuckoo").click();
		mm.waitFor(1);
		mm.clickOnButton("确定");
		
		mm.log("Step 5 : Quit from Settings activity.");
		mm.pressBack(3);
	}	
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}
}
