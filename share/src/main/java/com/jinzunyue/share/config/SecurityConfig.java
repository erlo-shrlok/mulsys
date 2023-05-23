package com.jinzunyue.share.config;

import com.jinzunyue.share.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 返回一个Bean，这个Bean负责处理所有的HTTP安全策略
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        // authorize -> authorize.anyRequest().authenticated() 配置了HTTP请求的授权规则。它表示所有的HTTP请求都需要经过身份验证。
        // .formLogin(withDefaults()) 配置了表单登陆的默认设置。当用户尝试访问一个需要身份验证的页面时，他们将被重定向到一个登陆页面。
        // .httpBasic(withDefaults()) 配置了HTTP基本认证的默认设置。HTTP基本认证是一种验证用户身份的简单方法，它将用户名和密码以明文形式发送到服务器。
        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        http.authenticationProvider(authenticationProvider());
        // 构建了HttpSecurity对象，并返回一个SecurityFilterChain对象
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
//        provider.setPasswordEncoder(passwordEncoder());
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
