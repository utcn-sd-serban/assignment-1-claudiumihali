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
public class Question implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    @Column(name = "stack_overflow_user_id")
    private Integer sOUserId;
    private String title;
    private String text;
    private LocalDateTime creationDateTime;

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Question))
            return false;
        else {
            Question q = (Question) obj;
            return q.getId().equals(questionId);
        }
    }

    @Override
    public int hashCode() {
        return questionId;
    }

    @Override
    public Integer getId() {
        return questionId;
    }

    @Override
    public void setId(Integer id) {
        questionId = id;
    }

    @Override
    public String getAssociatedTableName() {
        return "question";
    }

    @Override
    public Map<String, Object> getColumnFieldValueAssociations() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("stack_overflow_user_id", sOUserId);
        m.put("title", title);
        m.put("text", text);
        m.put("creation_date_time", creationDateTime);
        return m;
    }

    @Override
    public void updateEntityFields(DatabaseEntity entity) {
        if (entity instanceof Question) {
            Question e = (Question) entity;
            if (e.getSOUserId() != null)
                sOUserId = e.getSOUserId();
            if (e.getTitle() != null)
                title = e.getTitle();
            if (e.getText() != null)
                text = e.getText();
            if (e.getCreationDateTime() != null)
                creationDateTime = e.getCreationDateTime();
        }
    }
}
