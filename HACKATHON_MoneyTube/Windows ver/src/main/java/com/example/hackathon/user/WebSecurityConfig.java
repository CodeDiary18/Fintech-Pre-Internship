package com.example.hackathon.user;

import com.example.hackathon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure(WebSecurity web) { // 인증을 무시할 경로들 설정 가능
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/favicon.ico", "/resources/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
        http
                .authorizeRequests() // # 권한에 따른 접근가능한 주소 설정
                .antMatchers("/login", "/signup", "/", "/invest", "/user").permitAll() // 누구나 접근 허용
                .antMatchers("/mypage").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin","/admin/loan-list").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청(주소)들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // # 로그인에 관한 설정
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout() // # 로그아웃에 관한 설정
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 로그인할 때 필요한 정보를 가져오는 곳
        auth.userDetailsService(userService)
                // 해당 서비스(userService)에서는 UserDetailsService를 implements해서
                // loadUserByUsername() 구현해야함 (서비스 참고)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
