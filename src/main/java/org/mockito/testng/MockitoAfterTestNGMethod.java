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
package org.mockito.testng;

import static org.mockito.internal.util.reflection.Fields.annotatedBy;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.reflection.Fields;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;

public class MockitoAfterTestNGMethod {

    public void applyFor(IInvokedMethod method, ITestResult testResult) {
        Mockito.validateMockitoUsage();

        if (method.isTestMethod()) {
            resetMocks(testResult.getInstance());
        }
    }


    private void resetMocks(Object instance) {
        Mockito.reset(instanceMocksOf(instance).toArray());
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    private Collection<Object> instanceMocksOf(Object instance) {
        return Fields.allDeclaredFieldsOf(instance)
                                            .filter(annotatedBy(Mock.class,
                                                                Spy.class))
                                            .notNull()
                                            .assignedValues();
    }

    private Set<Object> instanceMocksIn(Object instance, Class<?> clazz) {
        Set<Object> instanceMocks = new HashSet<Object>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Mock.class) || declaredField.isAnnotationPresent(Spy.class)) {
                declaredField.setAccessible(true);
                try {
                    Object fieldValue = declaredField.get(instance);
                    if (fieldValue != null) {
                        instanceMocks.add(fieldValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new MockitoException("Could not access field " + declaredField.getName());
                }
            }
        }
        return instanceMocks;
    }


}
