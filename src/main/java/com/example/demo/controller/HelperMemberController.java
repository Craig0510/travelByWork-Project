package com.example.demo.controller;

import com.example.demo.config.GoogleLoginHandler;
import com.example.demo.dao.HelperMemberDao;
import com.example.demo.dao.StoreMemberDao;
import com.example.demo.dto.AccountConfig;
import com.example.demo.dto.GoogleLoginPrincipal;
import com.example.demo.dto.UpdateConfig;
import com.example.demo.model.HelperCv;
import com.example.demo.model.HelperMember;
import com.example.demo.model.StoreMember;
import com.example.demo.repository.HelperMemberRepository;
import com.example.demo.repository.HelpercvRepository;
import com.example.demo.repository.StoreworkListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelperMemberController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HelperMemberDao helperMemberService;
    @Autowired
    private StoreMemberDao service;
    @Autowired
	private HelpercvRepository helpercvdao;

    @Autowired
    private HelperMemberRepository dao;
    @PostMapping("/gethelpermember")
    public void getHelperMember(@RequestBody AccountConfig accountConfig) {

    }
    
    @GetMapping("/clearSession")
    public void clearSession(){

    }
    
    @PostMapping("/createsession")
    public ResponseEntity<String> createsession(@RequestBody HelperMember helperMember,HttpSession session) {
        HelperMember h1=helperMemberService.getHelperMemberByAccount(helperMember.getAccount());
        HelperMember h2=helperMemberService.getHelperMemberByUsername(helperMember.getUsername());
        HelperMember h3=helperMemberService.getHelperMemberByEmail(helperMember.getEmail());
        StoreMember s1=service.findStoreMemberByAccount(helperMember.getAccount());
        if(h1!=null) {
            return  ResponseEntity.status(HttpStatus.OK).body("account");
        }
       else if(h2!=null) {
            return  ResponseEntity.status(HttpStatus.OK).body("username");
        }
        else if(h3!=null) {
            return  ResponseEntity.status(HttpStatus.OK).body("email");
        }else if(s1!=null){
            return  ResponseEntity.status(HttpStatus.OK).body("account");
        }
        else{
            String pwd = passwordEncoder.encode(helperMember.getPassword());
            helperMember.setPassword(pwd);
            session.setAttribute("sign", helperMember);
            Object o = session.getAttribute("sign");
            System.out.println(o);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PostMapping("/createhelpermember")
    public ResponseEntity<Integer> createHelperMember(@RequestBody HelperMember helperMember) {
        String pwd = passwordEncoder.encode(helperMember.getPassword());
        helperMember.setPassword(pwd);
        Integer createdId = helperMemberService.createHelperMember(helperMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    @PutMapping("/updatehelpermember")
    public ResponseEntity<Object> updateHelperMember(@RequestBody UpdateConfig updateConfig) {
        Object member = helperMemberService.updateHelperMember(updateConfig);
        if (member.getClass().equals(String.class)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("查無資料");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("更新成功");
        }
    }


    //測試中
    @GetMapping("/getSession")
    public ResponseEntity<Object> getSession(HttpSession session) {
        SecurityContext security = SecurityContextHolder.getContext();
        Authentication authentication = security.getAuthentication();
        GoogleLoginPrincipal googleLoginHandler =(GoogleLoginPrincipal) new GoogleLoginHandler().googleLoginPrincipal(authentication);
        if(googleLoginHandler!=null){
            HelperMember helperMemberByGoogleLogin = helperMemberService.getHelperMemberByEmail(googleLoginHandler.getEmail());
            if(helperMemberByGoogleLogin!=null){
                HelperCv helperCvGoogleLogin = helpercvdao.findByAccount(helperMemberByGoogleLogin.getAccount());
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("helpercv", helperCvGoogleLogin);
                userMap.put("helperMember", helperMemberByGoogleLogin);
                return ResponseEntity.status(HttpStatus.OK).body(userMap);
            }else{
                HelperMember helperMemberNew = new HelperMember();
                helperMemberNew.setAccount(googleLoginHandler.getEmail());
                helperMemberNew.setName(googleLoginHandler.getName());
                helperMemberNew.setEmail(googleLoginHandler.getEmail());
                helperMemberNew.setCountry(googleLoginHandler.getLocale());
                helperMemberNew.setCreatetime(new Date());
                helperMemberNew.setRole("ROLE_USER");
                helperMemberService.createHelperMember(helperMemberNew);
                HelperMember newMember = helperMemberService.getHelperMemberByEmail(googleLoginHandler.getEmail());
                HelperCv helperCvNew = helpercvdao.findByAccount(helperMemberNew.getAccount());
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("helpercv", helperCvNew);
                userMap.put("helperMember", helperMemberNew);
                return  ResponseEntity.status(HttpStatus.OK).body(userMap);
            }
        }else {
            String securityContext = authentication.getName();
            HelperMember helperMember = helperMemberService.getHelperMemberByAccount(securityContext);
            HelperCv helpercv = helpercvdao.findByAccount(securityContext);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("helpercv", helpercv);
            userMap.put("helperMember", helperMember);
            return ResponseEntity.status(HttpStatus.OK).body(userMap);
        }
    }

    @GetMapping("/getCreateSession")
    public ResponseEntity<Object> getCreateSession(HttpSession session) {
        Object helperMember=session.getAttribute("sign");
        Object storeMember=session.getAttribute("storesign");
        if(helperMember!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(helperMember);
        } else if (storeMember!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(storeMember);
        }else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(false);
        }
    }
    

    @GetMapping("/getHelperMembers")
    public List<HelperMember> getHelperMembers() {
        return helperMemberService.findAll();
    }

    @PostMapping("/googleLogin")
    public String googlelogin(@RequestBody HelperMember helperMember,HttpSession session) {
    	HelperMember member=helperMemberService.getHelperMemberByEmail(helperMember.getEmail());
    	if(member==null) {
    		helperMemberService.createHelperMember(helperMember);
    		HelperMember newMember=helperMemberService.getHelperMemberByAccount(helperMember.getAccount());
    		Map<String, Object> userMap = new HashMap<>();
            userMap.put("helperMember", newMember);
    		session.setAttribute("user",userMap);
            session.setMaxInactiveInterval(60);
        	return "成功";
    	}else {
    		HelperCv helpercv=helpercvdao.findByAccount(helperMember.getAccount());
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("helpercv", helpercv);
            userMap.put("helperMember", member);
    		session.setAttribute("user",userMap);
            session.setMaxInactiveInterval(60);
    		return "成功";
    	}
    }
    @GetMapping("/getGoogleSession")
    public ResponseEntity<Object> getGoogleSession(HttpSession session) {
        return ResponseEntity.status(HttpStatus.OK).body(session.getAttribute("user"));
    }

    //刪除helperMember
    @DeleteMapping("/deleteHelperMemebr/{id}")
    public String deleteHelperMemebr(@PathVariable int id) {
    	dao.deleteById(id);
    	return "刪除成功";
    }

    @GetMapping("/getLoginSession")
    public ResponseEntity<Object> getLoginSession() {
        String securityContext = SecurityContextHolder.getContext().getAuthentication().getName();
        HelperMember helperMember = helperMemberService.getHelperMemberByAccount(securityContext);
        StoreMember member = service.findStoreMemberByAccount(securityContext);
        if (helperMember != null) {
            return ResponseEntity.status(HttpStatus.OK).body(helperMember);
        } else if (member != null) {
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("無此會員");
        }
    }


}