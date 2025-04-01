package demo;

import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;
        private static final Logger logger = Logger.getLogger(TestCases.class.getName());
        private Wrappers wrappers;
        
        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @Test(enabled = true)
        public void testCase01() throws InterruptedException{
            logger.info("Starting test case: testCase01");
            driver.get("https://www.youtube.com/");
            
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://www.youtube.com/", "URL does not match!");
            logger.info("Successfully navigated to YouTube");

            By menuBar = By.xpath("(//yt-icon-button[@id='guide-button'])[1]");
            wrappers.click(menuBar);
            //Thread.sleep("2000");
        
            By aboutLink = By.xpath("//a[contains (text(), 'About')]");
            wrappers.click(aboutLink);
            logger.info("Clicked on 'About'");
    
            By aboutMessage = By.xpath("//section[@class='ytabout__content']");
            String messageText = wrappers.getText(aboutMessage);
            System.out.println("About Page Message: " + messageText);
            
            logger.info("End test case: testCase01");
        }

        @Test(enabled = true)
        public void testCase02() throws InterruptedException{
                logger.info("Starting test case: testCase01");
                driver.get("https://www.youtube.com/");

                By menuBar = By.xpath("(//yt-icon-button[@id='guide-button'])[1]");
                wrappers.click(menuBar);

                By moviesOption = By.xpath("//yt-formatted-string[contains (text(), 'Movies')]");
                wrappers.scrollToElement(moviesOption);
                wrappers.click(moviesOption);
                Thread.sleep(2000);

                By rightArrow = By.xpath("//button[contains (@aria-label, 'Next')]");
                while(wrappers.isDisplayed(rightArrow)) {
                wrappers.click(rightArrow);
                Thread.sleep(1000);
                }

                List<WebElement> movies = driver.findElements(By.xpath("//span[contains (@id, 'video-title')]"));
                List<WebElement> ratings = driver.findElements(By.xpath("//div[contains(@aria-label, 'U') or contains(@aria-label, 'A') or contains(@aria-label, 'U/A')]"));
                List<WebElement> categories = driver.findElements(By.xpath("//ytd-grid-movie-renderer[@class='style-scope yt-horizontal-list-renderer']//span[contains(@class, 'grid-movie-renderer-')]"));

                SoftAssert softAssert = new SoftAssert();
                if (!movies.isEmpty()) {
                        int lastIndex = movies.size() - 1;
                        String movieTitle = movies.get(lastIndex).getText();
                        String ratingText = ratings.get(lastIndex).getText();
                        boolean isAcceptable = ratingText.contains("A") || ratingText.contains("U/A");
                        boolean hasCategory = !categories.get(lastIndex).getText().isEmpty();
            
                        softAssert.assertTrue(isAcceptable, "Movie " + movieTitle + " does not have an acceptable rating (A or U/A)");
                        softAssert.assertTrue(hasCategory, "Movie " + movieTitle + " does not have a valid category (Comedy, Animation, Drama, etc.)");
                    } else {
                        logger.warning("No movies found in the 'Top Selling' section.");
                    }
        
                softAssert.assertAll();
                logger.info("End test case: testCase02");


        }

        @Test(enabled = true)
        public void testCase03() throws InterruptedException {
        logger.info("Starting test case: testCase03");
        //driver.get("https://www.youtube.com");
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By menuBar = By.xpath("(//yt-icon-button[@id='guide-button'])[1]");
        wrappers.click(menuBar);
        //locating the musicbutton and clicking on it
        By musicButton = By.xpath("(//a[contains (@title, 'Music')])[1]");
        wrappers.click(musicButton);
        Thread.sleep(4000);
        // locate the first section
        By firstSection = By.xpath("//ytd-rich-section-renderer[@class='style-scope ytd-rich-grid-renderer'][1]//ytd-rich-shelf-renderer[contains(@elements-per-row, '4')]//span[@class='style-scope ytd-rich-shelf-renderer']");
        wrappers.waitForVisibility(firstSection);
        wrappers.scrollToElement(firstSection);
        Thread.sleep(2000);
        
        //  // Locate all playlists in the first section
        //  By allPlaylists = By.xpath("//div[@id='contents-container']//div[@id='contents']//ytd-rich-item-renderer[@class='style-scope ytd-rich-shelf-renderer']//div[@id='content']//yt-lockup-view-model");
        //  List<WebElement> playlists = driver.findElements(allPlaylists);
        //  wait.until(ExpectedConditions.visibilityOfAllElements(playlists));
        //  if (playlists.isEmpty()) {
        //      logger.warning("No playlists found in the first section!");
        //      return;
        //  }
 
         // Get the last playlist in the first row
         WebElement playlistName = driver.findElement(By.xpath("((//ytd-rich-shelf-renderer)[1]//ytd-rich-item-renderer[4]//span)[2]"));
         String txtAlbumname=playlistName.getText().toString();                             
        System.out.println("Playlist Name : " + txtAlbumname);

 
         // Locate track count for the last playlist
        WebElement videoCount = driver.findElement(By.xpath("(//ytd-rich-shelf-renderer)[1]//ytd-rich-item-renderer[4]//div[@class='badge-shape-wiz__text']"));
        String txtNoOfSongs=videoCount.getText().toString();
        String[] songs=txtNoOfSongs.split(" ");
        Integer NumberSongs=Integer.parseInt(songs[0]);
        
                                                 
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(NumberSongs<=50);
        
        // Assert All
        softAssert.assertAll();
        logger.info("End test case: testCase03");
}

        @Test(enabled = true)
        public void testCase04() throws InterruptedException{
            logger.info("Start test case: testCase04");
            //driver.get("https://www.youtube.com/");
            By menuBar = By.xpath("(//yt-icon-button[@id='guide-button'])[1]");
            wrappers.click(menuBar);
            logger.info("Clicking on menyBar Button: "+menuBar);

            By newsButton = By.xpath("//a[contains (@title, 'News')]");
            wrappers.click(newsButton);
            Thread.sleep(2000);
            logger.info("Clicking on news Button: "+newsButton);

            By latestNewsPostsHeading = By.xpath("//span[contains (text(), 'Latest news posts')]");
            wrappers.scrollToElement(latestNewsPostsHeading);
            wrappers.waitForVisibility(latestNewsPostsHeading);
            
            logger.info("Scrolling to Latest News Section: "+latestNewsPostsHeading);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> newsPosts = driver.findElements(By.xpath("(//div[@id='content' and contains(@class, 'ytd-rich-item-renderer')]/ytd-post-renderer)[position()<=3]"));
            wait.until(ExpectedConditions.visibilityOfAllElements(newsPosts));
            
            int totalLikes = 0;
            for (int i = 0; i<3 && i < newsPosts.size(); i++) {
                WebElement post = newsPosts.get(i);
    
                // Extract title
                String title = post.findElement(By.xpath("//a[contains (@id, 'author-text')]")).getText();
                Thread.sleep(2000);
                logger.info("Getting the title through .getText() method: "+title);
                // Extract body
                String body = post.findElement(By.id("home-content-text")).getText();
                Thread.sleep(2000);
                logger.info("Getting the body text through .getText() method: "+body);
    
                // Extract likes (handle case where likes may not be present)
                List<WebElement> likesElement = post.findElements(By.xpath(".//span[contains (@id, 'vote-count-middle')]"));
                int likes = 0; // Default value
                if (likesElement.size() > 0) {
                    String likesText = likesElement.get(0).getText().trim();
                    if (!likesText.isEmpty() && likesText.matches("\\d+")) { // Check if it's a valid number
                        likes = Integer.parseInt(likesText);
                    }
                }
    
                totalLikes += likes;
    
                // Print the extracted details
                System.out.println("News Post: " + (i + 1));
                System.out.println("Title: " + title);
                System.out.println("Body: " + body);
                System.out.println("Likes: " + likes);
            }
            System.out.println("Total Likes on First 3 Posts: " + totalLikes);
            logger.info("End test case: testCase04");
        }
        
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
                wrappers = new Wrappers(driver);
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}