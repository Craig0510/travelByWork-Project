package com.example.demo.dao;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AdminMemberRepository;
import com.example.demo.model.AdminMember;

@Service
public class AdminMemberDao {

	@Autowired
	private AdminMemberRepository dao;

	// 判斷資料有無重複
	public Boolean queryDataExisted(AdminMember member) {
		boolean accountExist = dao.existsByAccount(member.getAccount());
		boolean mobileExist = dao.existsByMobile(member.getMobile());
		if (accountExist || mobileExist) {
			return true;
		}
		return false;
	}

	// 註冊管理員資料加入資料庫
	public String addmember(AdminMember member) {
		boolean b = queryDataExisted(member);
		if (b) {
			return "管理員資料重複，新增失敗";
		} else {
			dao.save(member);
			return "資料新增成功";
		}
	}

	public String getByAccountAndPassword(HttpSession session, String account, String password) {
		AdminMember member = dao.findByAccountAndPassword(account, password);
		if (member != null) {
			session.setAttribute("adminMember", member);
			return "登入成功";
		}
		return "查無此員工";
	}

	// 更新資料
	public String UpdateAdminData(AdminMember member) {
		System.out.println(member.toString());
		dao.save(member);
		return "密碼修改成功";
	}

}
