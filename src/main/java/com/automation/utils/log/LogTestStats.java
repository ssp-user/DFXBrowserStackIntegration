package com.automation.utils.log;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import com.automation.pages.PageManager;
//import org.apache.poi.xwpf.usermodel.UpdateEmbeddedDoc;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import com.automation.utils.otherUtils.HttpStats;

    public class LogTestStats  extends LogUtils{

        private static int numberOfTests = 0;
        private static ArrayList<String> passedDMS;
        private static ArrayList<String> failedDMS;
        private static String task = null;
        private static boolean FCA;

        public static void logTestEnv(){																						// Log Testing Environment
            logInfo("Testing Environment", env, false);
        }


        public static void getBrowserName(WebDriver driver){																	// Get the current browser name
            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            browserName = cap.getBrowserName();
            System.out.println(browserName);
            logMsgOnly("Testing browser type : " + browserName);
        }


        public static String getTestingEnv(){																					// Return the Testing Environment
            updateEnv();
            return env;
        }

        public static String getTestingProduct(String packageName){																// Return the Application Under Test
            getAUT(packageName);
            return product;
        }


        public static String getTestingBrowser(){																				// Return Browser Type
            String browser = browserName;
            if(browser.equalsIgnoreCase("chrome")){
                browser = "Chrome";
            } else if(browser.equalsIgnoreCase("firefox")){
                browser = "FireFox";
            } else if(browser.equalsIgnoreCase("internet explorer")){
                browser = "Internet Explorer";
            } else {
                browser = "Unknow Browser: " + browserName;
            }
            return browser;
        }


        public static void passCount(String dms){																				// Count Passed tests
            passedDMS.add(dms);
        }


        private static void getAUT(String packageName){

            System.out.println("PackageName is : " + packageName);

            if(packageName.contains("advisorDashboard")){																		// Advisor Dashboard
                product = "Advisor Dashboard";
            } else if(packageName.contains("wiAdvisor.Legacy")){																// wiAdvisor - Legacy
                product = "wiAdvisor";
            } else if(packageName.contains("wiAdvisor")){																		// wiAdvisor
                product = "wiAdvisor (New)";
            } else if(packageName.contains("appointmentLedger")){																// Appointment Ledger
                product = "Appointment Ledger";
            } else if(packageName.contains("crossFunctional")){
                product = "Cross Functions";
            }
            else {
                LogUtils.logMsgOnly("package Name is : " + packageName);
                product = "Unknown";
            }
        }

        private static void updateEnv(){
            WebDriver driver = PageManager.getDriver();
            String node = HttpStats.getX_Node(driver);
            String url = driver.getCurrentUrl();

            if(node.toLowerCase().contains("test2")){
                env = "TEST 2";
                FCA = false;
            } else if(node.toLowerCase().contains("test")){
                env = "TEST 1";
                FCA = true;
            } else if(node.toLowerCase().contains("aws.stg")){
                env = "AWS STAGE";
                FCA = true;
            } else if(node.toLowerCase().contains("stg")){
                env = "STAGE";
                FCA = true;
            } else if(node.toLowerCase().contains(".prod2")){
                env = "PROD 2";
                FCA = false;
            } else if(node.toLowerCase().contains("prod2-")){
                env = "PROD 2(AWS)";
                FCA = false;
            } else if(node.toLowerCase().contains("pilot")){
                env = "PILOT 1";
                FCA = true;
            } else if(node.toLowerCase().contains("prod-")){
                env = "PROD 1";
                FCA = true;
            } else if(node.toLowerCase().contains("emea")){
                env = "Middle East";
                FCA = true;
            } else if(!node.contains(".") && node != "null" && !node.isEmpty()){
                env = "PROD 1";
                FCA = true;
            } else if(node.equalsIgnoreCase("null") && (url.contains("https://test.wiadvisor.dcctools.com") || url.contains("https://uidev.wiadvisor"))){
                env = "TEST 1";
                FCA = true;
            } else if(node.equalsIgnoreCase("null") && url.contains("https://gui.staging.wiadvisor.dcctools.com")){
                env = "STAGE";
                FCA = true;
            } else if(node.equalsIgnoreCase("null") && url.contains("https://wiadvisor.com")){
                env = "PROD 1";
                FCA = true;
            } else if(node.equalsIgnoreCase("null") && url.contains("https://www.wiadvisor.com")){
                env = "PROD 1(AWS)";
                FCA = true;
            } else if(node.equalsIgnoreCase("null") && url.contains("https://pilot.wiadvisor.com")){
                env = "PILOT 1";
                FCA = true;
            } else if(node.equalsIgnoreCase("null") && url.contains("corp.internal-projects.com")){
                env = "Iydro DEV";
                FCA = true;
            }
            else if(node.toLowerCase().contains("scheduler2")){		//OSS3 on Prod 2
                env = "PROD 2";
                FCA = false;
            }
            else if(node.toLowerCase().contains("scheduler")){			//OSS3 on Prod 1
                env = "PROD 1";
                FCA = true;
            }
            else if(node.toLowerCase().contains("pilot1scheduler")){	//OSS3 on Pilot 1
                env = "PILOT 1";
                FCA = true;
            }
            else {
                env = "Unknown";
            }


		/*
		if(url.contains("https://test.wiadvisor.dcctools.com")){															// wiAdvisor on TEST
			env = "TEST";
		} else if(url.contains("https://gui.staging.wiadvisor.dcctools.com")){												// wiAdvisor on STAGE
			env = "STAGE";
		} else if(url.contains("https://wiadvisor.com")){																	// wiAdvisor on PROD
			env = "PROD";
		} else if(url.contains("//testservice.dealer-fx.com")){																// Advisor Dashboard on TEST
			env = "TEST";
		} else if(url.contains("//stageservice.dealer-fx.com")){															// Advisor Dashboard on STAGE
			env = "STAGE";
		} else if(url.contains("//service.dealer-fx.com")){																	// Advisor Dashboard on PROD
			env = "PROD";
		} else if(url.contains("//audi.dealer-fx.com")){																	// Audi on PROD
			env = "Audi PROD";
		} else if(url.contains("//stageaudi.dealer-fx.com")){																// Audi on STAGE
			env = "Audi STAGE";
		} else if(url.contains("//testaudi.dealer-fx.com")){																// Audi on TEST
			env = "Audi TEST";
		} else if(url.contains("//mitsubishicanada.dealer-fx.com/")){														// Mitsubishi on PROD
			env = "Mit PROD";
		} else if(url.contains("//stagemitsubishicanada.dealer-fx.com/")){													// Mitsubishi on STAGE
			env = "Mit STAGE";
		} else if(url.contains("//testmitsubishicanada.dealer-fx.com/")){													// Mitsubishi on TEST
			env = "Mit TEST";
		}
		else {
			env = "Unknown";
		}
		*/
        }

        public static boolean isFCA(){																							// is FCA Environment
            if(FCA){
                return true;
            } else {
                return false;
            }
        }



        public static void initTestResult(){																					// Init test result tracking
            numberOfTests = 0;
            passedDMS = new ArrayList<String>();
            failedDMS = new ArrayList<String>();
            failScrPath = new ArrayList<File>();
            failCID = new ArrayList<String>();
        }


        public static void testCount(){																							// Count total of tests run
            numberOfTests++;
        }


        public static void testRemove(){																						// Remove total of tests run
            numberOfTests--;

        }

        public static void getFailDMS(Set<ITestResult> failedTests){															// Count Failed tests
            for(ITestResult fail : failedTests){
                failedDMS.add(fail.getAttribute("DMS").toString());
            }
        }

        public static void setTask(String task){																				// Set the Task from xml file
            LogTestStats.task = task;
        }

        public static String getTask(){																							// Retrieve Task
            return task;
        }

        public static boolean allTestsPassed(){																					// Check to see if tests are all passed
            if(numberOfTests==passedDMS.size()){
                return true;
            } else {
                return false;
            }
        }


        public static String getSubjectStatus(){																				// Get Status for Subject Line
            StringBuilder status = new StringBuilder();
            if(allTestsPassed()){
                status.append(" - All Passed");
            } else {
                status.append(": ");
                for(int i = 0; i < failedDMS.size(); i++){
                    if(i>=1){
                        status.append(", ");
                    }
                    status.append(failedDMS.get(i));
                }
                status.append(" - Failure");
            }
            return status.toString();
        }



        public static String getBodyStatus(){																					// Get Status for Email Body
            StringBuilder status = new StringBuilder();
            if(allTestsPassed()){
                status.append("All Passed");
            } else {
                for(int i = 0; i < failedDMS.size(); i++){
                    if(i >= 1){
                        status.append(", ");
                    }
                    status.append(failedDMS.get(i));
                }
                status.append(" - Failure");
            }
            return status.toString();
        }

        public static String getDate(){																							// Get the current date
            Calendar date = Calendar.getInstance();
            return (new SimpleDateFormat("EEEE, MMMM d").format(date.getTime()) + "  ");
        }

    }
