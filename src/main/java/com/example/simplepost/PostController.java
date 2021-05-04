package com.example.simplepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private static Integer PAGE_SIZE = 20;

    @Autowired
    PostRepository postRepository;

    //글 작성
    @PostMapping("post")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    //글 목록을 페이징하여 반환
    @GetMapping("/posts")
    public Page<Post> getPostList(@RequestParam(defaultValue = "1") Integer page) {
        return postRepository.findAll(
            PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").descending())
        );
    }

    //글 번호로 조회
    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postRepository.findById(id).get();
    }
    
    //글 내용으로 검색
    @GetMapping("/search")
    public List<Post> findPostsByContent(@RequestParam String content) {
        return postRepository.findByContentContains(content);
    }
}
