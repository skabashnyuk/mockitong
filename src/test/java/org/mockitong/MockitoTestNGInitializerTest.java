package org.mockitong;

import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockitong.dummy.ListDependent;
import org.mockitong.dummy.ListWithConstructorDependent;
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
   private ListDependent listDependent;

   @InjectMocks
   private ListWithConstructorDependent listWithConstructorDependent;

   @Mock
   private List<String> list;

   @Test
   public void shouldInitMocks()
   {
      list.add("test");
      verify(list).add("test");
   }

   @Test
   public void shouldInjectMocksWithConstructor()
   {
      assertNotNull(list);
      assertSame(list, listWithConstructorDependent.getList());
      assertTrue(listWithConstructorDependent.isConstructorInjectionUsed());
   }

   @Test
   public void shouldInjectMocksInFields()
   {
      assertNotNull(list);
      assertSame(list, listDependent.getList());
   }
}