package untitled.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import untitled.PurchaseApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { PurchaseApplication.class })
public class CucumberSpingConfiguration {}
