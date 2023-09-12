package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import com.example.demo.model.StoreMember;
import com.example.demo.repository.StoreMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.StoreworkListRepository;
import com.example.demo.model.StoreWorkList;
import com.example.demo.dao.StoreworkListDao;

@RestController
public class StoreworkListController {
	@Autowired
	private StoreworkListDao slservice;
	@Autowired
	private StoreworkListRepository storeworkListRepository;
	@Autowired
	private StoreMemberRepository storeMemberRepository;

	@GetMapping("/getPostJobs")
	public List<StoreWorkList> getPostJobs() {
		return slservice.getAllStoreworkList();
	}

	@PostMapping(value = "/createJob")
	public String addsl(@RequestBody StoreWorkList sl) {
		String storeContext= SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(storeContext);
		StoreMember storeMember = storeMemberRepository.findStoreMemberByAccount(storeContext);
		storeMember.setListSet(Set.of(sl));
		sl.setStorememberid(storeMember);
		return slservice.addsl(sl);
	}

	@DeleteMapping("/deleteStoreWorkList/{id}")
	public String deletestoreworklist(@PathVariable int id) {
		storeworkListRepository.deleteById(id);
		return "刪除成功";
	}

	@GetMapping("/getFavoriteJob/{storeworklistid}")
	public StoreWorkList getFavoriteJob(@PathVariable int storeworklistid) {
		return storeworkListRepository.findById(storeworklistid).get();
	}

	@GetMapping("/getByStoreMemebrId/{storememberid}")
	public List<StoreWorkList> getByStoreMemebrId(@PathVariable StoreMember storememberid) {
		return storeworkListRepository.findByStorememberid(storememberid);
	}
}
