package br.com.shoestock.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"features",},
		glue = "br.com.userede.stepdefinition", 
		monochrome = true, plugin = {"pretty", "html:Reports/cucumber" },
		tags={"~@ignore", "@incluirProduto"})
public class RunnerTestExample {
}
