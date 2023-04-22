package com.codeup.codeupspringblog.config;

import com.codeup.codeupspringblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/events") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeHttpRequests()

                .requestMatchers(
                        "/",
                        "/posts",
                        "/posts/{id}",
                        "/posts/index",
                        "/posts/{id}/like",

                        "/matrix",
                        "/comment/create",
                        "/login",
                        "/logout",
                        "/sign-up",
                        "/register",
                        "/about",
                        "/chat",
                        "/chat/{selectedId}",

                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/static/**",

                        "/events",
                        "/events/{id}",
                        "/events/{id}/find"
                        ) // anyone can see home, the posts pages, and sign up
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeHttpRequests()
                .requestMatchers(

                        "/pets/card",
                        "/pets/{id}",
                        "/pets/profile",
                        "/pets",
                        "/pets/delete",
                        "/pets/{id}/delete",
                        "/pets/edit",
                        "/pets/{id}/edit",
                        "/pets/register",

                        "/profile/edit",
                        "/profile/delete",
                        "/profile",
                        "/register",
                        "/user/{id}",
                        "/user/card",
                        "/users/{id}/follow",
                        "/following",
                        "/users/{id}/unfollow",

                        "/friends",
                        "/user/{id}/show",

                        "/posts",
                        "/posts/{id}/edit",
                        "/posts/create", // only authenticated users can create posts
                        "/posts/{id}/edit", // only authenticated users can edit ads
                        "/posts/edit",
                        "/posts/{n}/delete",
                        "/posts/delete/{n}",
                        "/posts/{id}/like",
                        "/posts/1/show",
                        "/posts/{id}/show",
                        "/comment/{id}/create",

                        "/events",
                        "/events/{id}",
                        "/events/create",
                        "/events/{id}/edit",
                        "/events/{id}/find",

                        "/events/{id}/like",
                        "/events/{id}/delete",
                        "/events/{id}/delete-profile-event",
                        "/events/{id}/participate",
                        "/events/{id}/delete",
                        "/events/{id}/participate",
                        "/events/{eventId}/participants",


                        //added for search functionality
                        "/api/**",
                        "/users",
                        "/test",
                        "/js/**",
                        "/img/**"
                )
                .authenticated();
        return http.build();
    }

}
