package BookRentalSystem;

import java.sql.Timestamp;
import java.util.Date;

public class UserRegistered extends AbstractEvent {

    private Long id;
    private String userName;
    private Date regTime;
    private Long rentalId;

    public UserRegistered(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime() {
        Date date = new Date();
        this.regTime = new Timestamp(date.getTime());
        System.out.println("regTime!! : " + this.regTime);
    }

    public void setRentalId(Long rentalId){ this.rentalId = rentalId;}
    public Long getRentalId() {return rentalId;}
}
