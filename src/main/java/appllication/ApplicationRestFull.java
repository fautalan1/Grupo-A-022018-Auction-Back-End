package appllication;

import appllication.model.factory.BuilderAuction;
import appllication.repository.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan(basePackages = {"appllication/entity"})
public class ApplicationRestFull {

    @Autowired
    private Environment env;

	@Autowired
	private AuctionDao auctionDao;


	public static void main(String[] args) {
		SpringApplication.run(ApplicationRestFull.class, args);
	}

	@Bean
	public BuilderAuction load() {
		BuilderAuction anyBuilderAuction = new BuilderAuction();

		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("victor@Gmail.com")
				.whitDescription("----El mejor juego de play(eso dicen)----")
				.whitTitle("----God of war----")
				.get()
		);


		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("gabiAvatar@Gmail.com")
				.whitDescription("----Set completo del avatar cubre libre 1 2 3 4 ----")
				.whitTitle("----Set Avatar----")
				.get()
		);

		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("ivar@Gmail.com")
				.whitDescription("----Waifusssssssssssss japonesa 100% original no fake----")
				.whitTitle("----Rica waifu----")
				.get()
		);
		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("n.autalan@Gmail.com")
				.whitDescription("----100% original made in argentina papa----")
				.whitTitle("----Armadura carton Megaman----")
				.get()
		);

		return anyBuilderAuction;
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String urls = env.getProperty("cors.urls");
				CorsRegistration reg = registry.addMapping("/**");
				for(String url: urls.split(",")) {
					reg.allowedOrigins(url);
				}
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
