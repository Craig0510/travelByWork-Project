package com.example.demo.dao;

import com.example.demo.repository.ContactUsRepository;
import com.example.demo.model.ContactUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactUsDao {
    @Autowired
    private ContactUsRepository contactUsDao;

    public void createContactUs(ContactUs contactUs){
        contactUsDao.save(contactUs);
    }

    public List<ContactUs> findAll(){
        List<ContactUs> contactUsList=contactUsDao.findAll();
        return contactUsList;
    }

}

