package hotelmanage;

import hotelmanage.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository PaymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentRequested_Payment(@Payload CompletePayment CompletePayment){
        if(CompletePayment.isMe()){
            System.out.println("##### listener PaymentCompleted : " + CompletePayment.toJson());
            Payment Payment = new Payment();

            Payment.setPaymentStatus("Y");
            PaymentRepository.save(Payment);

        }
    }

}
