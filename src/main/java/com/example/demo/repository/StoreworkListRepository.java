package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.HelperMember;
import com.example.demo.model.StoreMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StoreWorkList;

public interface StoreworkListRepository extends JpaRepository<StoreWorkList, Integer> {
	List<StoreWorkList> findByStorememberid(StoreMember storememberid);

    StoreWorkList findByStoreWorkListId(Integer storeWorkListId);

    @Query(value = "select count(*) from helperjob where storeworklistid= :id",nativeQuery = true)
    Integer countCollect(@Param("id") Integer id);

    @Query(value = "select * from storeworklist left join helperjob on storeworklist.storeworklistid = helperjob.storeworklistid where helperjob.storeworklistid= :id",nativeQuery = true)
    List<StoreWorkList> getWorkCollect(@Param("id") Integer id);
	
}
