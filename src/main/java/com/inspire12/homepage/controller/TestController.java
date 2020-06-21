package com.inspire12.homepage.controller;

import com.inspire12.homepage.model.request.ArticleRequest;
import com.inspire12.homepage.model.request.SignupRequest;
import com.inspire12.homepage.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.util.Set;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    EmailService emailService;


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


