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

import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.CaptorAnnotationProcessor;
import org.mockito.internal.util.reflection.Fields;
import org.mockito.internal.util.reflection.InstanceField;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;

import java.util.List;
import java.util.WeakHashMap;

public class MockitoBeforeTestNGMethod {

    private WeakHashMap<Object, Boolean> initializedInstances = new WeakHashMap<Object, Boolean>();

    /**
     * Initialize mocks.
     *
     * @param method Invoked method.
     * @param testResult TestNG Test Result
     */
    public void applyFor(IInvokedMethod method, ITestResult testResult) {
        initMocks(testResult);
        reinitCaptors(method, testResult);
    }

    private void reinitCaptors(IInvokedMethod method, ITestResult testResult) {
        if (method.isConfigurationMethod()) {
            return;
        }
        initializeCaptors(testResult.getInstance());
    }

    private void initMocks(ITestResult testResult) {
        if (alreadyInitialized(testResult.getInstance())) {
            return;
        }
        MockitoAnnotations.initMocks(testResult.getInstance());
        markAsInitialized(testResult.getInstance());
    }

    private void initializeCaptors(Object instance) {
        List<InstanceField> instanceFields = Fields.allDeclaredFieldsOf(instance).filter(annotatedBy(Captor.class)).instanceFields();
        for (InstanceField instanceField : instanceFields) {
            new CaptorAnnotationProcessor().process(instanceField.annotation(Captor.class), instanceField.jdkField());
        }
    }

    private void markAsInitialized(Object instance) {
        initializedInstances.put(instance, true);
    }

    private boolean alreadyInitialized(Object instance) {
        return initializedInstances.containsKey(instance);
    }

}
