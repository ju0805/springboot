package com.cos.photogramstart.domain.user;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호 자동 증가(DB를 따라감) 
	private int id;
	
	@Column(unique = true, length = 20, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String website; //웹사이트 
	private String bio; //자기소개
	
	@Column(nullable = false)
	private String email; 
	
	private String phone; //전화번호 
	private String gender; //성별 
	
	private String profileImageUrl; //사진 
	private String role; //권한 
	
	
	//양방향 맵핑 
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // table column x 
	private List<Image> images;
	
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
	
	
}
