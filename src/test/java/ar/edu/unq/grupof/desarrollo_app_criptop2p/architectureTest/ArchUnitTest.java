package ar.edu.unq.grupof.desarrollo_app_criptop2p.architectureTest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class ArchUnitTest {
    private JavaClasses javaClasses;
    @BeforeEach
    public void init() {

        this.javaClasses = new ClassFileImporter().importPackages("ar.edu.unq.grupof.desarrollo_app_criptop2p");
    }
    @Test
    public void persistenceClassWithRepositoryAnnotation() {
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..persistence")
                .should()
                .beAnnotatedWith(Repository.class);

        rule.check(this.javaClasses);
    }

    @Test
    public void restWebServiceClassWithRestControllerAnnotation() {
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..rest_webservice")
                .should()
                .beAnnotatedWith(RestController.class);

        rule.check(this.javaClasses);
    }

    @Test
    public void serviceClassWithServiceAnnotation() {
        ArchRule rule = classes()
                .that()
                .resideInAPackage("..service.impl")
                .should()
                .beAnnotatedWith(Service.class);

        rule.check(this.javaClasses);
    }

    @Test
    public void controllerResideInCorrectPackage() {
        ArchRule rule = classes()
                .that()
                .haveSimpleNameEndingWith("Controller")
                .should()
                .resideInAPackage("..rest_webservice");

        rule.check(this.javaClasses);
    }

    @Test
    public void servicesResideInCorrectPackage() {
        ArchRule rule = classes()
                .that()
                .haveSimpleNameEndingWith("Service").or().haveSimpleNameEndingWith("ServiceImpl")
                .should()
                .resideInAPackage("..service..");

        rule.check(this.javaClasses);
    }

    @Test
    public void servicesImplementToServices() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("..impl..").should().beInterfaces();

        rule.check(this.javaClasses);
    }

    @Test
    public void controllerDependToService() {

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..rest_webservice")
                .should().dependOnClassesThat()
                .resideInAPackage("..service..");

        rule.check(this.javaClasses);
    }

}
