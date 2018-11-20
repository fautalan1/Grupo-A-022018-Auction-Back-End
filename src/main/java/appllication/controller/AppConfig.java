package appllication.controller;


import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity(debug = true)
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auctions").authenticated()
                .antMatchers(HttpMethod.POST, "/auctions/recentAuctions").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/title_and_description").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/title").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/toFinish").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/toFinishBetween").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/update").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/{auctionId}/offer/{bidder}").authenticated()
                .antMatchers(HttpMethod.POST, "/auction/first/offer/{auctionId}/{maxAmount}/{bidder}").authenticated()

                .antMatchers(HttpMethod.DELETE, "/auction/delete/{id}").authenticated()

                .antMatchers(HttpMethod.PUT, "/new/auction").authenticated()

                .antMatchers(HttpMethod.GET, "/auction/recover/{id}").authenticated()

                .antMatchers(HttpMethod.GET, "/auction/{emailAuthor}").permitAll();


    }

}
