package appllication;

import appllication.model.factory.BuilderAuction;
import appllication.repository.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import java.time.LocalDateTime;

@SpringBootApplication
@EntityScan(basePackages = {"appllication/entity"})
public class ApplicationRestFull {

    @Autowired
    private Environment env;


	@Autowired
	@Qualifier("auctionDao")
	private AuctionDao auctionDao;


	public static void main(String[] args) {
		SpringApplication.run(ApplicationRestFull.class, args);
	}

	@Bean
	public BuilderAuction load() {
		BuilderAuction anyBuilderAuction = new BuilderAuction();

		//load Action//
		//* New Action*//
		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("victor@Gmail.com")
				.whitDescription("----El mejor juego de play(eso dicen)----")
				.whitTitle("----God of war----")
				.get()
		);


		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("gabiAvatar@Gmail.com")
				.whitDescription("----Set completo del avatar cubre libro 1 2 3 4 ----")
				.whitTitle("----Set Avatar----")
				.get()
		);

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("ivar@Gmail.com")
				.whitDescription("----Waifusssssssssssss japonesa 100% original no fake----")
				.whitTitle("----Rica waifu----")
				.get()
		);
		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("n.autalan@Gmail.com")
				.whitDescription("----100% original made in argentina papa----")
				.whitTitle("----Armadura carton Megaman----")
				.get()
		);
		//*InProgres*//

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("rukia@Gmail.com")
				.whitDescription("----Oni-chan-Baka----")
				.whitTitle("----Neko onichan----")
				.whitPublicationDate(LocalDateTime.now())
				.get()
		);
		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("waifu@Gmail.com")
				.whitDescription("----Tomos originales toda saga z----")
				.whitTitle("----Manga dragon ball z----")
				.whitPublicationDate(LocalDateTime.now())
				.get()
		);

		//* finished*//

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("lolo@Gmail.com")
				.whitDescription("----Set dragon ball super villanos----")
				.whitTitle("----Villanos dragon ball super----")
				.whitPublicationDate(LocalDateTime.now().minusDays(5))
				.whitFinishDate(LocalDateTime.now().minusDays(1))
				.whitInitialFinishDate(LocalDateTime.now().minusDays(1))
				.get()
		);
		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.whitEmailAuthor("kakaroto@Gmail.com")
				.whitDescription("----Es una golondrina que puede volar comer y no hacer nada----")
				.whitTitle("----Pepita 100% real no fake----")
				.whitPublicationDate(LocalDateTime.now())
				.whitFinishDate(LocalDateTime.now().minusDays(1))
				.whitInitialFinishDate(LocalDateTime.now().minusDays(1))
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
