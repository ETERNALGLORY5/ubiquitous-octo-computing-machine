package evon.api.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    private final static Integer LENGTH = 6;

    @Value("${app.default.otp}")
    public Integer otp;

    @Value("${app.default.otpValue}")
    public Boolean otpValue;

    private static Logger logger = LoggerFactory.getLogger(OtpService.class);


    public Integer generateOtp() {
        logger.info("Otp is : {}", otp);
        logger.info("Otp Value is:{}", otpValue);
        if (otpValue == true) {
            logger.info("Otp:{}", otp);
            return otp;
        } else {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return Integer.parseInt(oneTimePassword.toString().trim());
        }
    }
}
