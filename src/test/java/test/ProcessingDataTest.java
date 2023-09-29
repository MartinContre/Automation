package test;

import aquality.selenium.core.logging.Logger;
import models.TestModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.base.BaseTestC2;

public class ProcessingDataTest extends BaseTestC2 {

    @Test(dataProvider = "randomTests", dataProviderClass = BaseTestC2.class)
    public void simulateTest(TestModel testModel) {
        Logger.getInstance().info("Simulating executing test: " +  testModel.getName());
        Assert.assertEquals(15, 16, "There are not equals");
    }
}
