package com.inspire12.homepage.service.board;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.model.message.CommentMsg;
import com.inspire12.homepage.model.entity.Comment;
import com.inspire12.homepage.repository.CommentRepository;
import com.inspire12.homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    public List<CommentMsg> getComments(Long articleId) {
        List<Comment> comments = commentRepository.selectCommentByArticleOrder(articleId);
        return convertToCommentMsgs(comments);
    }

    public void saveByRequest(ObjectNode request) {
        String userId = request.get("username").asText();
        Long articleId = request.get("article_id").asLong();
        String content = request.get("content").asText();
        Comment comment = Comment.create(userId, articleId, content);
        try{
            commentRepository.insertByRequest(articleId, userId, content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveReplyByRequest(ObjectNode request) {
        String userId = request.get("username").asText();
        Long articleId = request.get("article_id").asLong();
        String content = request.get("content").asText();
        int parentCommentId = request.get("parent_id").asInt();

//        Comment childComment = createComment(userId, articleId, content);
        try{
            Comment parentComment = commentRepository.findById(parentCommentId).get();
            commentRepository.updateReplyOrder(parentComment.getGrpno(), parentComment.getGrpord());
            commentRepository.insertByRequest(articleId, userId, content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<CommentMsg> convertToCommentMsgs(List<Comment> comments) {
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentMsg commentMsg = CommentMsg.createCommentMsg(comment, userRepository.findById(comment.getUsername()).get());
            commentMsgs.add(commentMsg);
        }
        return commentMsgs;
    }

}
