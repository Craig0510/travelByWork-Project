package com.example.demo.repository;

import com.example.demo.model.HelperMember;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.HelperCv;

public interface HelpercvRepository extends JpaRepository<HelperCv,Integer>{

	public HelperCv findByAccount(String account);
}
