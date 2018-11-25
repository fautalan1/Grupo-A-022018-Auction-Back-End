package appllication;

import appllication.model.factory.BuilderAuction;
import appllication.repository.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
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
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties")
})
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
				.withEmailAuthor("victor@Gmail.com")
				.withDescription("----El mejor juego de play(eso dicen)----")
				.withTitle("God of war")
				.withPrice(200)
                .withPhoto("https://www.crystalcommerce.com/wp-content/uploads/2018/09/square_gow4-notxt.jpg")
				.get()
		);


		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("gabiAvatar@Gmail.com")
				.withDescription("----Set completo del avatar cubre libro 1 2 3 4 ----")
				.withTitle("Set Avatar")
				.withPrice(500)
                .withPhoto("https://static1.squarespace.com/static/547286fee4b0d546b9cd9ef6/t/579eb0eee58c625407399e3d/1501454327999/")
				.get()
		);

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("ivar@Gmail.com")
				.withDescription("----Waifusssssssssssss japonesa 100% original no fake----")
				.withTitle("Waifu")
				.withPrice(50)
				.withPublicationDate(LocalDateTime.now().plusHours(1))
                .withFinishDate(LocalDateTime.now().plusDays(3))
                .withInitialFinishDate(LocalDateTime.now().plusDays(3))
                .withPhoto("https://www.heypoorplayer.com/wp-content/uploads/2018/02/Segment_0001.jpg")
				.get()
		);
		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("n.autalan@Gmail.com")
				.withDescription("----100% original made in argentina papa----")
				.withTitle("Megaman")
				.withPrice(2000)
                .withPhoto("https://amp.businessinsider.com/images/5b928a6864dce81a008b5a0c-750-396.jpg")
				.get()
		);
		//*InProgres*//

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("rukia@Gmail.com")
				.withDescription("-----Dragon Ball FighterZ----------")
				.withTitle("FighterZ")
				.withPublicationDate(LocalDateTime.now().minusDays(2))
                .withFinishDate(LocalDateTime.now().plusHours(1))
                .withInitialFinishDate(LocalDateTime.now().plusHours(1))
                .withPhoto("https://icdn9.digitaltrends.com/image/dragon-ball-fighterz-review-1104-1920x1080.jpg")
				.get()
		);
		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("waifu@Gmail.com")
				.withDescription("---------Manga Dragon Ball--------------------")
				.withTitle("Manga Dragon Ball")
                .withPrice(100)
                .withPhoto("https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/main_element/public/filefield_paths/dragonballdvd2.jpg?itok=uO3HI59b")
				.withPublicationDate(LocalDateTime.now().minusDays(1))
				.withFinishDate(LocalDateTime.now().plusDays(1))
				.withInitialFinishDate(LocalDateTime.now().plusDays(1))
				.get()
		);

		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("locomotion@Gmail.com")
				.withDescription("Todos los videos que quedaron de el famoso canal locomotion de anime")
				.withTitle("Locomotion")
                .withPrice(200)
                .withPhoto("http://anime.es/wp-content/uploads/2015/10/NGEvangelion-770x433.jpg")
				.withPublicationDate(LocalDateTime.now().minusDays(2))
				.withFinishDate(LocalDateTime.now().plusMinutes(1))
				.withInitialFinishDate(LocalDateTime.now().plusMinutes(3))
				.get()
		);

		//* finished*//

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("lolo@Gmail.com")
				.withDescription("------- --------- Villanos Dragon Ball Super ---------- --------")
				.withTitle("Villanos DBS")
                .withPrice(300)
                .withPhoto("https://image.jimcdn.com/app/cms/image/transf/dimension=890x10000:format=png/path/s81b4791a1b7c63fb/image/i86c58e3ebe0dfc3b/version/1535480861/image.png")
				.withPublicationDate(LocalDateTime.now().minusDays(5))
				.withFinishDate(LocalDateTime.now().minusDays(1))
				.withInitialFinishDate(LocalDateTime.now().minusDays(1))
				.get()
		);
		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("kakaroto@Gmail.com")
				.withDescription("----Es una muñeco coleccionable----")
				.withTitle("Goku Bampresto")
                .withPrice(900)
                .withPhoto("https://www.ninoma.com/media/catalog/product/cache/2/image/02067325ba47ad6dbc32075e74e23f9c/n/e/new_goku_1.jpg")
				.withPublicationDate(LocalDateTime.now())
				.withFinishDate(LocalDateTime.now().minusDays(1))
				.withInitialFinishDate(LocalDateTime.now().minusDays(1))
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
