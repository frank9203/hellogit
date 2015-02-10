package com.android.deskclock;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;
import com.android.uiautomator.core.UiDevice;
///writer:wangweiwei1
//Created:20150205_M4W_Dev_5.2.5
//Compiled: Successfully_0209


public class Test_T1693292_WorldClockTab extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_1693292_worldclock_tab() throws UiObjectNotFoundException {
		if(!mm.isScreenOn()){
			mm.wakeUp();
			mm.move(360, 1000, 360, 1280);
			mm.pressHome();
		}
		mm.log("Step1: 进入时钟tab界面");
		mm.launchActivity("com.android.deskclock/.DeskClockTabActivity");
		mm.waitFor(2);
		mm.getObjectByText("时钟", "android.widget.TextView").click();
		mm.log("Step1 Expected: 图片显示清晰，无乱码重叠等现象");///暂时没有办法自动实现，需手动
		
		mm.log("Step2: 点击“添加时钟”，选择任意区域世界时钟");
		cc.assertUiObejctExist(mm.getObjectByText("添加时钟"));
		mm.getObjectByText("添加时钟", "android.widget.TextView").click();
		mm.waitFor(2);
		mm.log("Step2 Expected: 进入世界时钟列表界面，选择世界时钟显示在时钟别表界面");
		cc.assertTrue("Fail to enter worldclock view!", CheckCurrentView("com.android.deskclock/com.android.deskclock.TimezoneSearchView"));
		mm.getObjectByText("阿比让", "android.widget.TextView").click();
		mm.waitFor(4);
		cc.assertUiObejctExist(mm.getObjectByText("阿比让", "android.widget.TextView"));
		
		mm.log("Step3: 长按要删除的世界时钟");///暂时没有办法自动实现，需手动
		mm.log("Step3 Expected: 点击删除时区，确定后，删除时区不会显示在时钟列表界面");

		mm.log("Step4: 世界时钟列表上滑");
		mm.move(540,1650,540,650);
		mm.waitFor(2);
		mm.log("Step4 Expected: 时钟表盘被隐藏，时钟界面变化，图片显示清晰，无乱码重叠等现象");///时钟表盘被隐藏无法检查

		mm.log("Step5: 世界时钟列表下滑");
		mm.move(540,650,540,1650);
		mm.waitFor(2);
		mm.log("Step5 Expected:回复原来界面，图片显示清晰，无乱码重叠等现象 ");
	}
		
	///--public void found_FC(String str) throws UiObjectNotFoundException{
		///mm.waitFor(2);
		///if(mm.getObjectByText("鎶ュ憡 MIUI") != null){
			///mm.log(str+" FC.");
			///mm.clickOnButton("纭畾");
			///}
		///}
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
		mm.finish();
		super.tearDown();
	}
}
