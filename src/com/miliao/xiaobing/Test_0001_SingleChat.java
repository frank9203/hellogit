package com.miliao.xiaobing;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;
import junit.framework.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.miliao.common.Common;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_0001_SingleChat extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	private int count;
	private UiObject chatList;
	private int countPre;
	private Common common;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
		common = new Common();
		mm.log("Env Step 1: clear cache of mitalk");
		goHome();
		mm.clickOnText("应用");
		mm.waitFor(1);
		mm.clickOnText("已下载");
		mm.waitFor(1);
		mm.clickOnText("米聊");
		mm.waitFor(1);
		mm.move(560, 1300, 560, 448,2);
		mm.clickOnButton("清除数据");
		UiObject confirmButton = null;
		if((confirmButton = mm.getObjectByText("确定","android.widget.Button"))!=null){
			confirmButton.click();
		}
		goHome();
	}

	public void test_0001_SingleChat() throws UiObjectNotFoundException {
		mm.log("Step 1: Launch Mitalk and start to chat with xiao bing");
		mm.clickOnText("米聊");
		//skip welcome page
		mm.move(884, 964, 118, 964, 5);
		mm.clickOnText("立即体验");
		mm.waitFor(1);
		//login
		mm.clickOnText("登录");
		mm.waitFor(1);
		UiObject account = mm.getObjectByText("请输入小米帐号/手机号/邮箱", "android.widget.EditText");
        account.clearTextField();
        account.setText("71983298");
        mm.waitFor(1);
        mm.click(589, 425,2);
        mm.waitFor(1);
        mm.shell("input text 123456");
        mm.waitFor(1);
        mm.clickOnText("确定");
		int loop = 0;
		while(mm.getObjectByText("圈子")==null && loop<10){
			mm.waitFor(3);
		}
		mm.clickOnText("圈子");
		mm.waitFor(1);
		mm.clickOnText("通讯录");
		mm.waitFor(1);
		mm.clickOnText("小小(小冰)");
		mm.waitFor(1);
		mm.click(1005, 135);
		mm.waitFor(1);
		mm.clickOnText("删除对话记录");
		mm.waitFor(1);
		mm.clickOnText("确定");
		mm.waitFor(2);
		UiObject input = new UiObject(new UiSelector().className("android.widget.EditText").text("输入消息"));
		mm.pressBack();
		mm.pressBack();
		mm.waitFor(1);
		mm.clickOnText("通讯录");
		mm.waitFor(1);
		mm.clickOnText("小小(小冰)");
		mm.waitFor(1);
		input.setText("test");
		mm.click(1000, 1844);
		mm.waitFor(5);
		mm.click(1005, 135);
		mm.waitFor(1);
		mm.clickOnText("删除对话记录");
		mm.waitFor(1);
		mm.clickOnText("确定");
		mm.waitFor(2);
		input = new UiObject(new UiSelector().className("android.widget.EditText").text("输入消息"));
		
		mm.log("Step 2: how tall is Yao");
		input.setText(Utf7ImeHelper.e("姚明的身高"));
		mm.click(1000, 1844);
		mm.waitFor(10);
		cc.assertTextExist("226cm");
		chatList = new UiObject(new UiSelector().className("android.widget.ListView"));
		count = chatList.getChildCount();
		mm.log("children count: "+Integer.toString(count));
		
		mm.log("Step 3: joke time");
		input.setText(Utf7ImeHelper.e("讲个笑话"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		chatList = new UiObject(new UiSelector().className("android.widget.ListView"));
		countPre = count;
		count = chatList.getChildCount();
		mm.log("children count: "+Integer.toString(count));
		cc.assertTrue("通过气泡数量检查小冰是否有应答", count==(countPre+2));
		
		mm.log("Step 4: xiaobing info --- change name");
		input.setText(Utf7ImeHelper.e("改名字"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		common.clickLink(mm);
		if(new UiObject(new UiSelector().text("打开网页")).exists()){
			mm.clickOnText("打开网页");
			mm.waitFor(5);
		}
		cc.assertTrue("Check whether web view is shown", new UiObject(new UiSelector().className("android.webkit.WebView")).exists());
		mm.pressBack();
		
		mm.log("Step 5: weather in Beijing");
		input.setText(Utf7ImeHelper.e("北京天气"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("温度");
		
		mm.log("Step 6: pk18610069340");
		input.setText(Utf7ImeHelper.e("pk18610069340"));
		mm.click(1000, 1844);
		mm.waitFor(30);
		if((new UiObject(new UiSelector().textContains("胜")).exists())
				||(new UiObject(new UiSelector().textContains("赢")).exists())
				||(new UiObject(new UiSelector().textContains("败")).exists())
				||(new UiObject(new UiSelector().textContains("输")).exists())
				||(new UiObject(new UiSelector().textContains("练手")).exists())){
			mm.log("PASS, pk Text exists.");
		}
		else{
			mm.log("FAIL, pk Text does not exist.");
			Assert.assertTrue(false);
		}
		
		mm.log("Step 7: search my boss");
		input.setText(Utf7ImeHelper.e("搜索 雷军"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		common.clickLink(mm);
		if(new UiObject(new UiSelector().text("打开网页")).exists()){
			mm.clickOnText("打开网页");
			mm.waitFor(5);
		}
		cc.assertTrue("Check whether web view is shown", new UiObject(new UiSelector().className("android.webkit.WebView")).exists());
		mm.pressBack();
		
		mm.log("Step 8: listen and learn");
		input.setText(Utf7ImeHelper.e("问：你的朋友谁最二 答：当然是小王啊"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		input.setText(Utf7ImeHelper.e("你的朋友谁最二"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTrue("Check whether the answer is correct", (mm.getObjectByText("当然是小王啊。")!=null));
		
		mm.log("Step 9: star pk");
		input.setText(Utf7ImeHelper.e("韩寒和小四谁更火"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("http");
		
		mm.log("Step 10: freeze time");
		input.setText(Utf7ImeHelper.e("多出去玩一下放松心情。1天后提醒我"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		if(new UiObject(new UiSelector().textContains("#冰冻时间#好的，我记下了")).exists()
				||new UiObject(new UiSelector().textContains("每周")).exists()){
			mm.log("Text exists");
		}else{
			mm.log("Text not exist");
			Assert.assertTrue(false);
		}
		
		mm.log("Step 12: dog recognization");
		input.setText(Utf7ImeHelper.e("小冰识狗"));
		mm.click(1000, 1844);
		mm.waitFor(3);
		mm.click(1000, 1844);
		mm.clickOnText("图片");
		mm.clickOnText("从相册选择");
		mm.clickOnText("图库列表");
		mm.clickOnText("bpic(1)");
		mm.click(308, 651);
		mm.clickOnText("发送");
		mm.waitFor(10);
		cc.assertTextExist("秋田犬");
		
		mm.log("Step 11: xiaobing info --- change photo");
		input.setText(Utf7ImeHelper.e("改头像"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		common.clickLink(mm);
		if(new UiObject(new UiSelector().text("打开网页")).exists()){
			mm.clickOnText("打开网页");
			mm.waitFor(5);
		}
		cc.assertTrue("Check whether web view is shown", new UiObject(new UiSelector().className("android.webkit.WebView")).exists());
		mm.pressBack();
		
		mm.log("Step 13: translator");
		input.setText(Utf7ImeHelper.e("开始中日同传"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		input.setText(Utf7ImeHelper.e("你好"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("初めまして");
		input.setText(Utf7ImeHelper.e("开始中韩同传"));
		mm.click(1000, 1844);
		mm.waitFor(3);
		input.setText(Utf7ImeHelper.e("你好"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("처음 뵙겠습니다");
		input.setText(Utf7ImeHelper.e("开始中英同传"));
		mm.click(1000, 1844);
		mm.waitFor(3);
		input.setText(Utf7ImeHelper.e("你好"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("how do you do");
		input.setText(Utf7ImeHelper.e("结束同传"));
		mm.click(1000, 1844);
		
		mm.log("Step 14: xiaobing info --- skill");
		input.setText(Utf7ImeHelper.e("技能"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		common.clickLink(mm);
		if(new UiObject(new UiSelector().text("打开网页")).exists()){
			mm.clickOnText("打开网页");
			mm.waitFor(5);
		}
		cc.assertTrue("Check whether web view is shown", new UiObject(new UiSelector().className("android.webkit.WebView")).exists());
		mm.pressBack();
		
		mm.log("Step 15: toliet time");
		input.setText(Utf7ImeHelper.e("马桶时间"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("糗事");
		input.setText(Utf7ImeHelper.e("继续"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("糗事");
		input.setText(Utf7ImeHelper.e("再来"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("糗事");
		input.setText(Utf7ImeHelper.e("还来"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		if((new UiObject(new UiSelector().textContains("下次")).exists())
				||(new UiObject(new UiSelector().textContains("再见")).exists())
				||(new UiObject(new UiSelector().textContains("撤")).exists())){
			mm.log("PASS, pk Text exists.");
		}
		else{
			mm.log("FAIL, pk Text does not exist.");
			Assert.assertTrue(false);
		}
		
		mm.log("Step 16: xiaobing info --- your skill");
		input.setText(Utf7ImeHelper.e("你的技能"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		common.clickLink(mm);
		if(new UiObject(new UiSelector().text("打开网页")).exists()){
			mm.clickOnText("打开网页");
			mm.waitFor(5);
		}
		cc.assertTrue("Check whether web view is shown", new UiObject(new UiSelector().className("android.webkit.WebView")).exists());
		mm.pressBack();
		
		mm.log("Step 17: asterism");
		input.setText(Utf7ImeHelper.e("处女座运势"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertTextExist("处女座今日运势");
		
		mm.log("Step 18: find picture");
		input.setText(Utf7ImeHelper.e("汽车 图片"));
		mm.click(1000, 1844);
		mm.waitFor(5);
		cc.assertUiObejctExist(new UiObject(new UiSelector().className("android.widget.ImageView")));
	}

	@Override
	protected void tearDown() throws Exception {
		mm.finish();
		super.tearDown();
	}

	public void goHome() {
		mm.pressBack(3);
		mm.pressHome();
	}

}
