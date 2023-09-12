package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.PostImportantRepository;
import com.example.demo.model.PostImportant;

@Service
public class PostImportantDao {

	@Autowired
    private final PostImportantRepository postImportantDao;

    
    public PostImportantDao(PostImportantRepository postImportantDao) {
        this.postImportantDao = postImportantDao;
    }

    public List<PostImportant> getAllPosts() {
        return postImportantDao.findAll();
    }

    public PostImportant getPostById(Integer id) {
        return postImportantDao.findById(id).orElse(null);
    }

    public void savePost(PostImportant post) {
        postImportantDao.save(post);
    }

    public void deletePost(Integer id) {
        postImportantDao.deleteById(id);
    }
}

