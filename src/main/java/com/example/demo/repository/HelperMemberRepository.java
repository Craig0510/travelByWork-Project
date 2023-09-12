package com.example.demo.repository;

import com.example.demo.model.HelperMember;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HelperMemberRepository extends JpaRepository<HelperMember,Integer> {
    HelperMember findHelperMemberByAccount(String account);

    HelperMember findHelperMemberByUsername(String username);

    HelperMember findHelperMemberByEmail(String email);

    List<HelperMember> findAll();

    @Query(value = "select * from helpermember left join helperjob on helpermember.helpermemberid = helperjob.helpermemberid where helperjob.helpermemberid= :id",nativeQuery = true)
    List<HelperMember> getCollect(@Param("id") Integer id);

}

