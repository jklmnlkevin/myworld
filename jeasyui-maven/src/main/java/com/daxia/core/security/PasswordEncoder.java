package com.daxia.core.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * Aug 30, 2012
 * 
 */
@Component(value = "encoder")
public class PasswordEncoder extends Md5PasswordEncoder {
	@Override
	public String encodePassword(String rawPass, Object salt) {
		return super.encodePassword(rawPass, salt);
	}

	public static void main(String[] args) {
		System.out.println(new PasswordEncoder().encodePassword("333333", "admin"));
		System.out.println(new PasswordEncoder().encodePassword("333333", "testtest"));
	}

}
