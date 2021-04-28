package com.autotest.teststeps.tech_inspection;

import com.automation.pages.tech_inspection.FrenchTIPage;
import com.automation.pages.tech_inspection.ServiceTab.RecommendationTab;
import com.automation.pages.tech_inspection.dashboard.CreateMPIPage;
import com.automation.pages.tech_inspection.dashboard.TechInspectSearchPage;
import com.autotest.teststeps.BaseTestSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TI_FrenchSteps extends BaseTestSteps {

    FrenchTIPage fPage = new FrenchTIPage();
    RecommendationTab rTab = new RecommendationTab();
    CreateMPIPage cPage = new CreateMPIPage();
    TechInspectSearchPage tPage = new TechInspectSearchPage();

    @And("^I click \"([^\"]*)\" dropdown in \"([^\"]*)\" Dashboard$")
    public void iClickDropDown(String language, String dashboardName){
        fPage.clickOnLanguageOnHeader(language);
    }

    @And("^I select \"([^\"]*)\" in \"([^\"]*)\" dropdown in \"([^\"]*)\" Dashboard$")
    public void iSelectDropDown(String language, String dropDownName, String dashboardName){
        fPage.selectLanguage(language);
    }

    @When("^I click \"([^\"]*)\" button on search popup on \"([^\"]*)\" Dashboard$")
    public void iClickButtonOnDashboard(String buttonName,String pageName){
        switch (pageName){
            case "Technician Inspection":
                switch (buttonName){
                    case "Créer inspection":
                        fPage.clickOnFrenchButton(buttonName);
                        break;
                    case "Créer":
                        fPage.clickOnFrenchButton(buttonName);
                        break;
                }
                break;
        }
    }

    @When("^I see following French verbiage shown on 'Technician Inspection' Dashboard$")
    public void iCheckFrenchVerbiage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("English");
            String expectedResult = map.get("French");
            String actualResult = "";
            if (StringUtils.isNotBlank(expectedResult)) {
                switch(fieldName){
                    case "By VIN (last 5 to 8)":
                    case "Tag / Hat #":
                    case "Last Name":
                    case "RO #":
                    case "Status":
                    case "Created By":
                    case "Assigned To":
                    case "From":
                    case "Date Created":
                    case "To":
                    case "Auto-Refresh Search Results?":
                    case "Create MPI":
                    case "TAG#":
                    case "CUSTOMER":
                    case "RO#":
                    case "ARRIVAL TIME":
                    case "PROMISE TIME":
                    case "TECH / ADVISOR":
                    case "INSPECTION STATUS":
                        actualResult = fPage.verifyFrenchText(expectedResult);
                        break;
                    case "Clear Selections":
                    case "Search":
                        actualResult = fPage.verifyFrenchTextInput(expectedResult);
                        break;
                    case "TECHNICIAN INSPECTION":
                        fPage.switchContentToDefault();
                        actualResult = fPage.verifyFrenchText(expectedResult);
                        break;
                }
            }
            assertEquals(expectedResult, actualResult);
        }
    }

    @When("^I see following French verbiage shown on 'MPI' popup")
    public void iCheckFrenchVerbiageOnMPIPopUp(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("English");
            String expectedResult = map.get("French");
            String actualResult = "";
            if (StringUtils.isNotBlank(expectedResult)) {
                switch(fieldName){
                    case "Decode VIN":
                    case "Search":
                        //fPage.switchToFrenchMPICreateIframe();
                        actualResult = fPage.verifyFrenchTextInput(expectedResult);
                        break;
                    case "Customer Information / Service History":
                    case "Is this a Business?":
                        actualResult = fPage.verifyFrenchText(expectedResult);
                        break;
                }
            }
            assertEquals(expectedResult, actualResult);
        }
    }

    @And("^I see Vehicle with \"([^\"]*)\" with \"([^\"]*)\" status in French on 'TECHNICIAN INSPECTION Dashboard'$")
    public void iVerifyCarStatusInFrench(String VIN, String status){
//        long startTime = System.currentTimeMillis();
        String expectedStatus =status;
        String actualStatus = fPage.getInspectionStatusByVinFrench(VIN);
//        while (!actualStatus.equals(status)){
//            actualStatus = fPage.getInspectionStatusByVinFrench(VIN);
//            if(actualStatus.equals(status)){
//                break;
//            }else if((System.currentTimeMillis()-startTime)> 10000){
//                break;
//            }
//        }
//        if((actualStatus.equals("pièces demandées"))&& (expectedStatus.equals("terminé"))){
//            System.out.println("<====== "+VIN+" status incorrect, will check with the MPI page ======>");
//            tPage.clickOnStatusWithVin(VIN);
//            tPage.sleep(6000); //to check the incorrect status
//        }else if((actualStatus.equals("terminé"))&& (expectedStatus.equals("révision"))){
//            System.out.println("<====== "+VIN+" status incorrect, will check with the MPI page ======>");
//            tPage.clickOnStatusWithVin(VIN);
//            tPage.sleep(6000);
//        }else if((actualStatus.equals("révision"))&& (expectedStatus.equals("confirmé"))){
//            System.out.println("<====== "+VIN+" status incorrect, will check with the MPI page ======>");
//            tPage.clickOnStatusWithVin(VIN);
//            tPage.sleep(6000);
//        }
        assertEquals(expectedStatus, actualStatus);
    }

    @Then("^I should see \"([^\"]*)\" status on 'MPI Details' page in French$")
    public void iVerifyStatusOnMPIInFrench(String status){
        String actualStatus = fPage.getInspectStatusOnMPIPageFr();
        String expectedStatus = status;
        assertEquals(expectedStatus,actualStatus);
    }

    @When("^I enter data set in Create \"([^\"]*)\" popup in French$")
    public void iEnterDatasetInCreateMPIpopupWindowFrench(String popUpName,DataTable dataTable) throws Throwable {
        CreateMPIPage page = new CreateMPIPage();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                switch (fieldName){
                    case "NIV":
                        page.setVIN(value);
                        break;
                    case "Nom du client":
                        page.setCustomerName(value);
                        break;
                    case "Téléphone":
                        page.setCustomerPhone(processText(value));
                        break;
                    case "Marque":
                        page.selectMake(processText(value));
                        break;
                    case "Année":
                        page.selectYear(value);
                        break;
                    case "Modèle":
                        page.selectModel(value);
                        break;
                    case "Boîte de vitesse":
                        page.selectTransmission(processText(value));
                        break;
                    case "Taille du moteur":
                        page.selectEngineSize(processText(value));
                        break;
                    case "Groupe motopropulseur":
                        page.selectDriveTrain(value);
                        break;
                    case "Kilométrage":
                        page.checkMileages();
                        break;
                    case "Kilométrage Value":
                        page.setMileages(processText(value));
                        break;
                    case "Mois":
                        page.checkMonths();
                        break;
                    case "Mois Value":
                        page.setMonths(processText(value));
                        break;
                    case "Prénom":
                        page.setFirstName(processText(value));
                        break;
                    case "Nom de famille":
                        page.setLastName(value);
                        break;
                    case "Est-ce une entreprise?":
                        page.checkBusiness(processText(value));
                        break;
                    case "Adresse":
                        page.setAddress(value);
                        break;
                    case "Ville":
                        page.setCity(processText(value));
                        break;
                    case "Pays":
                        page.selectCountry(processText(value));
                        break;
                    case "Province/État":
                        page.selectState(processText(value));
                        break;
                    case "Code postal":
                        page.setPostalCode(processText(value));
                        break;
                    case "Comté":
                        page.selectCounty(processText(value));
                        break;
                    case "Numéro de téléphone par défaut":
                        page.setDefaultPhone(value);
                        break;
                    case "Téléphone cellulaire":
                        page.setCellPhone(processText(value));
                        break;
                    case "Courriel":
                        page.setEmail(processText(value));
                        break;
                    case "MOYEN DE COMMUNICATION PRÉFÉRÉ":
                        page.selectContactMethod(processText(value));
                        break;
                    case "Langue préférée":
                        page.selectLanguage(processText(value));
                        break;
                    case "Conseiller au service":
                        page.selectAdvisor(processText(value));
                        break;
                    case "Technicien":
                        page.selectTechnician(processText(value));
                        break;
                    case "Repère":
                        page.setTag(processText(value));
                        break;
                    case "N⁰ de BR":
                        page.setRO(processText(value));
                        break;
                    case "Numéro de lot":
                        page.setLotNumber(processText(value));
                        break;
                    case "Plaque d’immatriculation":
                        page.setLicensePlate(processText(value));
                        break;
                    case "HEURE PROMISE":
                        page.selectPromiseDate(processText(value));
                        break;
                    case "Promise Time":
                        page.selectPromiseTime(processText(value));
                        break;
                    case "Quart de temps":
                        page.selectQuarterDate(processText(value));
                        break;
                    case "Quarter Time":
                        page.selectQuarterTime(processText(value));
                        break;
                    case "Type d’inspection":
                        page.selectInspectionType(processText(value));
                        break;
                    case "Transport":
                        page.selectTransportation(processText(value));
                        break;
                    case "Garder les pièces":
                        page.checkSaveParts(processText(value));
                        break;
                    case "Lave-auto":
                        page.checkCarWash(processText(value));
                        break;
                    case "Le client a refusé de donner accès à la boîte à gants":
                        page.checkGlovebox(processText(value));
                        break;
                    case "Le client a refusé de donner accès à la roue de secours":
                        page.checkSparetire(processText(value));
                        break;
                    case "Commentaires":
                        page.setComments(processText(value));
                        break;
                }
            }
        }
    }

    @When("^I should see the following French verbiage shows on \"([^\"]*)\" page")
    public void iCheckFrenchVerbiageOnMPIPage(String pageName,DataTable dataTable) throws Throwable {
        CreateMPIPage page = new CreateMPIPage();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("English");
            String value = map.get("French");
            String expectedResult = value;
            String actualResult = "";
            if (StringUtils.isNotBlank(value)) {
                switch(pageName){
                    case "MPI":
                        switch(fieldName){
                            case "INSPECTION":
                                //fPage.waitForElementONMPIPage();
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "RECOMMENDATION":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "PARTS LIST":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "APPROVED SERVICES":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "WALK AROUND":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                        }
                        break;
                    case "Inspection Detail":
                        switch(fieldName){
                            case "High/low beam lights":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "VEHICLE ROAD TEST":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "Checked and OK at this time":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "May require future attention":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "Requires immediate attention":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "Internal Comments":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "Customer Comments":
                                actualResult=fPage.verifyFrenchText(value);
                                break;
                            case "Select services from the list...":
                                actualResult=fPage.verifyFrenchTextInput(value);
                                break;
                        }
                        break;
                    case "Car info":
                        actualResult=fPage.verifyFrenchText(value);
                        break;
                }

            }
            assertEquals(expectedResult,actualResult);
        }
    }

    @When("^I click \"([^\"]*)\" button on \"([^\"]*)\" section on \"([^\"]*)\" tab$")
    public void iClickSomethingOnSectionOnTab(String buttonName, String sectionName, String tabName){
        switch(sectionName){
            case "INSPECTION DES PNEUS":
                fPage.clickONMarkSectionAllOkayBasedOnSectionName(sectionName);
                break;
            case "PLAQUETTES DE FREIN (USURE)":
                fPage.clickONMarkSectionAllOkayBasedOnSectionName(sectionName);
                break;
        }
    }

    @And("I \"([^\"]*)\" on \"([^\"]*)\" page")
    public void iMarkSectionAllOKFr(String section, String pageName){
        switch (pageName){
            case "MPI Details":
                switch (section){
                    case "Marquer la section tout OK":
                        fPage.markSectionAllOKFr();
                        break;
                }
                break;
        }
    }

    @And("^I click \"([^\"]*)\" icon on \"([^\"]*)\" section on \"([^\"]*)\" tab$")
    public void iClickIconOnSectionOnTab(String iconName, String sectionName, String tabName){
        switch (sectionName){
            case "TEST ROUTIER DU VÉHICULE":
                fPage.clickONRedIconBasedOnSectionName(sectionName);
                break;
        }
    }

    @And("^I enter data set in addRecommendationField in French$")
    public void iEnterDatasetInCreateMPIpopupWindowFr(DataTable dataTable) throws Throwable {
        CreateMPIPage page = new CreateMPIPage();
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            String fieldName = map.get("FieldName");
            String value = map.get("Value");
            if (StringUtils.isNotBlank(value)) {
                if (fieldName.equals("Recommandation")) {
                    rTab.setRecommendationName(value);
                } else if (fieldName.equals("OPcode")) {
                    rTab.setOPcode(value);
                } else if (fieldName.equals("Heures")) {
                    rTab.setHours(processText(value));
                } else if (fieldName.equals("Vente de main-d’œuvre")) {
                    rTab.setLaborSale(processText(value));
                } else if (fieldName.equals("Vente de pièce")) {
                    rTab.setPartSale(value);
                } else if (fieldName.equals("Prix")) {
                    rTab.setPrice(value);
                }
            }
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" page in French$")
    public void iClickSomeButtonOnSomePageFr(String buttonName, String pageName){
        switch(buttonName){
            case "pièces requises":
                fPage.clickOnPartsRequestFr();
                break;
            case "Sauvegarder":
                switch (pageName){
                    case "Inspection Detail":
                        fPage.clickOnSaveOnInpectionDetaiFr();
                        break;
                    case "MPI":
                        fPage.clickOnSaveOnMPIFr();
                        break;
                }
                break;
            case "INSPECTION":
                fPage.clickInspectionOnMPIFr();
                break;
            case "RECOMMENDATION":
                fPage.clickONRecommendationOnMPIFr();
                break;
            case "Complété":
                fPage.clickONMarkCompletedOnMPIFr();
                break;
            case "Afficher":
                fPage.clickONShowMPIFr();
                break;
            case "Révisé":
                fPage.clickOnMarkReviewFr();
                break;
            case "Confirmer":
                fPage.clickOnConfirmerFr();
                break;
            case "Afficher l'historique":
                fPage.clickOnViewHistoryFr();
                break;
            case "Journal d'événements":
                fPage.clickOnStatusLogFr();
                break;
            case "Afficher le menu":
                fPage.clickOnViewMenuFr();
                break;
            case "Prêt à traiter":
                fPage.clickOnReadyForProcessingFr();
                break;
        }
    }

    @Then("^I should see recommendation with \"([^\"]*)\" Description with \"([^\"]*)\" Heures with \"([^\"]*)\" Main-d’œuvre with \"([^\"]*)\" Pièces with \"([^\"]*)\" Prix on 'RECOMMENDATION' tab in French$")
    public void iVerifyRecommendationOnRecommendationTabFr(String description, String hours, String labor, String parts, String price){
        String expectedDescription = description;
        String expectedHours = hours;
        String expectedLabor = labor;
        String expectedParts = parts;
        String expectedPrice = price;
        String actualDescription = fPage.getDescriptionValueOnRecommendationTabFr();
        String actualHours = fPage.getHoursValueOnRecommendationTabFr();
        String actualLabor = fPage.getLaborValueOnRecommendationTabFr();
        String actualParts = fPage.getPartsValueOnRecommendationTabFr();
        String actualPrice = fPage.getPriceValueOnRecommendationTabFr();
        assertEquals(expectedDescription,actualDescription);
        assertEquals(expectedHours,actualHours);
        assertEquals(expectedLabor,actualLabor);
        assertEquals(expectedParts,actualParts);
        assertEquals(expectedPrice,actualPrice);
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" tab in French$")
    public void iClickSomeButtonOnSomeTabInFrench(String buttonName, String tabName){
        switch(tabName){
            case "RECOMMENDATION":
                switch (buttonName){
                    case "Requête de pièces complétée":
                        fPage.clickOnPartsRequestCompletedFr();
                        break;
                }
                break;
        }
    }

    @When("^I click \"([^\"]*)\" button with \"([^\"]*)\" Description on 'RECOMMENDATION' page in French$")
    public void iClickOnButtonWithDescriptionOnRecommendationFr(String buttonName, String description){
        switch (buttonName){
            case "approuvée":
                rTab.clickOnApprovedAssociatedWithDescription(description);
                break;
            case "En stock":
                try{
                    fPage.clickOnInStockAssociatedWithDescriptionFr(description);
                }catch(org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException ex){
                    Assert.fail("<====== Hi, I caught the bug that seeing English 'In stock' instead French 'En stock' ======>");
                }
                break;
        }
    }

    @And("^I click \"([^\"]*)\" on 'footbar' tab on 'MPI Details' page in French$")
    public void iClickSomethingOnFooterFr(String buttonName){
        rTab.clickOnShowTotals();
    }

    @Then("^I should see \"([^\"]*)\" with \"([^\"]*)\" on 'footbar' tab in French$")
    public void iSeePriceOnFooter(String columnName, String price) {
        String expectedPrice = price;
        String actualPrice = "";
        switch (columnName){
            case "RECOMMANDÉS":
                actualPrice=rTab.getRecommendedPriceOnFooter();
                break;
            case "APPROUVÉS":
                actualPrice=rTab.getApprovedPriceOnFooter();
                break;
        }
        assertEquals(expectedPrice,actualPrice);
    }

    @Then("^I should see \"([^\"]*)\" title on popup in French$")
    public void iVerifyTitleOnPopUpFr(String titleName){
        String expectedTitle = titleName;
        String actualTitle = "";
        switch(titleName){
            case "rapport(s) d'inspection multipoint":
                actualTitle = rTab.getViewHistoryPopUpTitle();
                break;
            case "Journal d'événements":
                actualTitle = rTab.getStatusLogPopUpTitle();
                break;
        }
        assertThat(actualTitle,containsString(expectedTitle));
    }

    @When("^I click \"([^\"]*)\" button on \"([^\"]*)\" popup in French$")
    public void i_click_something_button_on_popupFr(String buttonName,String popUpName){
        switch (popUpName){
            case "Afficher l'historique":
                switch(buttonName){
                    case "close":
                        rTab.closeViewHistoryPopUpFr();
                        break;
                }
                break;
            case "Journal d'événements":
                switch (buttonName){
                    case "close":
                        rTab.closeStatusLogPopUp();
                        break;
                }
                break;
        }
    }

    @Then("^I should see \"([^\"]*)\" on link in French$")
    public void iVerifyExternalLink(String text){
        String expectedText = "";
        String actualText = "";
        switch(text){
            case "Audi of Anytown Canada requis par le fabricant Scheduled Maintenance Guide":
                expectedText = "PDF";
                actualText = rTab.getViewMenuPopupURL();
                break;
        }
        assertThat(actualText,containsString(expectedText));
    }

    @And("^I should see \"([^\"]*)\" Description with \"([^\"]*)\" labor with \"([^\"]*)\" price in 'recommendations' section on 'SERVICES APPROUVÉS' tab in French$")
    public void iVerifyPriceOnReApprovedServiceTab(String description, String labor, String price){
        String expectedDescription = description;
        String expectedLabor = labor;
        String expectedPrice = price;
        String actualDescription = rTab.getDescriptionValueOnArrovedServiceTab(description);
        String actualLabor = rTab.getLaborValueOnArrovedServiceTab(description);
        String actualPrice = rTab.getPriceValueOnArrovedServiceTab(description);
        assertEquals(expectedDescription,actualDescription);
        assertEquals(expectedLabor,actualLabor);
        assertEquals(expectedPrice,actualPrice);
    }

    @When("^I click \"([^\"]*)\" toggle with \"([^\"]*)\" Description on 'SERVICES APPROUVÉS' tab in French$")
    public void i_click_something_toggle_with_something_description_on_approved_services_tab(String iconColor, String desciption){
        switch (iconColor){
            case "green":
                rTab.clickOnIconBasedONDescritionOnApprovedService(desciption);
                break;
        }
    }
}
