package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.StoreworkListRepository;
import com.example.demo.model.StoreWorkList;

@Service //oli
public class StoreworkListDao {
    @Autowired
    private StoreworkListRepository slDao;

    public List<StoreWorkList> getAllStoreworkList() {
        return slDao.findAll();
    }

    public String addsl(StoreWorkList sl) {
        sl.setWorkupdatetime(new Date());
        slDao.save(sl);
        return "新增成功"; 
    }
}
