package com.inspire12.homepage.service.board;

import com.inspire12.homepage.domain.service.CommentDomainService;
import com.inspire12.homepage.domain.service.UserDomainService;
import com.inspire12.homepage.dto.article.CommentInfo;
import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.message.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDomainService commentDomainService;
    private final UserDomainService userDomainService;

    public List<CommentInfo> getComments(Long articleId, Integer count) {
        return commentDomainService.getComments(articleId, count).stream()
                .map(c -> CommentInfo.create(c, AppUserInfo.create(userDomainService.findByUsername(c.getUsername()).get())))
                .collect(Collectors.toList());
    }

    public void saveByRequest(CommentRequest request) {
        commentDomainService.saveByRequest(request);
    }

    public void saveReplyByRequest(CommentRequest request) {
        commentDomainService.saveByRequest(request);
    }
    }
