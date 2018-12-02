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
				.withEmailAuthor("gabiAvatar@Gmail.com")
				.withDescription("----Set completo del avatar cubre libro 1 2 3 4 ----")
				.withTitle("Set Avatar")
				.withPrice(500)
				.withPublicationDate(LocalDateTime.now().plusHours(1))
                .withFinishDate(LocalDateTime.now().plusDays(3))
                .withInitialFinishDate(LocalDateTime.now().plusDays(3))
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
				.withEmailAuthor("n.autalan@gmail.com")
				.withDescription("----100% original made in argentina papa----")
				.withTitle("Megaman")
				.withPrice(2000)
				.withPublicationDate(LocalDateTime.now().plusHours(1))
                .withFinishDate(LocalDateTime.now().plusDays(3))
                .withInitialFinishDate(LocalDateTime.now().plusDays(3))
                .withPhoto("https://amp.businessinsider.com/images/5b928a6864dce81a008b5a0c-750-396.jpg")
				.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("ZERO@Gmail.com")
						.withTitle("Black Zero Figure ")
						.withDescription("-Purple slashing sword effect part\n" +
										" -Interchangeable hands and faces\n" +
										" -Optional buster attachment " )
						.withPrice(10000)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/81PeQpV028L._SX425_.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("n.autalan@gmail.com")
						.withTitle("Black Goku Figure ")
						.withDescription("-Kamehameha effect Part, and rose effect Part set\n" +
										 "Includes Super Saiyan rose\n" +
								         "Also includes six pairs of optional hands, three optional expressions (two for rose)\n" +
								         "Product bears official Bluefin Distribution logo " )
						.withPrice(500)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://limitededition.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/b/o/boneco-goku-black-bandai.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("marcosLonchuka@gmail.com")
						.withTitle("Bulma S.H.Figuarts")
						.withDescription(" -Size: Standard\n" +
								"-Figure is 5.25\" tall\n" +
								"-Poseable w/ 12 points of articulation\n" +
								"-Interchangeable faces & hands snap into place\n" +
								"-Officially licensed " )
						.withPrice(1000)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/41XNV2t-NdL.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("RodryMendy@gmail.com")
						.withTitle("Power Rangers White  ")
						.withDescription("-Detailed, 6-inch scale White Ranger figure from Mighty Morphin Power Rangers\n" +
								"-Premium Tamashii Nations design and detail.\n" +
								"-Includes figure and accessories.\n" +
								"-Ages 15 and up. " )
						.withPrice(700)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/71kpWZolkDL._SY450_.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("Franco@gmail.com")
						.withTitle("Vegeta Figure")
						.withDescription(" -Re-create dramatic scenes from the anime with incredibly life-like posing\n" +
								"-The 160mm figure includes many accessories, including 2 pairs of optional hands, 2 optional facial expression parts\n" +
								"-Optional crossed arms, an optional head, a tail, a wearable scouter, and a hand gripping a crushed scouter\n" +
								"-Product bears official Bluefin Distribution logo\n" +
								"-The affixed label with the Bluefin Distribution logo also entitles the purchaser to product support assistance  " )
						.withPrice(100)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/61PWi7kT29L._SY550_.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("LaGallardo@gmail.com")
						.withTitle("Gato gallina")
						.withDescription(" GATO MEDIO GALLINA QUE TE RASGUÑA" )
						.withPrice(8000)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://http2.mlstatic.com/gato-chino-dorado-de-la-suerte-con-movimiento-de-la-mano-D_NQ_NP_805715-MLM25306141876_012017-F.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("susana_guapa@gmail.com")
						.withTitle("Master Roshi Action")
						.withDescription("-Materials: PVC, The model is from Dragon Ball Z , the classic image of Master Roshi.\n" +
								"-High-quality dragon ball series Action Figure,age grade 12+.\n" +
								"-The modelling style is changeful, the quality is superior.\n" +
								"-Size: 8.9\" ,Weight:17.63 oz. " )
						.withPrice(700)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://ae01.alicdn.com/kf/HTB1u8dISXXXXXagXpXXq6xXFXXXS/Dragon-Ball-Z-maestro-Roshi-PVC-figuras-de-acci-n-Juguetes-20-cm-opp.jpg_640x640.jpg")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("juancitoLaguna@gmail.com")
						.withTitle("Final Fantasy ArchiveVolume3  ")
						.withDescription("The journey through the creation of the groundbreaking video games continues with this volume, featuring hundreds of pieces of concept art, design notes, and creator retrospectives from the original team behind the making of Final Fantasy X, Final Fantasy XI, Final Fantasy XII, Final Fantasy XIII, and Final Fantasy XIV. " )
						.withPrice(200)
						.withPublicationDate(LocalDateTime.now().plusHours(1))
						.withFinishDate(LocalDateTime.now().plusDays(3))
						.withInitialFinishDate(LocalDateTime.now().plusDays(3))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/41UHCtWSKuL._SX373_BO1,204,203,200_.jpg")
						.get()
		);
		//11
		//*InProgres*//
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("batman@gmail.com")
						.withTitle("Batman Action ")
						.withDescription(" " +
								"-Parallel import goods\n" +
								"-body size: Height approx 160mm\n" +
								"-Target Gender: boy\n" +
								"-Age: from the 15-year-old\n" +
								"-major producing countries: China  " )
						.withPrice(1500)
						.withPublicationDate(LocalDateTime.now().minusDays(2))
						.withFinishDate(LocalDateTime.now().plusMinutes(17))
						.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/419bmZqq0OL.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
						.withBidder("victor@gmail.com")
						.withBidder("n.autalan@gmail.com")
						.withBidder("ivan@gmail.com")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("s@gmail.com")
						.withTitle("Deadpool ")
						.withDescription("" +
								"-Guaranteed 100% refund. We will do our best to satisfy our customers.\n" +
								"-New Brand, Funny Deadpool action figure, 2 style is available\n" +
								"-Size: 8cm (3.1 inch), Material: PVC, Package: With box\n" +
								"-Please let us know if you have any questions. I'll get back to you as soon as possible.\n" +
								"-Delivery time is 14-28days normally. If delay shipping is occured, we provide discount as case.  " )
						.withPrice(20)
						.withPublicationDate(LocalDateTime.now().minusDays(2))
						.withFinishDate(LocalDateTime.now().plusMinutes(17))
						.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/413qylCebbL.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
						.withBidder("n.autalan@gmail.com")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("cerati@gmail.com")
						.withTitle("Collection Batman ")
						.withDescription("" +
								"Features four different Batman action figures plus a brand-new Bruce Wayne action figure\n" +
								"All based on the designs of the bestselling line of video games\n" +
								"Includes Arkham City Bruce Wayne, Batman from Arkham Origins, Arkham Knight, Arkham City, and Arkham Asylum\n" +
								"Limited edition\n" +
								"Great for Batman comic and game fans  " )
						.withPrice(1750)
						.withPublicationDate(LocalDateTime.now().minusDays(2))
						.withFinishDate(LocalDateTime.now().plusMinutes(17))
						.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
						.withPhoto("http://2.bp.blogspot.com/-p6yn0ZU7yWM/U489GdXSxAI/AAAAAAAARC0/Z0pU2HwyAQE/s1600/400bat-15.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
						.withBidder("ivan@gmail.com")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("señorCaraDePapa@gmail.com")
						.withTitle("Mr. Potato ")
						.withDescription(" " +
								"-Popular and classic spud friend comes with all of the accessories he sported in the Toy Story movie — and even features a Tater Tush storage compartment\n" +
								"-Potato body comes with a pair of glasses, one derby hat, two ears, one pair of shoes, one set of teeth, two noses, one set of eyebrows, one tongue, one mustache, two arms and two pair of eyes accessories  " )
						.withPrice(1750)
						.withPublicationDate(LocalDateTime.now().minusDays(2))
						.withFinishDate(LocalDateTime.now().plusMinutes(17))
						.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/51DEovgRBhL.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
						.withBidder("victor@gmail.com")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("CALLEESEVIEJOLESBIANO@gmail.com")
						.withTitle("Callese Viejo Lesbiano(Remix) ")
						.withDescription("\n" +
								"    Original Release Date: October 11, 2018\n" +
								"    Release Date: October 11, 2018\n" +
								"    Label: 650562 Records DK\n" +
								"    Total Length: 3:35\n" +
								"    Genres:\n" +
								"        International > Latin Music\n" +
								"        Latin Music\n" +
								"    ASIN: B07JDD2HPN\n" +
								"    Average Customer Review: Be the first to review this item\n " )
						.withPrice(1750)
						.withPublicationDate(LocalDateTime.now().minusDays(2))
						.withFinishDate(LocalDateTime.now().plusMinutes(17))
						.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/511hxx-gKLL._SS500.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
						.withBidder("n.autalan@gmail.com")
						.withBidder("mendigo@gmail.com")
						.withBidder("rodri_milu@gmail.com")
						.withBidder("marcosLONCHUKA@gmail.com")
						.withBidder("FrankCasle@gmail.com")
						.get()
		);
		auctionDao.save(
				anyBuilderAuction
						.anyAuction()
						.withEmailAuthor("leon@gmail.com")
						.withTitle("Leon Figure ")
						.withDescription(" " +
								"-Parallel import goods\n" +
								"-body size: about H23.5cm\n" +
								"-Target Gender: unisex\n" +
								"-Age: from the 15-year-old\n" +
								"-(C) CAPCOM CO.LTD. 2012 ALL RIGHTS RESERVED." )
						.withPrice(1750)
						.withPublicationDate(LocalDateTime.now().minusDays(2))
						.withFinishDate(LocalDateTime.now().plusMinutes(17))
						.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
						.withPhoto("https://images-na.ssl-images-amazon.com/images/I/414Y8zSsOlL.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
						.get()
		);

		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("victor@Gmail.com")
				.withDescription("----El mejor juego de play(eso dicen)----")
				.withTitle("God of war")
				.withPrice(900)
				.withPublicationDate(LocalDateTime.now().minusDays(2))
				.withFinishDate(LocalDateTime.now().plusMinutes(17))
				.withInitialFinishDate(LocalDateTime.now().plusMinutes(17))
                .withPhoto("https://www.crystalcommerce.com/wp-content/uploads/2018/09/square_gow4-notxt.jpg")
						.withFirstBidder("gabi@gmail.com",5000)
				.get()
		);
		auctionDao.save(
				anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("rukia@Gmail.com")
				.withDescription("-----Dragon Ball FighterZ----------")
				.withTitle("FighterZ")
				.withPrice(200)
				.withPublicationDate(LocalDateTime.now().minusDays(2))
                .withFinishDate(LocalDateTime.now().plusHours(1))
                .withInitialFinishDate(LocalDateTime.now().plusHours(1))
                .withPhoto("https://icdn9.digitaltrends.com/image/dragon-ball-fighterz-review-1104-1920x1080.jpg")
				.withFirstBidder("gabi@gmail.com",5000)
				.withBidder("n.autalan@gmail.com")
				.withBidder("victor@gmail.com")
				.get()
		);
		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("waifu@Gmail.com")
				.withDescription("---------Manga Dragon Ball--------------------")
				.withTitle("Manga Dragon Ball")
                .withPrice(100)
                .withPhoto("https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/main_element/public/filefield_paths/dragonballdvd2.jpg?itok=uO3HI59b")
				.withPublicationDate(LocalDateTime.now())
				.withFinishDate(LocalDateTime.now().plusDays(1))
				.withInitialFinishDate(LocalDateTime.now().plusDays(1))
				.withPublicationDate(LocalDateTime.now().minusDays(2))
				.withFinishDate(LocalDateTime.now().plusMinutes(7))
				.withInitialFinishDate(LocalDateTime.now().plusMinutes(7))
				.withFirstBidder("gabi@gmail.com",5000)
				.withBidder("pepito")
				.get()
		);
		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("arkham@Gmail.com")
				.withDescription("Batman Arkham Knight is a 2015 action-adventure video game developed by Rocksteady Studios")
				.withTitle("Arkham Knight")
                .withPrice(22750)
                .withPhoto("https://www.gamepur.com/files/images/2015/batman-arkham-knight-ps4-limited-edition.jpg")
				.withPublicationDate(LocalDateTime.now().minusDays(2))
				.withFinishDate(LocalDateTime.now().plusHours(10))
				.withInitialFinishDate(LocalDateTime.now().plusHours(10))
				.withFirstBidder("gabi@gmail.com",5000)
				.get()
		);
		auctionDao.save(anyBuilderAuction
				.anyAuction()
				.withEmailAuthor("locomotion@Gmail.com")
				.withDescription("Todos los videos que quedaron de el famoso canal locomotion de anime")
				.withTitle("Locomotion")
                .withPrice(200)
                .withPhoto("http://anime.es/wp-content/uploads/2015/10/NGEvangelion-770x433.jpg")
				.withPublicationDate(LocalDateTime.now().minusDays(1))
				.withFinishDate(LocalDateTime.now().plusDays(2))
				.withInitialFinishDate(LocalDateTime.now().plusDays(2))
				.withFirstBidder("gabi@gmail.com",5000)
				.get()
		);
		//11
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
						.withBidder("SEÑOR Z")
						.withBidder("SEÑOR Y")
						.withBidder("SEÑOR X")
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
				.withPublicationDate(LocalDateTime.now().minusDays(5))
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
