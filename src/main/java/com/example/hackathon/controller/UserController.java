package com.example.hackathon.controller;

import com.example.hackathon.dto.UserInfoDto;
import com.example.hackathon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String getSign() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserInfoDto infoDto, BindingResult bindingResult,
                         HttpServletRequest request, HttpServletResponse response) throws Exception { // 회원 추가
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            sb.append("message :\n");
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();
                sb.append(message + "\n");
            });
            out.println("<script>alert('회원가입에 실패하셨습니다'); history.go(-1);</script>");
            out.flush();
        } else {
            if (userService.sn_check(infoDto.getSocialNumber()) == false | userService.email_check(infoDto.getEmail()) == false) {
                out.println("<script>alert('이미 가입되어 있는 회원입니다'); location.href ='/login'</script>");
                out.flush();
            } else {
                userService.save(infoDto);
                out.println("<script>alert('회원가입에 성공하셨습니다'); location.href ='/login'</script>");
                out.flush();
                return "redirect:/login";
            }
        }
        return "";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}