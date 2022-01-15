package zhku.graduation.core.config.check;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author qr
 * @date 2022/1/15 15:08
 */
@Configuration
public class FastValidatorConf {

    /**
     * 自定义校验Bean，设置快速校验，只要有一个不符合规定的参数就直接进行判断
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 开启快速校验
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
