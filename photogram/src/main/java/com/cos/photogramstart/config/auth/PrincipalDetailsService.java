package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	
	//1. password는 자동 체크 
	//2.return시 세션 자동생성 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//username이 있는지 확인 
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			return null;
		}else {
			//세션 자동 생성 
			return new PrincipalDetails(userEntity);
		}
	}

}
