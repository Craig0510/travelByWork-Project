package com.example.demo.controller;

import com.example.demo.config.GoogleLoginHandler;
import com.example.demo.dao.HelperJobJdbcTemplate;
import com.example.demo.dto.GoogleLoginPrincipal;
import com.example.demo.model.HelperMember;
import com.example.demo.model.StoreWorkList;
import com.example.demo.repository.HelperMemberRepository;
import com.example.demo.repository.StoreworkListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class HelperJobController {
    @Autowired
    private HelperMemberRepository helperMemberRepository;
    @Autowired
    private StoreworkListRepository storeworkListRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //2222222222222222222222222
    @PostMapping("/addHelperJob")
    public String addHelperJob(@RequestBody StoreWorkList storeWorkListSet){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GoogleLoginPrincipal googleLoginPrincipal = (GoogleLoginPrincipal) new GoogleLoginHandler().googleLoginPrincipal(authentication);
        HelperMember helperMember;
        if(googleLoginPrincipal!=null){
            helperMember = helperMemberRepository.findHelperMemberByAccount(googleLoginPrincipal.getEmail());
        }else {
            String helperMemberAccount = authentication.getName();
            helperMember = helperMemberRepository.findHelperMemberByAccount(helperMemberAccount);
        }
            StoreWorkList storeWorkList = storeworkListRepository.findByStoreWorkListId(storeWorkListSet.getStoreWorkListId());
            helperMember.setStoreWorkLists(Set.of(storeWorkList));
            storeWorkList.setHelperMembers(Set.of(helperMember));
            //新增資料到關聯表中
            HelperJobJdbcTemplate helperJobJdbcTemplate = new HelperJobJdbcTemplate(jdbcTemplate);
            helperJobJdbcTemplate.saveHelperJob(helperMember.getHelperMemberId(), storeWorkList.getStoreWorkListId());
            return "成功";
        }

    @GetMapping("/getcollectids/{memberid}")
    public  Set<StoreWorkList> getCollectIds(@PathVariable Integer memberid){
        List<HelperMember> helperMembers = helperMemberRepository.getCollect(memberid);
        Set<StoreWorkList> storeWorkLists = new HashSet<>();
        helperMembers.forEach(helperMember ->storeWorkLists.addAll(helperMember.getStoreWorkLists()));
        Set<Integer>  storeWorkIds = new HashSet<>();
        storeWorkLists.forEach(v->storeWorkIds.add(v.getStoreWorkListId()));
        return storeWorkLists;
    }

    @GetMapping("/getcollect/{id}")
    public List<HelperMember> getCollect(@PathVariable Integer id){
        return helperMemberRepository.getCollect(id);
    }
    @GetMapping("/getworkcollect/{id}")
    public List<StoreWorkList> getWorkCollect(@PathVariable Integer id){
        return storeworkListRepository.getWorkCollect(id);
    }

    @GetMapping("/getcollectnumber/{id}")
    public Integer getCollectNimber(@PathVariable Integer id){
        return storeworkListRepository.countCollect(id);
    }

    @GetMapping("/islistcollect/{id}/{listId}")
    public Boolean isListCollect(@PathVariable Integer id, @PathVariable Integer listId){
        List<HelperMember> helperMemberList=getCollect(id);
        List<StoreWorkList> storeWorkLists = new ArrayList<>();
        helperMemberList.forEach(helperMember ->storeWorkLists.addAll(helperMember.getStoreWorkLists()));
        StoreWorkList list = storeWorkLists.stream().filter(v->v.getStoreWorkListId().equals(listId)).findFirst().orElse(null);
        return list!=null;

    }

    @DeleteMapping("/deletecollect/{id}/{listid}")
    public void deleteCollect(@PathVariable Integer id,@PathVariable Integer listid){
        HelperJobJdbcTemplate helperJobJdbcTemplate=new HelperJobJdbcTemplate(jdbcTemplate);
        helperJobJdbcTemplate.deleteHelperJob(id,listid);
    }



}
