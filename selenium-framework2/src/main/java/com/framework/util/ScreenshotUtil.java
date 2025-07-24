package com.framework.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    private static boolean folderPrepared = false;

    public static String takeScreenshot(WebDriver driver, String testName) {
        prepareScreenshotFolder(); // ensure folder is ready

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = "reports/screenshots/";
        String filePath = folderPath + testName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);
            FileUtils.copyFile(src, dest);

            System.out.println("📸 Screenshot saved to: " + dest.getAbsolutePath());
            return filePath;

        } catch (IOException e) {
            System.err.println("⚠️ Could not save screenshot: " + e.getMessage());
            return null;
        }
    }

    private static void prepareScreenshotFolder() {
        if (folderPrepared) return;

        String screenshotFolder = "reports/screenshots";
        File screenshotDir = new File(screenshotFolder);

        if (screenshotDir.exists() && screenshotDir.isDirectory()) {
            String archiveFolder = "reports/archive/screenshots_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File archiveDir = new File(archiveFolder);

            try {
                FileUtils.copyDirectory(screenshotDir, archiveDir);  // move old to archive
                FileUtils.cleanDirectory(screenshotDir);             // clean for new run
                System.out.println("📦 Archived old screenshots to: " + archiveDir.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("⚠️ Failed to archive old screenshots: " + e.getMessage());
            }
        } else {
            screenshotDir.mkdirs(); // create folder if missing
        }

        folderPrepared = true;
    }
}
