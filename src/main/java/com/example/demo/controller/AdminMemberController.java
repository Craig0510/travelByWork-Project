package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AdminMemberRepository;
import com.example.demo.model.AdminMember;
import com.example.demo.dao.AdminMemberDao;

@RestController
public class AdminMemberController {

	@Autowired
	private AdminMemberRepository dao;
	@Autowired
	private AdminMemberDao service;

	// 註冊資料加入資料庫
	@PostMapping("/adminsignup")
	public String signup(@RequestBody AdminMember member) {
		return service.addmember(member);
	}

	@GetMapping("/getAllAdmin")
	public List<AdminMember> getHelperMembers() {
		return dao.findAll();
	}

	@GetMapping("/adminlogin")
	public String loginQuery(HttpServletRequest request, @RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password) {
		HttpSession session = request.getSession();
		return service.getByAccountAndPassword(session, account, password);
	}

	// 更新員工資料
	@PostMapping("/adminupdate")
	public String updateData(@RequestBody AdminMember member, HttpSession session) {
		session.setAttribute("adminMember", member);
		return service.UpdateAdminData(member);
	}

	// 刪除adminMember
	@DeleteMapping("/deleteAdmin/{id}")
	public String deleteHelperShare(@PathVariable int id) {
		dao.deleteById(id);
		return "刪除成功";
	}

	// 進入adminAccount.html時取得adminSession
	@GetMapping("/getAdminSession")
	public ResponseEntity<Object> getAdminSession(HttpSession session) {
		return ResponseEntity.status(HttpStatus.OK).body(session.getAttribute("adminMember"));
	}

}
