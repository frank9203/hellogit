package com.miui.shell;
import android.os.RemoteException;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;

public class Test_0024_LockScreenWallpaperSelectOther extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0154_LockScreenWallpaperSelectOther() throws UiObjectNotFoundException{
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check LockScreenWallpaper.");
		mm.getObjectByText("锁屏壁纸", "android.widget.TextView").click();

		mm.waitFor(2);
		mm.click(60, 127);
		mm.waitFor(1);
		mm.getObjectByText("通过其他程序选择", "android.widget.Button").click();
		mm.waitFor(1);
		mm.getObjectByText("图库", "android.widget.TextView").click();
		mm.waitFor(1);
		mm.click(540, 97);
		mm.waitFor(1);
		mm.click(169,602);
		mm.waitFor(1);
		mm.getObjectByText("应用", "android.widget.Button").click();
	
		mm.pressBack(4);
		try {
			getUiDevice().sleep();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mm.waitFor(2);
		try {
			getUiDevice().wakeUp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean CheckSelectedByText(String textName, String classNmae) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, classNmae).isSelected() == true){
			checkSelected = true;
		}
		return checkSelected;
	}
	@Override 
	protected void tearDown() throws Exception{
		mm.finish();
		super.tearDown();
	}

}
