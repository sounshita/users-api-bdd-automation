package com.api.hooks;

import com.api.config.ConfigFactoryManager;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @BeforeAll
    public static void createLogDirs() throws IOException {
        Files.createDirectories(Paths.get("logs"));
        Files.createDirectories(Paths.get("logs/archive"));
        Files.createDirectories(Paths.get("logs/errors"));
        Files.createDirectories(Paths.get("logs/errors/archive"));
    }

    @Before(order=0)
    public void setupBaseUrl(Scenario scenario){
        MDC.put("scenario", scenario.getName());
        String baseUrl = ConfigFactoryManager.getConfig().baseUrl();
        RestAssured.baseURI = baseUrl;
        logger.info("🔧 Base URL configured: {}", baseUrl);
    }

    @Before(order = 1)
    public void beforeScenario(Scenario scenario){
        logger.info("===== START Scenario: {} =====", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario){
        String status = scenario.isFailed() ? "❌ FAILED" : "✅ PASSED";
        logger.info("===== END Scenario: {} | Status: {} =====", scenario.getName(), status);
        attachLogsToReport();
        // Clear MDC after scenario
        MDC.clear();
    }


    private void attachLogsToReport() {
        try {
            File logFile = new File("logs/framework.log");
            if(logFile.exists()){
                ExtentCucumberAdapter.addTestStepLog("<a href='" + logFile.getAbsolutePath() + "' target='_blank'>📜 Framework Log</a>");
            }
            File errorFile = new File("logs/errors/error.log");
            if(errorFile.exists()){
                ExtentCucumberAdapter.addTestStepLog("<a href='" + errorFile.getAbsolutePath() + "' target='_blank'>❗ Error Log</a>");
            }
        } catch (Exception e) {
            logger.error("Error attaching logs to Extent report", e);
        }
    }
}
