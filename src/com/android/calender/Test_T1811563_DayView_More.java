package com.android.calendar;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;
///writer:wangweiwei1
//Created:20150208_M2W_Dev_5.2.5_4.4
//Compiled successfully:20150512_3W_Dev_5.2.11


public class Test_T1811563_DayView_More extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1811563_day_wiew_more() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		mm.log("Preconditions: In Months view.");
		mm.launchActivity("com.android.calendar/com.android.calendar.AllInOneActivity");
		mm.waitFor(3);
		stepOneToThree();
		
		mm.log("Preconditions: In Days view.");
		cc.assertUiObejctExist(mm.getObjectByText("日", "android.widget.Button"));
		mm.getObjectByText("日", "android.widget.Button").click();
		mm.waitFor(3);
		stepOneToThree();
	}

	private void stepOneToThree() throws UiObjectNotFoundException{
		//Step 1 to step 3.
		mm.log("Step 1: Press Menu key.");
		mm.log("Expected: Display recent events view.");
		mm.pressMenu();
		mm.waitFor(3);
		UiObject clear_icon = new UiObject(new UiSelector()
    				.className("android.widget.ImageView").index(0)
    				.resourceId("com.android.systemui:id/clearButton"));
		cc.assertTrue("clearButton doesn't exist.", clear_icon.exists());
		cc.assertTextExist("可用");
		mm.pressBack();

		mm.log("Step 2: Press More button.");
		mm.log("Expected: Menu pops up.");
		cc.assertUiObejctExist(mm.getObjectByText("更多", "android.widget.Button"));
		mm.getObjectByText("更多", "android.widget.Button").click();
		mm.waitFor(3);
		cc.assertUiObejctExist(mm.getObjectByText("跳转到指定日期", "android.widget.TextView"));
		cc.assertUiObejctExist(mm.getObjectByText("日程", "android.widget.TextView"));
		cc.assertUiObejctExist(mm.getObjectByText("设置", "android.widget.TextView"));

		mm.log("Step 3: Select 'Jump to date'/'Agenda'/'Settings'");
		mm.log("Expected: Behaviors are correctly.");
		mm.getObjectByText("跳转到指定日期", "android.widget.TextView").click();
		mm.waitFor(3);
		UiObject nextD_button = new UiObject(new UiSelector()
    				.className("android.widget.Button").instance(5));
		String nextD_text = nextD_button.getText();
		nextD_button.click();
		mm.log(nextD_text);
		cc.assertUiObejctExist(mm.getObjectByText("确定", "android.widget.Button"));
		mm.getObjectByText("确定", "android.widget.Button").click();
		mm.waitFor(3);
		mm.getObjectByText("更多", "android.widget.Button").click();
		mm.waitFor(2);
		cc.assertUiObejctExist(mm.getObjectByText("跳转到指定日期", "android.widget.TextView"));
		mm.getObjectByText("跳转到指定日期", "android.widget.TextView").click();
		mm.waitFor(3);
		UiObject currentD_button = new UiObject(new UiSelector()
    				.className("android.widget.EditText").instance(2));
		String currentD_text = currentD_button.getText();
		mm.log(nextD_text);
		mm.log(currentD_text); 
		cc.assertTrue("Switch to some day successfully.", nextD_text.equalsIgnoreCase(currentD_text));

		mm.getObjectByText("确定", "android.widget.Button").click();
		mm.waitFor(2);
		mm.getObjectByText("更多", "android.widget.Button").click();
		mm.waitFor(3);
		mm.getObjectByText("日程", "android.widget.TextView").click();
		mm.waitFor(3);
		cc.assertUiObejctExist(mm.getObjectByTextContains("点击查看"));
		cc.assertUiObejctExist(mm.getObjectByTextContains("之前的活动"));
		cc.assertUiObejctExist(mm.getObjectByTextContains("之后的活动"));
		mm.pressBack();
		mm.getObjectByText("更多", "android.widget.Button").click();
		mm.waitFor(3);
		mm.getObjectByText("设置", "android.widget.TextView").click();
		mm.waitFor(3);
		cc.assertTrue("Calender Settings view isn't opened.", CheckCurrentView("com.android.calendar/com.android.calendar.CalendarSettingsActivity"));
		mm.pressBack();
	}

	private boolean CheckCurrentView(String activityName) throws UiObjectNotFoundException{
		boolean enterView = false;
		String trueName = mm.getCurrentActivityName();
		if(trueName.equalsIgnoreCase(activityName)){
			enterView = true;
		}
		return enterView;
	}

	@Override
	protected void tearDown() throws Exception {
		mm.pressHome();
		mm.waitFor(3);
		UiObject clear_icon = new UiObject(new UiSelector()
    		.className("android.widget.ImageView").index(0)
    		.resourceId("com.android.systemui:id/clearButton"));
		for (int i=0; i<2; i++){
			mm.pressMenu();
			mm.waitFor(3);
			clear_icon.click();
			mm.waitFor(2);
		}
		mm.finish();
		super.tearDown();
	}
}