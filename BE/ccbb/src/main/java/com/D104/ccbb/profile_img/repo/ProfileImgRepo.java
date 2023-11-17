package com.D104.ccbb.profile_img.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.profile_img.domain.ProfileImg;

public interface ProfileImgRepo extends JpaRepository<ProfileImg, Integer> {

	ProfileImg findByUserId_UserId(int userId);
}
