package com.organizemanagemodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizemanagemodule.dtos.UserDto;
import com.organizemanagemodule.entities.User;
import com.organizemanagemodule.paylods.ApiResponse;
import com.organizemanagemodule.services.UserService;

@RestController
@RequestMapping("/users")
@EnableMethodSecurity
public class UserController {

	@Autowired
	private UserService userService;

//	@PreAuthorize("hasRole('ADMIN') || hasRole('EMPLOYEE') || hasRole('TEACHER')")
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> users = this.userService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer id) {
		UserDto singleUser = this.userService.GetSingleUser(id);
		return ResponseEntity.ok(singleUser);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> UpdateUser(@PathVariable Integer id, @RequestBody User user) {
		UserDto updateUser = this.userService.UpdateUser(id, user);
		return ResponseEntity.ok(updateUser);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> DeleteUser(@PathVariable Integer id) {
		this.userService.DeleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true, 200), HttpStatus.OK);
	}
}
