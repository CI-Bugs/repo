/*
 * Copyright (C) 2013 Brett Wooldridge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zaxxer.hikari.proxy;

import java.sql.CallableStatement;

/**
 * This is the proxy class for java.sql.CallableStatement.  It is used in two ways:
 * 
 *  1) If instrumentation is not used, Javassist will generate a new class
 *     that extends this class and delegates all method calls to the 'delegate'
 *     member (which points to the real CallableStatement).
 *
 *  2) If instrumentation IS used, Javassist will be used to inject all of
 *     the &amp;HikariInject and &amp;HikariOverride annotated fields and methods
 *     of this class into the actual CallableStatement implementation provided by the
 *     JDBC driver.  In order to avoid name conflicts when injecting code into
 *     a driver class some of the fields and methods are prefixed with _ or __.
 *     
 *     Methods prefixed with __, like __executeQuery() are especially
 *     important because when we inject our own executeQuery() into the
 *     target implementation, the original method is renamed to __executeQuery()
 *     so that the call operates the same whether delegation or instrumentation
 *     is used.
 *
 * @author Brett Wooldridge
 */
public abstract class CallableStatementProxy extends PreparedStatementProxy implements IHikariStatementProxy, CallableStatement
{
    protected CallableStatementProxy(ConnectionProxy connection, CallableStatement statement)
    {
        super(connection, statement);
    }

    // **********************************************************************
    //               Overridden java.sql.CallableStatement Methods
    // **********************************************************************


    // ***********************************************************************
    // These methods contain code we do not want injected into the actual
    // java.sql.Connection implementation class.  These methods are only
    // used when instrumentation is not available and "conventional" Javassist
    // delegating proxies are used.
    // ***********************************************************************

}