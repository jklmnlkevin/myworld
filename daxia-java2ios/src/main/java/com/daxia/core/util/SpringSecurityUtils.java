package com.daxia.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.daxia.core.model.Manager;

public class SpringSecurityUtils {
	public static Manager getCurrentUser() {
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof Manager) {
			Manager user = (Manager) au.getPrincipal();
			return user;
		}
		return null;
	}
}
