package ExcelHomework;

import com.codeborne.selenide.Configuration;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestClass {
    @Test(dataProvider="LoginData")
    public void navigate(String name, String lastName, String mobile){
        Configuration.startMaximized=true;
        open("https://demoqa.com/automation-practice-form");
        $(By.xpath("//*[@id='firstName']")).sendKeys(name);
        $(By.xpath("//*[@id='lastName']")).sendKeys(lastName);
        $(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]/label")).click();
        $(By.xpath("//*[@id='userNumber']")).sendKeys(mobile);
    }


    @DataProvider(name = "LoginData")
    public Object[][] getData(){
        return new Object[][] {{"John", "smith","5558578965"},
                               {"bob", "pitt","5558578965"},
                               {"nick", "cave","5558578965"}};
    }


}
