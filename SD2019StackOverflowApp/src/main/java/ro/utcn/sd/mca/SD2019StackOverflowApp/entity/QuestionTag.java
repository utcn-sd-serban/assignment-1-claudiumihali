package ro.utcn.sd.mca.SD2019StackOverflowApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionTag implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionTagId;
    private Integer questionId;
    private Integer tagId;

    @Override
    public Integer getId() {
        return questionTagId;
    }

    @Override
    public void setId(Integer id) {
        questionTagId = id;
    }

    @Override
    public String getAssociatedTableName() {
        return "question_tag";
    }

    @Override
    public Map<String, Object> getColumnFieldValueAssociations() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("question_id", questionId);
        m.put("tag_id", tagId);
        return m;
    }

    @Override
    public void updateEntityFields(DatabaseEntity entity) {
        if (entity instanceof QuestionTag) {
            QuestionTag e = (QuestionTag) entity;
            if (e.getQuestionId() != null)
                questionId = e.getQuestionId();
            if (e.getTagId() != null)
                tagId = e.getTagId();
        }
    }
}
