package ro.utcn.sd.mca.SD2019StackOverflowApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;
    private Integer questionId;
    @Column(name = "stack_overflow_user_id")
    private Integer sOUserId;
    private String text;
    private LocalDateTime creationDateTime;

    @Override
    public Integer getId() {
        return answerId;
    }

    @Override
    public void setId(Integer id) {
        answerId = id;
    }

    @Override
    public String getAssociatedTableName() {
        return "answer";
    }

    @Override
    public Map<String, Object> getColumnFieldValueAssociations() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("question_id", questionId);
        m.put("stack_overflow_user_id", sOUserId);
        m.put("text", text);
        m.put("creation_date_time", creationDateTime);
        return m;
    }

    @Override
    public void updateEntityFields(DatabaseEntity entity) {
        if (entity instanceof Answer) {
            Answer e = (Answer) entity;
            if (e.getQuestionId() != null)
                questionId = e.getQuestionId();
            if (e.getSOUserId() != null)
                sOUserId = e.getSOUserId();
            if (e.getText() != null)
                text = e.getText();
            if (e.getCreationDateTime() != null)
                creationDateTime = e.getCreationDateTime();
        }
    }
}
