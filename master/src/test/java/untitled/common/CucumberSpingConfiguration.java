package untitled.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import untitled.MasterApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { MasterApplication.class })
public class CucumberSpingConfiguration {}
