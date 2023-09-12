package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StoreMember;

@Repository
public interface StoreMemberRepository extends JpaRepository<StoreMember, Integer> {

	public boolean existsByAccount(String account);

	public boolean existsByEmail(String email);

	public boolean existsByStorename(String name);

	public boolean existsByMobile(String mobile);

	public StoreMember findByAccountAndPassword(String account, String password);

	StoreMember findStoreMemberByAccount(String account);

	StoreMember findStoreMemberByEmail(String email);

	StoreMember findByAccount(String account);
}
