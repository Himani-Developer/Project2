package com.niit.DAO;

import com.niit.models.ProfilePicture;

public interface ProfilePictureDAO {
public boolean uploadProfilePicture(ProfilePicture profilePicture);
public ProfilePicture getProfilePicture(String email);
}
