package com.miui.shell;
import java.security.PublicKey;

import com.android.uiautomator.core.UiObjectNotFoundException;	
import com. android.uiautomator .testrunner. UiAutomatorTestCase;
import com. miui.marmot .lib. Checker;
import com. miui.marmot .lib. Marmot;


public class Test_0006_FeaturedNewList extends UiAutomatorTestCase {
	public Marmot mm;
	public Checker cc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mm = new Marmot(this.getClass());
		cc = new Checker();
}

   public void name() throws UiObjectNotFoundException {
	   
	   
	
}
   @Override
   protected void tearDown() throws Exception {
         mm .finish() ;
          super. tearDown();
   }
}