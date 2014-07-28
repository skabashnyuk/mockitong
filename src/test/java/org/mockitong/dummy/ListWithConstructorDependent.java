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

import java.util.List;

public class ListWithConstructorDependent
{

   private final List<String> list;

   private boolean constructorInjectionUsed = false;

   /**
    * @param list
    */
   public ListWithConstructorDependent(List<String> list)
   {
      super();
      this.list = list;
      this.constructorInjectionUsed = true;
   }

   /**
    * @return the constructorInjectionUsed
    */
   public boolean isConstructorInjectionUsed()
   {
      return constructorInjectionUsed;
   }

   public List<String> getList()
   {
      return list;
   }
}