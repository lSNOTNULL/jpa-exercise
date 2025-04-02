package org.example.jpaexercise.controller;

import jakarta.persistence.EntityManager;
import org.example.jpaexercise.config.JPAConfig;
import org.example.jpaexercise.model.dto.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {
    final JPAConfig jpaConfig; // 이게 의존성 주입이 되네;;;

    public IndexController(JPAConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @GetMapping
    public String index(Model model) {
        try (EntityManager entityManager = jpaConfig.getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            // 실제 DB 작업 수행 , 사용 후 닫아야 하지만 try-with-resources는 finally에서 자동으로 닫힘, begin -> 작업 시작

            Student student = new Student();
            student.setName(LocalDateTime.now().toString());
            entityManager.persist(student); // student 객체를 persistence Context에 등록+ DB에 저장 준비
            entityManager.getTransaction().commit();
            // 트랜잭션 커밋 -> persist 된 내용이 실제 DB에 반영 (INSERT SQL 실행)


            List<Student> students = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
                model.addAttribute("students", students);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}