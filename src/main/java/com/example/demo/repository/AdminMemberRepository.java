package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdminMember;


@Repository
public interface AdminMemberRepository extends JpaRepository<AdminMember,Integer> {

	public boolean existsByAccount(String account);
	public boolean existsByMobile(String mobile);
	public AdminMember findByAccountAndPassword(String account, String password);
}
