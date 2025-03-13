package com.example.koolcard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.client.provider.keycloak.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public KeycloakConverter customKeycloakConverter() {
        return new KeycloakConverter();
    }
    //JwtDecoder, qui est responsable de décoder et valider les tokens JWT reçus dans les requêtes HTTP.
// JWT (JSON Web Token) est un standard d'authentification basé sur des tokens signés, utilisé pour vérifier les utilisateurs ou les systèmes.
    @Bean
    public JwtDecoder decode() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }
    //JwtAuthenticationProvider, qui est un fournisseur d'authentification utilisé par Spring Security.
    //Ce composant utilise le JwtDecoder (créé dans la méthode précédente) pour authentifier les utilisateurs en fonction des JWT reçus.

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(decode());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder =
                new MvcRequestMatcher.Builder(introspector);
        // Désactive la protection CSRF (Cross-Site Request Forgery)
        // CSRF est une attaque où un utilisateur malveillant tente de faire exécuter des actions non autorisées par un utilisateur authentifié.
        // Ici, on la désactive parce qu'une API REST sans état (stateless) n'en a généralement pas besoin.
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // Désactive la protection CORS (Cross-Origin Resource Sharing)
        // CORS permet de restreindre l'accès aux ressources d'un serveur à des domaines spécifiques.
        // On désactive ici CORS, ce qui peut être géré par un autre middleware ou une configuration spécifique.
        httpSecurity.cors(AbstractHttpConfigurer::disable);
// Configuration des autorisations d'accès aux requêtes HTTP
        httpSecurity
                // Permet toutes les requêtes OPTIONS sans authentification
                // Les requêtes OPTIONS sont utilisées pour gérer les pré-demandes CORS par les navigateurs.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.OPTIONS, "/**")).permitAll()
                        // .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/user/getAllusers")).permitAll()
                         .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/file/upload")).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/user/getAllusers")).hasAuthority("GROUP_KANTRA")
                        .anyRequest().authenticated()
                )  // Configuration de l'authentification via OAuth2 et des tokens JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        // Décode les tokens JWT pour authentifier les utilisateurs
                        .jwt(jwt -> jwt.decoder(decode()).jwtAuthenticationConverter(customKeycloakConverter()))
                )
                // Définition de la politique de gestion des sessions comme "stateless"
                // "Stateless" signifie qu'aucune session n'est conservée entre les requêtes. Chaque requête est authentifiée par un token JWT.
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        // Retourne la chaîne de filtres de sécurité configurée
        return httpSecurity.build();
    }

}