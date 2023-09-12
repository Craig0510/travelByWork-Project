package com.example.demo.dao;

import com.example.demo.repository.HelperMemberRepository;
import com.example.demo.dto.UpdateConfig;
import com.example.demo.model.HelperMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelperMemberDao {
    @Autowired
    private HelperMemberRepository helperMemberDao;


    public Integer createHelperMember(HelperMember helperMember) {
        Integer createdId = helperMemberDao.save(helperMember).getHelperMemberId();
        return createdId;
    }


    public Object updateHelperMember(UpdateConfig updateConfig) {
        HelperMember member = helperMemberDao.findById(updateConfig.getHelpermemberid()).orElse(null);
        if (member != null) {
            member.setCountry(updateConfig.getCountry());
            member.setSexual(updateConfig.getSexual());
            member.setBirth(updateConfig.getBirth());
            member.setCountry(updateConfig.getCountry());
            member.setUsername(updateConfig.getUsername());
            member.setMobile(updateConfig.getMobile());
            HelperMember mem = helperMemberDao.save(member);
            return mem;
        } else {
            return "執行失敗,資料不存在";
        }

    }


    public HelperMember getHelperMemberByAccount(String account) {
        HelperMember helperMember = helperMemberDao.findHelperMemberByAccount(account);
        if (helperMember != null) {
            return helperMember;
        } else {
            return null;
        }
    }


    public HelperMember getHelperMemberByUsername(String username) {
        HelperMember helperMember = helperMemberDao.findHelperMemberByUsername(username);
        if (helperMember != null) {
            return helperMember;
        } else {
            return null;
        }
    }


    public HelperMember getHelperMemberByEmail(String email) {
        HelperMember helperMember = helperMemberDao.findHelperMemberByEmail(email);
        if (helperMember != null) {
            return helperMember;
        } else {
            return null;
        }
    }


    public List<HelperMember> findAll() {

        return helperMemberDao.findAll();
    }
}
