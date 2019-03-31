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
public class AnswerVote implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerVoteId;
    @Column(name = "stack_overflow_user_id")
    private Integer sOUserId;
    private Integer answerId;
    private String voteType;

    @Override
    public Integer getId() {
        return answerVoteId;
    }

    @Override
    public void setId(Integer id) {
        answerVoteId = id;
    }

    @Override
    public String getAssociatedTableName() {
        return "answer_vote";
    }

    @Override
    public Map<String, Object> getColumnFieldValueAssociations() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("stack_overflow_user_id", sOUserId);
        m.put("answer_id", answerId);
        m.put("vote_type", voteType);
        return m;
    }

    @Override
    public void updateEntityFields(DatabaseEntity entity) {
        if (entity instanceof AnswerVote) {
            AnswerVote e = (AnswerVote) entity;
            if (e.getSOUserId() != null)
                sOUserId = e.getSOUserId();
            if (e.getAnswerId() != null)
                answerId = e.getAnswerId();
            if (e.getVoteType() != null)
                voteType = e.getVoteType();
        }
    }
}
