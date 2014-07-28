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
public class ObjectManipulator
{
   public void mainpulate(int year) throws IOException
   {
      int limit = getLimit();
      if (year > limit)
      {
         throw new IOException("Out of limit" + limit);
      }
   }

   public int getLimit()
   {
      return 2020;
   }
}
