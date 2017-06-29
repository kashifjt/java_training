package tutorialspoint.juint.basic;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class TestMessageUtil{
	
   String message = "Hello World";	
   MessageUtil messageUtil = new MessageUtil(message);

   @Test
   public void testPrintMessage() {
	  message = "New Word";
      assertEquals(message,messageUtil.printMessage());
   }
}