package com.example.demo.controller;

import com.example.demo.config.GoogleLoginHandler;
import com.example.demo.dao.HelperShareDao;
import com.example.demo.dto.GoogleLoginPrincipal;
import com.example.demo.model.HelperCv;
import com.example.demo.model.HelperMember;
import com.example.demo.model.HelperShare;
import com.example.demo.repository.HelperMemberRepository;
import com.example.demo.repository.HelperShareRepository;
import com.example.demo.repository.HelpercvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class HelperShareController {
    @Autowired
    private HelperMemberRepository helperMemberRepository;
    @Autowired
    private HelperShareRepository helperShareRepository;
    @Autowired
    private HelpercvRepository helpercvRepository;
    @Autowired
    private HelperShareDao helperShareDao;

    //取得全部心得分享
    @GetMapping("/getShares")
    public List<HelperShare> getShares() {
        return helperShareRepository.findAll();
    }

    //新增心得分享
    @PostMapping("/addshare")
    public ResponseEntity<String> addHelperShare(@RequestBody HelperShare helper) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GoogleLoginPrincipal googleLoginHandler = (GoogleLoginPrincipal) new GoogleLoginHandler().googleLoginPrincipal(authentication);
        if (googleLoginHandler != null) {
            HelperMember helperMember = helperMemberRepository.findHelperMemberByAccount(googleLoginHandler.getEmail());
            helperMember.setShareSet(Set.of(helper));
            helper.setHelpmemberid(helperMember);
            return helperShareDao.addShare(helper);
        } else {
            String context = authentication.getName();
            HelperMember helperMember = helperMemberRepository.findHelperMemberByAccount(context);
            helperMember.setShareSet(Set.of(helper));
            helper.setHelpmemberid(helperMember);
            return helperShareDao.addShare(helper);
        }
    }

    //刪除心得分享
    @DeleteMapping("/deleteHelperShare/{id}")
    public String deleteHelperShare(@PathVariable int id) {
        helperShareRepository.deleteById(id);
        return "刪除成功";
    }

    //更新or新增履歷、大頭貼11111111111111111
    @PostMapping("/updatehelpercv")
    public String updateHelpercv(@RequestBody HelperCv cv) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GoogleLoginPrincipal googleLoginPrincipal = (GoogleLoginPrincipal) new GoogleLoginHandler().googleLoginPrincipal(authentication);
        HelperMember helperMember;
        if(googleLoginPrincipal!=null){
            helperMember = helperMemberRepository.findHelperMemberByAccount(googleLoginPrincipal.getEmail());
        }else {
            String context = authentication.getName();
            helperMember = helperMemberRepository.findHelperMemberByAccount(context);
        }
            helperMember.setCvSet(Set.of(cv));
            cv.setHelpermemberid(helperMember);
            helpercvRepository.save(cv);
            return "成功";
        }


    //刪除helpercv
    @DeleteMapping("/deleteHelpercv/{id}")
    public String deleteHelperCV(@PathVariable int id) {
        helpercvRepository.deleteById(id);
        return "刪除成功";
    }
}