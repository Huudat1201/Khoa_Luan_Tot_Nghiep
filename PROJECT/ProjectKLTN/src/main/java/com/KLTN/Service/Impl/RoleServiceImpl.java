package com.KLTN.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Role;
import com.KLTN.Repository.RoleRepository;
import com.KLTN.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	/*
	 *  Danh sách tên các vai trò của người dùng dựa trên userId.
	 */
	@Override
	public List<String> getRoleNames(int userId) {
		return roleRepository.getRoleNames(userId);
	}

	/*
	 *  Tìm và trả về vai trò (Role) dựa trên roleId.
	 */
	@Override
	public Role findRoleById(int roleId) {
		return roleRepository.findById(roleId).get();
	}
	
}
