package BookRentalSystem;

import BookRentalSystem.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    UserRepository userRepo;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRented_ChangeRentalStatus(@Payload Rented rented){
        /*대여 완료 시 대여id 업데이트*/
        if(rented.isMe()){
            System.out.println("대여완료-->user Policy : " + rented.toJson());
            User user = userRepo.findById(rented.getUserId()).get();
            user.setRentalId(rented.getId());

            userRepo.save(user);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_ChangeRentalStatus(@Payload Returned returned){
        /*반납 시 대여 id 삭제*/
        if(returned.isMe()){
            System.out.println("##### listener ChangeRentalStatus : " + returned.toJson());
            User user = userRepo.findById(returned.getUserId()).get();
            user.setRentalId(null);

            userRepo.save(user);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_ChangeRentalStatus(@Payload Cancelled cancelled){
        /*대여 취소 시 대여 id 삭제*/
        if(cancelled.isMe()){
            System.out.println("##### listener ChangeRentalStatus : " + cancelled.toJson());
            User user = userRepo.findById(cancelled.getUserId()).get();
            user.setRentalId(null);

            userRepo.save(user);
        }
    }

}
