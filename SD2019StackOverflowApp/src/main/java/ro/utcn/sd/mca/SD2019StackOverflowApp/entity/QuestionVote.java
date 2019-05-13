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
public class QuestionVote implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionVoteId;
    @Column(name = "stack_overflow_user_id")
    private Integer sOUserId;
    private Integer questionId;
    private String voteType;

    @Override
    public Integer getId() {
        return questionVoteId;
    }

    @Override
    public void setId(Integer id) {
        questionVoteId = id;
    }

    @Override
    public String getAssociatedTableName() {
        return "question_vote";
    }

    @Override
    public Map<String, Object> getColumnFieldValueAssociations() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("stack_overflow_user_id", sOUserId);
        m.put("question_id", questionId);
        m.put("vote_type", voteType);
        return m;
    }

    @Override
    public void updateEntityFields(DatabaseEntity entity) {
        if (entity instanceof QuestionVote) {
            QuestionVote e = (QuestionVote) entity;
            if (e.getSOUserId() != null)
                sOUserId = e.getSOUserId();
            if (e.getQuestionId() != null)
                questionId = e.getQuestionId();
            if (e.getVoteType() != null)
                voteType = e.getVoteType();
        }
    }
}
