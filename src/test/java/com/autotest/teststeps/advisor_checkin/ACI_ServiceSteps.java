package com.autotest.teststeps.advisor_checkin;


import com.automation.utils.otherUtils.CommonMethods;
import com.autotest.teststeps.BaseTestSteps;
import com.automation.pages.advisor_checkin.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ACI_ServiceSteps extends BaseTestSteps {

    private static StringBuffer expectSearch = new StringBuffer(" ***");
    private static StringBuffer actulSearch = new StringBuffer(" ***");

    ACI_ServicesPage asPage = new ACI_ServicesPage();
    ACI_PageHelpPDF asPDF = new ACI_PageHelpPDF();

    private static ArrayList<String> preselectedServiceRequiredItems;
    private static ArrayList<String> selectedFactoryRequiredServiceTextList;
    private static ArrayList<String> selectedTriageItemList = new ArrayList<String>();
    private static String[] beforeRemovingItems;
    private static String[] afterRemovingItems;

    private static Logger log = Logger.getLogger(ACI_ServiceSteps.class);


    @And("^I keep Pre-Selected or \"([^\"]*)\" \"([^\"]*)\" in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected$")
    public void ikeepPreSelectedOrCheckOnServicePage(String checks, String checkItem) {
        if (asPage.isScheduledTabPresent()) {
            preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
            if (preselectedServiceRequiredItems.size() > 0) {
                System.out.println(" Pre-Selected Factory Item exist  ");
            } else {
                asPage.chooseSingleItemInScheduleService(checks, checkItem);
            }
        } else {
            selectedFactoryRequiredServiceTextList = asPage.getSelectedFactoryRequiredServiceItems();
            if (selectedFactoryRequiredServiceTextList.size() > 0) {
                System.out.println(" Factory Required Items  exist  ");
            } else {
                asPage.chooseSingleItemInScheduleService(checks, checkItem);
            }
        }
    }

    @And("^I keep Pre-Selected or \"([^\"]*)\" \"([^\"]*)\" or first in 'FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected$")
    public void ikeepPreSelectedOrCheckFirstOnServicePage(String checks, String checkItem) {
        if (asPage.isScheduledTabPresent()) {
            preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
            if (preselectedServiceRequiredItems.size() > 0) {
                System.out.println(" Pre-Selected Factory Item exist  ");
            } else {
            	try {
            		asPage.chooseSingleItemInScheduleService(checks, checkItem);					
				} catch (org.openqa.selenium.ElementNotInteractableException e) {
					System.out.println("Exception found");
					asPage.clickScheduledTab();
		            selectedFactoryRequiredServiceTextList = asPage.getSelectedFactoryRequiredServiceItems();
		            if (selectedFactoryRequiredServiceTextList.size() > 0) {
		                System.out.println(" Factory Required Items  exist  ");
		            } else {
		                asPage.chooseSingleItemInScheduleService(checks, checkItem);
		            }					
				}
            }
        } else {
            selectedFactoryRequiredServiceTextList = asPage.getSelectedFactoryRequiredServiceItems();
            if (selectedFactoryRequiredServiceTextList.size() > 0) {
                System.out.println(" Factory Required Items  exist  ");
            } else {
                asPage.chooseSingleItemInScheduleService(checks, checkItem);
            }
        }
    }

    @And("^I verify selected \"([^\"]*)\" services matched selected servies items$")
    public void iVerifySelectedServicesMatchedPerformedSelectedItems(String service) {
        String name = service.toUpperCase();
        String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
        Boolean match = false;
        switch (name) {
            case "SCHEDULED":
//                System.out.println("performServiceItemTextList size   is : " + performServiceItemTextList.length);
//                for(int i = 0; i < performServiceItemTextList.length; i++){
//                    System.out.println("performServiceItemTextList text    is : " + performServiceItemTextList[i]);
//                }
                preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
                match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                break;
            case "FACTORY REQUIRED":
                preselectedServiceRequiredItems = asPage.getSelectedFactoryRequiredServiceItems();
                match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                break;
            case "UNSCHEDULED":
                preselectedServiceRequiredItems = asPage.getSelectedOtherUnscheduledServiceItems();
                match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                break;
            case "RECOMMENDED":
                preselectedServiceRequiredItems = asPage.getSelectedRecommendServiceItems();
                System.out.println("get preselectedServiceRequiredItems is . size ... " + preselectedServiceRequiredItems.size() );
                match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                break;
        }
        if (!match) {
            log.error(" The selected " + name + " services Items are not match the Items in perform Service Item ");
            System.err.println(" verify Failed ! " + " The selected " + name + " services Items are not match the Items in perform Service Item ");
        } else {
            System.out.println(" verify passed ! " + " The selected " + name + " services Items  match the Items in perform Service Item ");
        }
        Assert.assertTrue("verify Failed ! " + " The selected " + name + " services Items are not match the Items in perform Service Item !", match);
    }

    @And("^I verify selected 'scheduled services' matched selected servies items$")
    public void iVerifyScheduledServicesMatchedSelectedItems() {
        String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
        preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
        Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
//        Assert.assertTrue(match);
        if (!match) {
            log.error(" The selected scheduled services Items are not match the Items in perform Service Item ist");
            System.out.println(" verify Failed ! The scheduled services Items are not match the Items in perform Service Item ist");
        }
    }

    @And("^I verify selected 'Factory Required services' matched selected servies items$")
    public void iVerifyFactoryRequiredServicesMatchedSelectedItems() {
        String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
        preselectedServiceRequiredItems = asPage.getSelectedFactoryRequiredServiceItems();
        Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
//        Assert.assertTrue(match);
        if (!match) {
            log.error(" The selected Factory Required Items are not match the Items in perform Service Item ist");
            System.out.println(" verify Failed ! The selected FactoryRequired Items are not match the Items in perform Service Item ist");
        }
    }

    @And("^I keep Pre-Selected or choose random less \"(.*)\" Scheduled services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected$")
    public void ikeepPreSelectOrRandomScheduledOnServicePage(int num) {
        if (asPage.isScheduledTabPresent()) {
            preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
            if (preselectedServiceRequiredItems.size() > 0) {
                System.out.println(" Pre-Selected Factory Item exist  ");
            } else {
                asPage.chooseRandomMumItemInScheduleService(num);
            }
        }
    }


    @And("^I keep Pre-Selected or choose random less \"(.*)\" Factory Required services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected$")
    public void ikeepPreSelectOrRandomFactoryOnServicePage1(int num) {
        if (!asPage.isScheduledTabPresent()) {
            selectedFactoryRequiredServiceTextList = asPage.getSelectedFactoryRequiredServiceItems();
            if (selectedFactoryRequiredServiceTextList.size() > 0) {
                System.out.println(" Factory Required Items  exist  ");
                String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
                Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                if (!match) {
                    System.out.println(" Verify failed ! Selected Scheduled Service Items not Matched");
                }
                match = asPage.okRemoveAllFactoryRequiredItems();
                if (!match) {
                    System.out.println(" Verify failed ! Factory Required Items failed to be Removed ");
                }

            } else {
                System.out.println("There is No Pre-Selected Factory Required Service Items. Now Select random Servics.");
                asPage.chooseRandomMumItemFactoryRequiredService(num);
                String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
                preselectedServiceRequiredItems = asPage.getSelectedFactoryRequiredServiceItems();
                Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                if (!match) {
                    System.out.println(" Verify failed ! Selected Scheduled Service Items not Matched");
                }
                match = asPage.okRemoveAllFactoryRequiredItems();
                if (!match) {
                    System.out.println(" Verify failed ! Factory Required Items failed to be Removed ");
                }
            }
            asPage.chooseRandomMumItemFactoryRequiredService(num);
        }
    }

    @And("^I keep Pre-Selected or choose random less \"(.*)\" 'unScheduled' services in 'OTHER FACTORY MAINTENANCE' Tab$")
    public void ikeepPreSelectOrRandomUnScheduledOnServicePage(int num) {
        if (asPage.isScheduledTabPresent()) {
            preselectedServiceRequiredItems = asPage.getSelectedOtherUnscheduledServiceItems();
            if (preselectedServiceRequiredItems.size() > 0) {
                System.out.println(" Pre-Selected Unscheduled Item exist  ");
            } else {
                asPage.chooseRandomMumOtherUnScheduledService(num);
            }
        }
    }

    @And("^I keep Pre-Selected or choose random less \"(.*)\" 'recommend' services in 'RECOMMNED SERVICES' Tab$")
    public void ikeepPreSelectOrRandomRecommendOnServicePage(int num) {
        if (asPage.isScheduledTabPresent()) {
            preselectedServiceRequiredItems = asPage.getSelectedRecommendServiceItems();
            if (preselectedServiceRequiredItems.size() > 0) {
                System.out.println(" Pre-Selected Unscheduled Item exist  ");
            } else {
                asPage.chooseRandomMumRecommendService(num);
            }
        }
    }


    @And("^I choose random less \"(.*)\" 'unScheduled' services in 'OTHER FACTORY MAINTENANCE' Tab$")
    public void iChooseRandomOtherUnScheduledOnServicePage(int num) {
        asPage.chooseRandomMumOtherUnScheduledService(num);
    }

    @And("^I choose random less \"(.*)\" 'recommend' services in 'RECOMMNED SERVICES' Tab$")
    public void iChooseRandomRecommendOnServicePage(int num) {
        asPage.chooseRandomMumRecommendService(num);
    }

    @And("^I verify Remove All to UnScheduled service Items$")
    public void iVerifyRemoveAllUnScheduledServiceItems() {
        Boolean ok = asPage.okRemoveAllUnScheduledItems();
        if (!ok) {
            log.error(" The selected Factory Required Items are not match the Items in perform Service Item ist");
            System.out.println(" verify Failed ! The selected FactoryRequired Items are not match the Items in perform Service Item ist");
        }
    }

    @And("^I verify Remove All to \"([^\"]*)\" services items on services page$")
    public void iVerifyRemoveAllToServicesSelectedItems(String service) {
        String name = service.toUpperCase();
        Boolean ok = false;
        switch (name) {
            case "SCHEDULED":
                ok = asPage.okRemoveAllScheduledItems();
                break;
            case "FACTORY REQUIRED":
                if (!asPage.isScheduledTabPresent()) {
                    ok = asPage.okRemoveAllFactoryRequiredItems();
                } else {
                    return;
                }
                break;
            case "UNSCHEDULED":
                ok = asPage.okRemoveAllUnScheduledItems();
                break;
            case "RECOMMENDED":
                ok = asPage.okRemoveAllRecommendedItems();
                break;
        }

        if (!ok) {
            log.error(" verify Failed ! " + " The selected " + name + "  Items are Removed Failed ! ");
            System.err.println(" verify Failed ! " + " The selected " + name + " Items are Removed Failed ! ");
        }
        Assert.assertTrue("verify Failed ! " + " The selected " + name + "  Items are Removed Failed !", ok);
    }

    @And("^Iaaa keep Pre-Selected or choose random less \"(.*)\" services in 'SERVICE-FACTORY SCHEDULED MAINTENANCE' Tab if No Pre-selected$")
    public void ikeepPreSelectOrRandomOnServicePage1(int num) {
        if (asPage.isScheduledTabPresent()) {
            preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
            if (preselectedServiceRequiredItems.size() > 0) {
                System.out.println(" Pre-Selected Factory Item exist  ");
                String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
                Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                if (!match) {
                    System.out.println(" Verify failed ! Selected Scheduled Service Items not Matched");
                }

            } else {
                asPage.chooseRandomMumItemInScheduleService(num);
//                preselectedServiceRequiredItems =asPage.selectedItemList;
                String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
                preselectedServiceRequiredItems = asPage.getSelectedScheduledServiceItems();
                Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                if (!match) {
                    System.out.println(" Verify failed ! Selected Scheduled Service Items not Matched");
                }
            }
        } else {
            selectedFactoryRequiredServiceTextList = asPage.getSelectedFactoryRequiredServiceItems();
            if (selectedFactoryRequiredServiceTextList.size() > 0) {
                System.out.println(" Factory Required Items  exist  ");
                String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
                Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                if (!match) {
                    System.out.println(" Verify failed ! Selected Scheduled Service Items not Matched");
                }
                match = asPage.okRemoveAllFactoryRequiredItems();
                if (!match) {
                    System.out.println(" Verify failed ! Factory Required Items failed to be Removed ");
                }

            } else {
                System.out.println("There is No Pre-Selected Factory Required Service Items. Now Select random Servics.");
                asPage.chooseRandomMumItemFactoryRequiredService(num);
                String[] performServiceItemTextList = asPage.getPerformItemsBeforeRemoving();
                preselectedServiceRequiredItems = asPage.getSelectedFactoryRequiredServiceItems();
                Boolean match = CommonMethods.isServiceItemMatchSelectedItem(preselectedServiceRequiredItems, performServiceItemTextList);
                if (!match) {
                    System.out.println(" Verify failed ! Selected Scheduled Service Items not Matched");
                }
                match = asPage.okRemoveAllFactoryRequiredItems();
                if (!match) {
                    System.out.println(" Verify failed ! Factory Required Items failed to be Removed ");
                }
            }
            asPage.chooseRandomMumItemFactoryRequiredService(num);
        }
    }


    @And("^I click \"([^\"]*)\" tab on 'SERVICES' Page$")
    public void iClickTabonServicePage(String tab) {
        if (tab.equalsIgnoreCase("OTHER FACTORY MAINTENANCE")) {
            asPage.clkUnscheduledTab();
        } else if (tab.equalsIgnoreCase("RECOMMENDED SERVICES")) {
            asPage.clkRecommendedTab();
        }
          else if (tab.equalsIgnoreCase("FACTORY SCHEDULED MAINTENANCE")){
            asPage.clkFRSTab();
        }
    
    
    }


    @And("^I click \"([^\"]*)\" button on 'SERVICE-FACTORY SCHEDULED' Page$")
    public void iClickBtnServiceFactorySchedueledPage(String btn) {
        if (btn.equalsIgnoreCase("REMOVE ALL")) {
            asPage.removeScheduleItem();
        }
    }

    @And("^I click \"([^\"]*)\" button on 'SERVICE-OTHER FACTORY MAINTENANCE' Page$")
    public void iClickBtnServiceOFMPage(String btn) {
        if (btn.equalsIgnoreCase("REMOVE ALL")) {
            asPage.removeUnscheduleItem();
        }
    }

    @And("^I click \"([^\"]*)\" button on 'SERVICE-RECOMMENDED SERVICES' Page$")
    public void iClickBtnServiceRecommandPage(String btn) {
        if (btn.equalsIgnoreCase("REMOVE ALL")) {
            asPage.removeRecommendedItem();
        }
    }

    @And("^I click \"([^\"]*)\" button on 'SERVICES' page$")
    public void iClickButtonOnServicesPage(String buttonName) {
        asPage.clickButtonOnServicesPage(buttonName);
    }

    @And("^I click \"([^\"]*)\" button on 'POP UP' page$")
    public void iClickButtonOnPopPage(String buttonName) {
        if (buttonName.equalsIgnoreCase("edit")) {
            asPage.clickEditBtnOnPop();
        } else if (buttonName.equalsIgnoreCase("show")) {
            asPage.clickMenuShowBtnOnPop();
        }

    }

    @Then("^I verify \"([^\"]*)\" service PDF generated on 'SERVICES' page$")
    public void iamVerifyPDFGenerateQuote(String field) {    	
        field = field.toUpperCase();
        expectSearch.append(" " + field + ":" + "Y ");
        boolean result = false;
        if (field.equalsIgnoreCase("Maintenance Time")) {
            result = asPDF.okMaintMenuVWTime();
        } else if (field.equalsIgnoreCase("Maintenance Mileage")) {
            result = asPDF.okMaintMenuVWMileage();
        } else {
            return;
        }
        if (result) {
            actulSearch.append(" " + field + ":" + "Y ");
        } else {
            actulSearch.append(" " + field + ":" + "N ");
            System.out.println(" Verify Fail ! The " + field + " NOT CORRECT ! ");
            log.error(" Verify Fail ! The " + field + " NOT CORRECT ! ");
        }
        asPDF.cleanPDF();

    }

    @Then("^I verify 'MONTH' and 'MILES' service larger than below on 'SERVICES' page$")
    public void iVerifyMonthAndMilesScheduleLargerOnServicePage(DataTable dataTable) throws Throwable {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        String service = asPage.getFirstScheduledServiceText();
        int acMonth = Integer.valueOf(service.substring(0, service.indexOf("MONTHS")).trim());
        String mileage = service.substring(service.indexOf("/") + 1, service.indexOf("MILES")).trim();
        int acMile = Integer.valueOf(mileage.replaceAll(",", ""));
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName");
            int value = Integer.valueOf(map.get("Value"));
            if (field.equalsIgnoreCase("Month")) {
                if (acMonth < value) {
                    System.out.println("<==Verify failed ! " + field + " : " + value + " VS " + acMonth + "  test failed =>");
                    log.error("<==Verify failed ! " + field + " : " + value + " VS " + acMonth + "  test failed =>");
                }
            } else if (field.equalsIgnoreCase("Mileage")) {
                if (acMile < value) {
                    System.out.println("<==Verify failed ! " + field + " : " + value + " VS " + acMile + "  test failed =>");
                    log.error("<==Verify failed ! " + field + " : " + value + " VS " + acMile + "  test failed =>");
                }
            }
        }

    }

    @And("^I assert all above PDF verify result on 'SERVICES' page$")
    public void iAssertAllPDFVerifyOnServicePage() {
        String ex = expectSearch.toString();
        String ac = actulSearch.toString();
        Assert.assertEquals(" customer search not match ! ", ex, ac);
    }

    @Then("^I see \"([^\"]*)\" tab is activated on 'SERVICES' page$")
    public void iSeeSomeTabActivated(String tabName) {
        Assert.assertTrue("Verify VEHICLE HEALTH tab is activated failed!", asPage.verifyTabActivated(tabName));
    }

    @And("^I see Following status on 'SERVICES' page$")
    public void iVerifyStatusOnServicesPage(DataTable dataTable) {
        StringBuffer expect = new StringBuffer("-->");
        StringBuffer actual = new StringBuffer("-->");
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String field = map.get("FieldName");
            String value = map.get("Value");
            switch (field) {
                case "RECALLS":
                    expect.append(" " + field + ":" + (processEmpty(value)).toUpperCase());
                    if (asPage.recallFound()) {
                        actual.append(" " + field + ":Y");
                    } else {
                        actual.append(" " + field + ":N");
                    }
                    break;
            }
            String ex = expect.toString();
            String ac = actual.toString();
            if (!ex.equalsIgnoreCase(ac)) {
                System.out.println("Recall s Test failed!");
                System.out.println("Expect result: " + ex);
                System.out.println("Actual  result: " + ac);
            } else {
                System.out.println(" Recall s Test passed!");
            }
        }
    }

    @And("^I see number of (.+) and recalls descriptions and status of recalls$")
    public void iVerifyNumberOfRecallsAndDescriptions(String recalls) {
       if(recalls.equalsIgnoreCase("N")){
           Assert.assertTrue("No recalls with this vehicle and no recalls found in Vehicle Health, verification passed!", !asPage.recallFound());
       }else{
           asPage.verifyNumOfRecallsAndDescriptions();
       }
    }

    @And("^I select \"([^\"]*)\" service on 'SERVICES' page$")
    public void iSelectServiceOnServicesPage(String serviceName) {
        switch(serviceName){
            case "MULTI-POINT INSPECTION":
                asPage.selectMultipointInspection();
                break;
        }
    }
    
    @And("^I click 'FACTORY SCHEDULED MAINTENANCE' on 'SERVICES' page$")
    public void iSelectUnScheduledTabOnServicesPage() {
    	asPage.clickScheduledTab();
    } 
    
    @And("^I validate selected services on 'SERVICES' page$")
    public void iValidateSelectedServices() {
    	asPage.validateServices();
    }       

}