package com.crms.CustomerRelationalManagement.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity(debug=false)
@EnableMethodSecurity(prePostEnabled = false)
public class SecurityConfig {

    //securitychain beans

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        //configurations
        //urls
        //public koun se protected
        //koun se urls admin, koun se normal user  .

        //configuration urls

        security.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable()) ;

        security.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration() ;

                        corsConfiguration.setAllowedOrigins(List.of("*"));
                        corsConfiguration.setAllowedMethods(List.of("*"));
                        corsConfiguration.setAllowCredentials(true) ;
                        corsConfiguration.setAllowedHeaders(List.of("*"));
                        corsConfiguration.setMaxAge(4000L);
                        return corsConfiguration ;
                    }
                })) ; 
        // csrf ka hame abhi ke lie disable kiya hai
        // isko ham log baad me mein
        security.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable() ) ;

        security.authorizeHttpRequests(request->
                request .requestMatchers( "/api/v1/users/**","/api/v1/customers/**").permitAll()
                        .anyRequest().permitAll()


                
        );
        security.httpBasic(Customizer.withDefaults()) ;

        //kis type ki security :


        return  security.build() ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }
//    @Bean
//    public UserDetailsService userDetailsService() throws UsernameNotFoundException {
//
//
//        UserDetails user1  = User.withDefaultPasswordEncoder()
//                .username("durgesh")
//                .password("durgesh")
//                .roles("ADMIN","GUEST")
//                .build();
//
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("deepak")
//                .password("deepak")
//                .roles("ADMIN","GUEST" )
//                .build() ;
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager1= new InMemoryUserDetailsManager(user1, user2) ;
//
//
//        return  inMemoryUserDetailsManager1;
//    }
}
