package com.example.demo.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.StoreMemberRepository;
import com.example.demo.dto.StoreUpdateConfig;
import com.example.demo.model.StoreMember;

@Service
public class StoreMemberDao {

	@Autowired
	private StoreMemberRepository dao;
	

	//判斷資料有無重複
	public Boolean queryDataExisted(StoreMember member) {	
		boolean accountExist=dao.existsByAccount(member.getAccount());
		boolean nameExist=dao.existsByStorename(member.getStorename());
		boolean emailExist=dao.existsByEmail(member.getEmail());
		boolean mobileExist=dao.existsByMobile(member.getMobile());
		if(accountExist || nameExist || emailExist || mobileExist ) {
			return true;
		}
		return false;	
	}
	//註冊店家資料加入資料庫
	public String addmember(StoreMember member,HttpSession session) {
		boolean b=queryDataExisted(member);
		if(b){ 
			return "新增失敗，資料重複";
		}else {
			session.setAttribute("newMember", member);
			dao.save(member);
			return "新增成功";
		}
	}
	//判斷登入的帳號密碼
	public String getByAccountAndPassword(HttpSession session,String account,String password) {
		StoreMember member=dao.findByAccountAndPassword(account, password);
		if(member!=null) {	
			session.setAttribute("storeMember", member);
			return "登入成功";
		}
		return "查無此會員";
	}
	//更新資料
	public String UpdateStoreData(StoreMember member) {
			System.out.println(member.toString());
			dao.save(member);
			return "修改成功";
	}
	
	//oli
	public List<StoreMember> findAllStoreMembers() {
        return dao.findAll();
    }
	public StoreMember findStoreMemberByEmail(String email) {
        StoreMember member = dao.findStoreMemberByEmail(email);
        if (member != null) {
            return member;
        } else {
            return null;
        }
    }
	public StoreMember findStoreMemberByAccount(String account) {
        StoreMember member = dao.findStoreMemberByAccount(account);
        if (member != null) {
            return member;
        } else {
            return null;
        }
    }
	public Object updateStoreMember(StoreUpdateConfig updateConfig){
        StoreMember member = dao.findById(updateConfig.getStorememberid()).orElse(null);
        if (member != null) {
            member.setOwnername(updateConfig.getOwnername());
            member.setAddress(updateConfig.getAddress());
            member.setPhone(updateConfig.getPhone());
            member.setMobile(updateConfig.getMobile());
            StoreMember mem = dao.save(member);
            return mem;
        } else {
            return "執行失敗,資料不存在";
        }
    }
	
}
