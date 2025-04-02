package org.example.jpaexercise.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component // 이 클래스를 Spring의 Bean으로 등록해 다른 Component(IndexController)에서  사용 가능
public class JPAConfig {

    private EntityManagerFactory entityManagerFactory;

    public JPAConfig() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        Map<String, String> props = new HashMap<>();
        // DB 연결 정보를 담을 Map 생성
        props.put("javax.persistence.jdbc.url", dotenv.get("DB_URL"));
        props.put("javax.persistence.jdbc.user", dotenv.get("DB_USER"));
        props.put("javax.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));
        props.put("javax.persistence.jdbc.driver", dotenv.get("DB_DRIVER"));

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit",props);
            // persistence.xml 에서 MyPersistenceUnit 이름 설정을 찾고, props에 담긴 정보를 ${...} 에 덮어씌움.
        } catch (Exception e) {
            // 예외 처리: EntityManagerFactory 생성 실패 시 로그 기록 또는 예외 던지기
            System.err.println("EntityManagerFactory 생성 실패: " + e.getMessage());
            throw new RuntimeException("EntityManagerFactory 생성 실패", e);
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory가 초기화되지 않았습니다.");
        }
        return entityManagerFactory;
    }
}
