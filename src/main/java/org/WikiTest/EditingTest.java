package org.WikiTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class EditingTest extends BaseTest{

// 5.1 - Login Editing Confirmation
    @Test(priority = 1)
    public void loginEditingConfirmation() {
    }

// 5.2 - Previewing Edits
    @Test(priority = 2)
    public void previewEdits() {
    }

// 5.3 - Administrator Only editing
    @Test(priority = 3)
    public void administratorOnlyEditing() {
    }

// 5.4 - Wikipedia Link Insert
    @Test(priority = 4)
    public void linkInsert() {
    }

// 5.5 - Visual Editor
    @Test(priority = 5)
    public void visualEditor() {
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
