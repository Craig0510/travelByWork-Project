package com.example.demo.controller;

import com.example.demo.dto.ReplyMail;
import com.example.demo.model.ContactUs;
import com.example.demo.dao.ContactUsDao;
import com.example.demo.config.Gmailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactUsController {
    @Autowired
    private ContactUsDao contactUsService;

    @PostMapping("/createcontactus")
    public  void createContactUs(@RequestBody ContactUs contactUs){
        contactUsService.createContactUs(contactUs);
    }

    @PostMapping("sendContactUsCopy")
    public ResponseEntity<String> sendCopy(@RequestBody ContactUs contactUs) throws Exception {
        String from="travelbywork2023@gmail.com";
        String title="已收到您的來信"+contactUs.getContactType();
        String subject="寄件者姓名:"+contactUs.getName()+"\n"+
                    "寄件者信箱:"+contactUs.getEmail()+"\n"+
                    "類型:"+contactUs.getContactType()+"\n"+
                    "日期:"+contactUs.getContactDate()+"\n"+
                    "內容:"+contactUs.getMessage();
        String toMail=contactUs.getEmail();
        new Gmailer().sendMail(from,toMail,title,subject);
        return  ResponseEntity.status(HttpStatus.OK).body("已成功記副本至"+contactUs.getEmail());
    }

    @GetMapping("/getAllContactUs")
    public ResponseEntity<List<ContactUs>> findAll(){
        List<ContactUs> contactUsList=contactUsService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(contactUsList);
    }
    @PostMapping("/sendReply")
    public ResponseEntity<String> sendReply(@RequestBody ReplyMail replyDetail) throws Exception {
        String from="travelbywork2023@gmail.com";
        new Gmailer().sendMail(from,replyDetail.getToEmail(),replyDetail.getTitle(),replyDetail.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED).body("成功");
    }
}