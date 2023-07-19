package com.organizemanagemodule.services;

import java.util.List;

import com.organizemanagemodule.dtos.UserDto;
import com.organizemanagemodule.entities.User;

public interface UserService {
	
	List<UserDto> getAllUser();
	
	UserDto GetSingleUser(Integer id);
	
	UserDto UpdateUser(Integer id,User user);
	
	void DeleteUser(Integer id);

	Boolean isUserPresent(String email);
}
