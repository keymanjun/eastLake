package com.framework.security.cache;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityCacheManager 
{
	
	public void getUser()
	{
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authen=context.getAuthentication();
	
		authen.getPrincipal();
	}
}
