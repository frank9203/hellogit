package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.*;
import com.miui.marmot.lib.*;

public class Test_0030_IconUiCheck extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0161_IconUiCheck() throws UiObjectNotFoundException{
		mm.log("Step 1 : launch theme.");
		mm.pressHome();
		mm.clickOnText("主题风格");
		mm.waitFor(2);
		mm.getObjectByText("分类", "android.widget.TextView").click();
		cc.assertTrue("CheckFeaturedPage", CheckSelectedByText("分类", "android.widget.TextView"));
//		mm.waitFor(1);
		mm.getObjectByText("混搭", "android.widget.Button").click();
		mm.waitFor(2);
		
		mm.log("Step 2 : Check Icon.");
		mm.getObjectByText("图标", "android.widget.TextView").click();
		mm.waitFor(2);
		UiScrollable  scroll = new UiScrollable(new UiSelector().scrollable(true));
		if(!mm.getObjectByText("精品", "android.widget.TextView").isSelected()){
			mm.getObjectByText("精品", "android.widget.TextView").click();
			mm.waitFor(2);
		}
		scroll.scrollToEnd(10);
		mm.getObjectByText("排行", "android.widget.TextView").click();
		mm.waitFor(2);
		if(!mm.getObjectByText("收费", "android.widget.TextView").isSelected()){
			mm.getObjectByText("收费", "android.widget.TextView").click();
			mm.waitFor(2);
		}
		scroll.scrollToEnd(10);
		mm.getObjectByText("免费", "android.widget.TextView").click();
		mm.waitFor(2);
		scroll.scrollToEnd(10);
		mm.click(60, 127);//点击我的列表
		mm.waitFor(2);
		mm.pressBack(3);
		mm.pressHome();
	}
	
	private boolean CheckSelectedByText(String textName,String className) throws UiObjectNotFoundException{
		boolean checkSelected = false;
		if(mm.getObjectByText(textName, className).isSelected()){
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
