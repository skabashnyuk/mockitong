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

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.mockitong.dummy.ObjectManipulator;
import org.mockitong.dummy.RealWorldClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 
 */
@Listeners(MockitoTestNGListener.class)
public class ConfigurationMethodTest
{
   @Mock
   private ObjectManipulator objectManipulator;

   private RealWorldClass realWorldClass;

   @BeforeMethod
   public void prepareTestObject()
   {
      when(objectManipulator.getLimit()).thenReturn(1999);
      realWorldClass = new RealWorldClass(objectManipulator, 2012);
   }

   @Test
   public void shouldCreateMockBeforeConfigurationMethod() throws Exception
   {
      assertNotNull(realWorldClass.getManipulator());
      assertSame(realWorldClass.getManipulator(), objectManipulator);
   }

   @Test(expectedExceptions = IOException.class)
   public void shouldBeAbleToConfigureMock() throws IOException
   {
      doThrow(IOException.class).when(objectManipulator).mainpulate(eq(2012));
      realWorldClass.realWorldMethod();
   }

   @Test(dependsOnMethods = "shouldBeAbleToConfigureMock")
   public void shouldResetMockBetweenTests() throws IOException
   {
      realWorldClass.realWorldMethod();
   }

   @Test
   public void shouldBeAbleToConfigureMockInTestngConfigurationMethod() throws IOException
   {
      assertEquals(objectManipulator.getLimit(), 1999);
   }
}