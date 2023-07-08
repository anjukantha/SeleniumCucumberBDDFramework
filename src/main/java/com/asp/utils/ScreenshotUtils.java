package com.asp.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.asp.constants.FrameworkConstants;
import com.asp.driver.DriverManager;

/**
 * Utility to take screenshots.
 * 
 * @author Anjan S P
 */
public final class ScreenshotUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ScreenshotUtils() {
	}

	/**
	 * Captures screenshot of the current page, constructs a base64 string from the
	 * image and return to the caller.
	 * 
	 * @author Anjan S P
	 * @return Image in the form of Base64 String which can be appended directly to
	 *         report
	 */
	public static String getBase64Image() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * Used to capture the screenshot and saves the screenshot to provided folder
	 * location inside test-results folder.
	 * 
	 * @param folderName
	 * @return
	 * @throws IOException
	 */
	public static String captureSheetshotAsPNG(String folderName) throws IOException {
		File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		String destPath = FrameworkConstants.getCurrentresultsPath() + "/Screenshots/" + folderName + "/";
		String destFilePath = "";
		File file;
		for (int i = 0; i < 200; i++) {
			destFilePath = destPath + FrameworkConstants.getTestName() + "(" + i + ").png";
			file = new File(destFilePath);
			if (!file.isFile()) {
				break;
			}
		}
		FileUtils.copyFile(src, new File(destFilePath));
		return addEnvironmentDetails(destFilePath);
	}

	/**
	 * Used to add the current URL and Date & Time stamp on the top of the image
	 * 
	 * @param destFilePath
	 * @return file path
	 * @throws IOException
	 */
	private static String addEnvironmentDetails(String destFilePath) throws IOException {
		BufferedImage image = ImageIO.read(new File(destFilePath));
		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(12f));
		g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 2000, 25);
		g.getColor();
		g.setColor(Color.RED);
		g.drawString("URL: " + DriverManager.getDriver().getCurrentUrl(), 10, 10);
		g.drawString("Date & Time: " + new Date(), 10, 22);
		g.dispose();
		ImageIO.write(image, "png", new File(destFilePath));
		return destFilePath;
	}
}
