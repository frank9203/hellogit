package com.miui.shell;

import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.*;
public class Test_0035_IconCheckDetailsOnline extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}
	
	public void test_0181_IconCheckDetailsOnline() throws UiObjectNotFoundException{
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
		
		mm.log("Step 3 : Check My List.");
		mm.click(60, 127);//点击我的列表
		mm.waitFor(1);
		mm.click(796, 435);//点击第一个已下载图标
		mm.waitFor(1);
		UiScrollable scroll = new UiScrollable(new UiSelector());
		scroll.scrollTextIntoView("查看在线页");
		mm.getObjectByText("查看在线页", "android.widget.Button").click();
		mm.waitFor(1);
		mm.saveScreenshot("IconOnline.png");
		if(mm.isTextExist("图标详情")){
			mm.click(60, 128);//点击左上角导航键;		
			mm.saveScreenshot("IconLocal.png");
			cc.assertTrue("CheckLocalIcon", true);
		}
		mm.pressBack(5);
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
