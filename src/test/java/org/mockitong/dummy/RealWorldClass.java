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
package org.mockitong.dummy;

import java.io.IOException;

/**
 *
 */
public class RealWorldClass
{
   private final ObjectManipulator manipulator;

   private final int year;

   /**
    * @param manipulator
    */
   public RealWorldClass(ObjectManipulator manipulator, int year)
   {
      super();
      this.manipulator = manipulator;
      this.year = year;
   }

   /**
    * @return the manipulator
    */
   public ObjectManipulator getManipulator()
   {
      return manipulator;
   }

   /**
    * @return the year
    */
   public int getYear()
   {
      return year;
   }

   public void realWorldMethod() throws IOException
   {
      manipulator.mainpulate(year);
   }
}
