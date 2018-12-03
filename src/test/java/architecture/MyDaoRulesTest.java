package architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Example;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@Category(Example.class)
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "appllication/repository")
public class MyDaoRulesTest {
    @ArchTest
    public static final ArchRule DAOs_must_reside_in_a_dao_package =
            classes().that().haveNameMatching(".*dao").should().resideInAPackage("..repository..")
                    .as("DAOs should reside in a package '..dao..'");

}