package com.niit.DAO;

import java.util.List;

import com.niit.models.Friend;
import com.niit.models.User;

public interface FriendDAO {

	public boolean addFriendRequest(Friend friend);
	public boolean deleteFriendRequest(Friend friend);
	public boolean acceptFriendRequest(Friend friend);
	public List<User> getSuggestedUsers(String email);
	public List<Friend> getPendingRequests(String email);
	public List<User> listOfFriends(String email);
}
