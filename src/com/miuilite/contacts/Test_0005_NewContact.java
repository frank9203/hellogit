/**
 * 跑此条case前，请确保：1.手机未存有联系人1234abcd^_^；2.使用系统自带的输入法
 */

package com.miuilite.contacts;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0005_NewContact extends UiAutomatorTestCase{
	public Marmot mm;
	public Checker cc;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
	}

	public void test_0005_NewContact() throws UiObjectNotFoundException {
		mm.waitFor(5);
		mm.log("Step 1 : Launch.");
		mm.pressHome();
		mm.clickOnText("联系人");
		mm.waitFor(1);
		mm.clickOnButton("新建联系人");
		found_FC("Creat a New Contact");
		
		mm.log("Step 2 : Add Contact.");
		if(mm.isTextExist("请选择联系人所在的账户"))
			mm.clickOnButton("仅保存在手机中");
		
		UiObject Name = new UiObject(new UiSelector().text("姓名"));
		Name.setText("1234abcd^_^");
		
		UiObject Company = new UiObject(new UiSelector().text("公司"));
		Company.setText("XiaoMi");

		UiObject Title = new UiObject(new UiSelector().text("职位"));
		Title.setText("Testing Engineer");
		
		UiObject Tel = new UiObject(new UiSelector().text("电话"));
		Tel.setText("13436312171");
		
		mm.move(360, 360, 360, 90);
		UiObject Email = new UiObject(new UiSelector().text("邮件"));
		Email.setText("abc@xiaomi.com");
		found_FC("Edit Details");
		
		mm.log("Step 3 : Group.");
		mm.clickOnButton("群组名称");
		mm.clickOnButton("新建群组");
		UiObject EditGroupName = new UiObject(new UiSelector().className("basefx.android.widget.EditText").index(0));
		EditGroupName.setText("Test_MiuiLite1");
		mm.clickOnButton("确定");
		mm.clickOnButton("确定");
		found_FC("Creat a New ContactGroup");
		
		mm.log("Step 4 : Photo.");
		mm.getObjectByDescription("联系人照片").click();
		if(mm.isTextExist("删除照片"))
			mm.clickOnText("删除照片");
		mm.getObjectByDescription("联系人照片").click();
		mm.clickOnText("拍照");
		mm.pressBack();
		found_FC("Take Photo");
		mm.getObjectByDescription("联系人照片").click();
		mm.clickOnText("从图库中选择照片");
		mm.pressBack();
		found_FC("Choose Photo from Gallery");
		mm.getObjectByDescription("联系人照片").click();
		mm.clickOnText("从头像仓库选择新照片");
		mm.move(480, 360, 90, 360, 2);
		mm.pressBack();
		found_FC("Browse Folders");
		
		mm.clickOnButton("确定");
		mm.saveScreenshot("新建联系人");
		mm.pressBack(2);
	}	
	
	public void found_FC(String str) throws UiObjectNotFoundException{
		mm.waitFor(2);
		if (mm.getObjectByText("很抱歉，“小米系统”已停止运行。") != null){
			mm.log(str+" FC.");
			mm.clickOnButton("确定");
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}


}
