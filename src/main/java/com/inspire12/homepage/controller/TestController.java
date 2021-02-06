package com.inspire12.homepage.controller;

import com.inspire12.homepage.message.request.ArticleRequest;
import com.inspire12.homepage.message.request.SignupRequest;
import com.inspire12.homepage.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final EmailService emailService;

    public SignupRequest testSignup () {
        return SignupRequest.create();
    }

    @PostMapping("/test")
    public void sendEmail () {
        emailService.getCertifyTokenByMail("ox4443@naver.com");
    }

    @PostMapping("/valid")
    @ResponseBody
    public String validTest (@Valid ArticleRequest articleRequest) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ArticleRequest>> constraintViolations = validator.validate(articleRequest);

        return (articleRequest.getContent());
    }
}


