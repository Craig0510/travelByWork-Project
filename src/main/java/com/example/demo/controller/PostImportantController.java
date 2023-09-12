package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.PostImportant;
import com.example.demo.dao.PostImportantDao;

@Controller
@RequestMapping("/posts")
public class PostImportantController {
	@Autowired
	private final PostImportantDao postImportantService;

	public PostImportantController(PostImportantDao postImportantService) {
		this.postImportantService = postImportantService;
	}

	// 顯示所有文章列表
	@GetMapping
	public String listPosts(Model model) {
		model.addAttribute("posts", postImportantService.getAllPosts());
		return "post-list"; // 返回文章列表的視圖模板名稱
	}

	@ResponseBody
	@GetMapping("/allpost")
	public List<PostImportant> getAllPost() {
		return postImportantService.getAllPosts();
	}

	// 查看單篇文章的詳細資訊
	@GetMapping("/{id}")
	public String viewPost(@PathVariable Integer id, Model model) {
		PostImportant post = postImportantService.getPostById(id);
		model.addAttribute("post", post);
		return "post-details"; // 返回文章詳情的視圖模板名稱
	}

	// 顯示創建文章的表單
	@GetMapping("/create")
	public String createPostForm(Model model) {
		model.addAttribute("post", new PostImportant());
		return "post-form"; // 返回創建文章的視圖模板名稱
	}

	// 處理創建文章的提交
	@ResponseBody
	@PostMapping("/create")
	public String createPost(@RequestBody PostImportant post) {
		System.out.println(post.toString());
		postImportantService.savePost(post);
		return "公告新增成功"; // 重定向到文章列表頁面
	}

	// 處理刪除文章的請求
	@ResponseBody
	@DeleteMapping("/{id}/delete")
	public String deletePost(@PathVariable Integer id) {
		postImportantService.deletePost(id);
		return "公告新增成功"; // 重定向到文章列表頁面
	}
}
