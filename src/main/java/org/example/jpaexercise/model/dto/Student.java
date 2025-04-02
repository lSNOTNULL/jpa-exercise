package org.example.jpaexercise.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// record 에서 class로 변경. getter setter
@Entity // DB 테이블과 매핑되는 엔티티임을 나타냄
public class Student {
        @Id // id 가 테이블의 PK 임을 나타냄
        @GeneratedValue // PK를 자동으로 생성
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
}
