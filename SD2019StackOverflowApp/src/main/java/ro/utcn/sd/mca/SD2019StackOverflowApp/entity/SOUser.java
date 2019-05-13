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
@Table(name = "stack_overflow_user")
public class SOUser implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stack_overflow_user_id")
    private Integer sOUserId;
    private String username;
    private String password;

    @Override
    public Integer getId() {
        return sOUserId;
    }

    @Override
    public void setId(Integer id) {
        sOUserId = id;
    }

    @Override
    public String getAssociatedTableName() {
        return "stack_overflow_user";
    }

    @Override
    public Map<String, Object> getColumnFieldValueAssociations() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("username", username);
        m.put("password", password);
        return m;
    }

    @Override
    public void updateEntityFields(DatabaseEntity entity) {
        if (entity instanceof SOUser) {
            SOUser e = (SOUser) entity;
            if (e.getUsername() != null)
                username = e.getUsername();
            if (e.getPassword() != null)
                password = e.getPassword();
        }
    }
}
