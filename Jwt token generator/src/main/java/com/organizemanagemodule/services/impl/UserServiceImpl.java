package com.organizemanagemodule.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizemanagemodule.dtos.UserDto;
import com.organizemanagemodule.entities.User;
import com.organizemanagemodule.exceptions.UserNotFoundException;
import com.organizemanagemodule.repo.UserRepo;
import com.organizemanagemodule.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		return users.stream().map((user)-> this.userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto GetSingleUser(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("ID", id));
		return this.userToDto(user);
	}

	@Override
	public UserDto UpdateUser(Integer id, User user) {
		User user2 = this.userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("ID", id));
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		User savedUser = userRepo.save(user2);
		return userToDto(savedUser);
	}

	@Override
	public void DeleteUser(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("ID", id));
		this.userRepo.delete(user);
	}

	@Override
	public Boolean isUserPresent(String email) {
		return this.userRepo.existsByEmail(email);
	}

	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUuid(user.getUuid());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setUuid(userDto.getUuid());
		user.setPassword(userDto.getPassword());
		return user;
		
	}

}
