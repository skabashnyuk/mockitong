/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.mockitong;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

import org.mockito.Mock;
import org.mockitong.dummy.ObjectManipulator;
import org.mockitong.dummy.RealWorldClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 
 */
@Listeners(MockitoTestNGInitializer.class)
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