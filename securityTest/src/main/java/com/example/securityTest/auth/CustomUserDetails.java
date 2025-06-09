package com.example.securityTest.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.securityTest.entity.UserEntity;

public class CustomUserDetails implements UserDetails {
	private UserEntity userEntity;
	
	public CustomUserDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userEntity.getRole();
			}
		});
		
		return collect;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}
	
	public String getName() {
		return userEntity.getName();
	}
	
	public String getRole() {
		return userEntity.getRole();
	}

}
