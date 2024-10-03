package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.Comment;
import com.kindsonthegenius.inventoryms_springboot_api.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {


    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public List<Comment> getCommentes(){
        return commentService.getAllComments();
    }

    @GetMapping("/comment/{id}")
    public Comment getComment(@PathVariable("id") Long id){
        return commentService.getCommentById(id);
    }

    @PutMapping("/comment/{id}")
    public Comment updateComment(@RequestBody() Comment comment, @PathVariable("id") Long id){
        return commentService.save(comment);
    }

    @PostMapping("/comments")
    public Comment addNew(@RequestBody() Comment comment){
        return commentService.save(comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
    }
}
