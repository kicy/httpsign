/*
 * Copyright (c) 2016-2100, fastquery.org and/or its affiliates. All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * For more information, please see http://www.fastquery.org/.
 * 
 */

package org.fastquery.httpsign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 统一处理Resorce类的异常
 * @author mei.sir@aliyun.cn
 */
public class InvokeFaultExceptionMapper implements ExceptionMapper<Throwable> {
		
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	public Response toResponse(Throwable throwable) {
		if(throwable instanceof WebApplicationException) {
			WebApplicationException wae = (WebApplicationException) throwable;
			return ReplyBuilder.error(wae.getResponse().getStatus(),wae.getMessage()).build();
		} else {
			throw new RuntimeException("未知错误,有待处理");
		}
	}
	
}
