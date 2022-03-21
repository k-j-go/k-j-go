package com.azunitech.rx.rxtwo;

import com.azunitech.rx.rxtwo.persistent.PersistentConfig;
import com.azunitech.rx.rxtwo.services.ServiceConfig;
import com.azunitech.rx.rxtwo.usecases.UseCasesConfig;
import com.azunitech.rx.rxtwo.web.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import({WebConfig.class, PersistentConfig.class, UseCasesConfig.class, ServiceConfig.class})
class RxtwoApplicationTests {

    @Test
    void contextLoads() {
    }

}
