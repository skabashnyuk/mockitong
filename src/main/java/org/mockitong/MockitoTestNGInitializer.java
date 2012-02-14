/*
 * Copyright (C) 2012
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

import org.mockito.testng.MockitoTestNGListener;

/**
 * Mockito annotation initializer for TestNG test.
 * 
 * To enable annotation initialization you should add MockitoTestNGInitializer
 * as a listener to you TestNG test. </p> Example of usage
 * 
 * 
 * @ Listeners(MockitoTestNGInitializer.class) public class MyTestClass {
 * 
 * }
 */
@Deprecated
public class MockitoTestNGInitializer extends MockitoTestNGListener
{

}
