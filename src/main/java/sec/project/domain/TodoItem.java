package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class TodoItem extends AbstractPersistable<Long> {

    @ManyToOne
    private Account account;
    
    private String task;

    public TodoItem() {
        super();
    }

    public TodoItem(Account account, String name) {
        this();
        this.account = account;
        this.task = name;
    }
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    

}
