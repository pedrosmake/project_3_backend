package org.example;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.example.auth.JwtAuthenticator;
import org.example.auth.RoleAuthorizer;
import org.example.controllers.AuthController;
import org.example.controllers.EmployeeController;
import org.example.daos.AuthDao;
import org.example.daos.EmployeeDao;
import org.example.controllers.ProjectController;
import org.example.daos.ProjectDao;
import org.example.models.JwtToken;
import org.example.services.AuthService;
import org.example.services.EmployeeService;
import org.example.services.ProjectService;
import org.example.controllers.ClientController;
import org.example.controllers.TestController;
import org.example.daos.ClientDao;
import org.example.daos.TestDao;
import org.example.services.ClientService;
import org.example.services.TestService;
import org.example.validators.EmployeeValidator;
import org.example.validators.RegisterValidator;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.security.Key;

public class TestApplication extends Application<TestConfiguration> {
    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }
    @Override
    public String getName() {
        return "Test";
    }
    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final TestConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }
    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        Key jwtKey = Jwts.SIG.HS256.key().build();
        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<JwtToken>()
                        .setAuthenticator(new JwtAuthenticator(jwtKey))
                        .setAuthorizer(new RoleAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()
        ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(
                JwtToken.class));

        environment.jersey()
                .register(new TestController(new TestService(new TestDao())));
        environment.jersey()
                .register(new EmployeeController(
                        new EmployeeService(
                                new EmployeeDao(),
                                new EmployeeValidator())));
        environment.jersey()
                .register(new ProjectController(
                        new ProjectService(new ProjectDao())));
        environment.jersey()
                .register(new ClientController(new ClientService(
                        new ClientDao())));
        environment.jersey()
                .register(new AuthController(new AuthService(
                        new AuthDao(), new RegisterValidator(), jwtKey)));

    }

}
