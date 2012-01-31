package org.mockitong;

import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Analog of JUnit44RunnerTest
 * 
 */
@Listeners(MockitoTestNGInitializer.class)
public class MockitoTestNGInitializerTest
{

   @InjectMocks
   private final ListDependent listDependent = new ListDependent();

   @Mock
   private List<String> list;

   @Test
   public void shouldInitMocksUsingRunner()
   {
      list.add("test");
      verify(list).add("test");
   }

   @Test
   public void shouldInjectMocksUsingRunner()
   {
      assertNotNull(list);
      assertSame(list, listDependent.getList());
   }

   class ListDependent
   {
      private List<String> list;

      public List<String> getList()
      {
         return list;
      }
   }
}
