/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.mockitong;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.mockitong.dummy.ListDependent;
import org.mockitong.dummy.ListWithConstructorDependent;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Analog of JUnit44RunnerTest
 * 
 */
@Listeners(MockitoTestNGListener.class)
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
