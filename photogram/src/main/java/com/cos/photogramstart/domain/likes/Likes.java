package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name = "likes_uk",
						columnNames = {"imageId", "userId"}
				)
		}
	)
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호 자동 증가(DB를 따라감) 
	private int id;
	
	@JoinColumn(name="imageId")
	@ManyToOne
	private Image image;
	
	@JsonIgnoreProperties({"images", "password"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
