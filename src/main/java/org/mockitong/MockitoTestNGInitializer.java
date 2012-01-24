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

import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.IConfigurationListener2;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

/**
 * Mockito annotation initializer for TestNG test.
 * 
 * To enable annotation initialization you should add MockitoTestNGInitializer
 * as a listener to you TestNG test. </p>
 * 
 * <code><b>@Listeners(MockitoTestNGInitializer.class)</b><br>
 * public class MyTestClass {<br>
 * 
 * }<code>
 */
public class MockitoTestNGInitializer implements IInvokedMethodListener, IConfigurationListener2
{

   /**
    * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
   {

   }

   /**
    * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void afterInvocation(IInvokedMethod method, ITestResult testResult)
   {
      if (method.isTestMethod())
      {
         Mockito.validateMockitoUsage();
         Object[] instances = method.getTestMethod().getTestClass().getInstances(false);
         for (Object object : instances)
         {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields)
            {
               if (field.isAnnotationPresent(Mock.class) || field.isAnnotationPresent(InjectMocks.class)
                  || field.isAnnotationPresent(Spy.class) || field.isAnnotationPresent(Captor.class))
               {
                  try
                  {
                     field.setAccessible(true);
                     field.set(object, null);
                  }
                  catch (IllegalAccessException e)
                  {
                     throw new RuntimeException(e.getLocalizedMessage(), e);
                  }
               }
            }
         }
      }
   }

   /**
    * @see org.testng.IConfigurationListener#onConfigurationSuccess(org.testng.ITestResult)
    */
   @Override
   public void onConfigurationSuccess(ITestResult itr)
   {
   }

   /**
    * @see org.testng.IConfigurationListener#onConfigurationFailure(org.testng.ITestResult)
    */

   @Override
   public void onConfigurationFailure(ITestResult itr)
   {
   }

   /**
    * @see org.testng.IConfigurationListener#onConfigurationSkip(org.testng.ITestResult)
    */
   @Override
   public void onConfigurationSkip(ITestResult itr)
   {
   }

   /**
    * @see org.testng.IConfigurationListener2#beforeConfiguration(org.testng.ITestResult)
    */
   @Override
   public void beforeConfiguration(ITestResult tr)
   {
      MockitoAnnotations.initMocks(tr.getInstance());
   }

}
