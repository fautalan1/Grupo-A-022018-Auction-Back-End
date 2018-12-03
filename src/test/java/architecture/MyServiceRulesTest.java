package architecture;

import appllication.service.AuctionService;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Example;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


@Category(Example.class)
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "appllication/service")
public class MyServiceRulesTest {



    @ArchTest
    static final ArchRule the_services_must_have_the_Component_annotation =
            classes().that().arePublic()
                    .should().beAnnotatedWith(Component.class);

    @ArchTest
    static final ArchRule the_services_must_have_the_transactional_annotation =
            classes().that().arePublic()
                    .should().beAnnotatedWith(Transactional.class);



}
