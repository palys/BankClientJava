// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `FinancialNews.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package FinancialNews;

public interface _NewsReceiverDel extends Ice._ObjectDel
{
    void exchangeRate(Currency curr1, Currency curr2, float rate, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;

    void interestRate(Currency curr, float rate, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;
}
