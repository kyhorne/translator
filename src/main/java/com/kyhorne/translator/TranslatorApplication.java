package com.kyhorne.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TranslatorApplication {
  public static void main(String[] args) {
    SpringApplication.run(TranslatorApplication.class, args);
    var expression = "1 + 2 + 3 * 5";
    SampleTranslator.promptForCompilation(expression);
    SampleTranslator.promptForEvaluation(expression);
  }
}
