package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.repository.HelperShareRepository;
import com.example.demo.model.HelperShare;

@Service
public class HelperShareDao {

    @Autowired
    private HelperShareRepository helperShareRepository;

    // 使用 helperShareRepository 將新的 helperShare 物件存入資料庫
    public ResponseEntity<String> addShare(HelperShare helper) {
        helperShareRepository.save(helper);
        return ResponseEntity.ok("心得新增成功");
    }

}
