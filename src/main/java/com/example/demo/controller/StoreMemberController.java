package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.HelperMemberRepository;
import com.example.demo.repository.StoreMemberRepository;
import com.example.demo.dto.StoreUpdateConfig;
import com.example.demo.model.HelperMember;
import com.example.demo.model.StoreMember;
import com.example.demo.dao.StoreMemberDao;

@RestController
public class StoreMemberController {

	@Autowired
	private StoreMemberRepository dao;
	@Autowired
	private StoreMemberDao service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HelperMemberRepository helperMemberDao;

	// 註冊資料加入資料庫
	@PostMapping("/storesignup")
	public String signup(@RequestBody StoreMember member, HttpServletRequest request) {
		HttpSession session = request.getSession();
		return service.addmember(member, session);
	}

	// 登入
	@GetMapping("/storelogin")
	public String loginQuery(HttpServletRequest request, @RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password) {
		HttpSession session = request.getSession();
		return service.getByAccountAndPassword(session, account, password);
	}

	// 更新會員資料
	@PostMapping("/storeupdata")
	public String updateData(@RequestBody StoreMember member, HttpSession session) {
		session.setAttribute("storeMember", member);
		return service.UpdateStoreData(member);
	}

	// 刪除storemember
	@DeleteMapping("/deleteStoreMemebr/{id}")
	public String deleteStoreMemebr(@PathVariable int id) {
		dao.deleteById(id);
		return "刪除成功";
	}

	@GetMapping("/getStoreMember")
	public List<StoreMember> findAllStoreMembers() {
		return dao.findAll();
	}

	@PutMapping("/updatestoremember")
	public ResponseEntity<Object> updateHelperMember(@RequestBody StoreUpdateConfig updateConfig) {
		Object member = service.updateStoreMember(updateConfig);
		if (member.getClass().equals(String.class)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("查無資料");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("更新成功");
		}
	}

	@PostMapping("/createstoresession")
	public ResponseEntity<String> createsession(@RequestBody StoreMember member, HttpSession session) {
		StoreMember s1 = service.findStoreMemberByAccount(member.getAccount());
		HelperMember h1 = helperMemberDao.findHelperMemberByAccount(member.getAccount());
		StoreMember s2 = service.findStoreMemberByEmail(member.getEmail());
		if (s1 != null) {
			return ResponseEntity.status(HttpStatus.OK).body("account");
		} else if (s2 != null) {
			return ResponseEntity.status(HttpStatus.OK).body("email");
		} else if (h1 != null) {
			return ResponseEntity.status(HttpStatus.OK).body("account");
		} else {
			String pwd = passwordEncoder.encode(member.getPassword());
			member.setPassword(pwd);
			session.setAttribute("storesign", member);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}

	@GetMapping("/getStore")
	public ResponseEntity<Object> getSession(HttpSession session) {
		String securityContext = SecurityContextHolder.getContext().getAuthentication().getName();
		StoreMember member = service.findStoreMemberByAccount(securityContext);
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}

	@GetMapping("/getCreateStore")
	public ResponseEntity<Object> getCreateSession(HttpSession session) {
		Object storeMember = session.getAttribute("storesign");
		System.out.println(storeMember);
		return ResponseEntity.status(HttpStatus.OK).body(storeMember);
	}
}
