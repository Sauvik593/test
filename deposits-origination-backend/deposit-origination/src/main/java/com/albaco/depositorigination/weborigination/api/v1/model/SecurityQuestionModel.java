package com.albaco.depositorigination.weborigination.api.v1.model;

import com.albaco.depositorigination.common.enums.SecurityQuestionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "security_question_answers")
@Getter
@Setter
public class SecurityQuestionModel extends BaseModel {

    @Column(nullable = false, length = 20)
    private String customerId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SecurityQuestionEnum question;

    @Column(nullable = false, length = 100)
    private String answer;
}
