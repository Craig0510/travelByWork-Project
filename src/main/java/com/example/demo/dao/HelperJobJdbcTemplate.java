package com.example.demo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class HelperJobJdbcTemplate{

    private final JdbcTemplate jdbcTemplate;

    public HelperJobJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveHelperJob(Integer helperMemberId, Integer storeWorkListId){
        jdbcTemplate.update("INSERT INTO helperjob(helpermemberid,storeworklistid) VALUES(?,?)",helperMemberId,storeWorkListId);
    }

    public void deleteHelperJob(Integer helperMemberId, Integer storeWorkListId){
        jdbcTemplate.update("DELETE FROM helperjob WHERE helpermemberid = ?  AND storeworklistid = ?",helperMemberId,storeWorkListId);
    }



}
