@TI10 @TI
Feature: TI10_TechnicianInspection_French, French (audi 29 point Canada template)

  @tifrench_dashboard
  Scenario Outline: [@tifrench_dashboard] check dashboard in french version
    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "auditechca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Change to French version
    And I click "English (United States)" dropdown in "Technician Inspection" Dashboard
    And I select "Français (Canada)" in "language" dropdown in "Technician Inspection" Dashboard
    #Create MPI
    And I launch 'Search popup panel' in Inspection dashboard
    #Check Search popup panel in french Version, mini copydeck
    Then I see following French verbiage shown on 'Technician Inspection' Dashboard
      | English                      | French                                                |
      | By VIN (last 5 to 8)         | Par NIV (5 à 8 derniers)                              |
      | Tag / Hat #                  | N⁰ de repère                                          |
      | Last Name                    | Nom de famille                                        |
      | RO #                         | N⁰ de BR                                              |
      | Status                       | Statut                                                |
      | Created By                   | Créé par                                              |
      | Assigned To                  | Assignés à                                            |
      | From                         | De                                                    |
      | Date Created                 | Date de création                                      |
      | To                           | à                                                     |
      | Auto-Refresh Search Results? | Actualisation automatique des résultats de recherche? |
      | Clear Selections             | Effacer les choix                                     |
      | Search                       | Recherche                                             |
      | Create MPI                   | Créer inspection                                      |
    When I click "Créer inspection" button on search popup on "Technician Inspection" Dashboard
    #Check create MPI popup window in french version
    Then I see following French verbiage shown on 'MPI' popup
      |English   |French        |
      |Decode VIN|Décoder le NIV|
      |Search    |Recherche     |
    When I enter data set in Create "MPI" popup in French
    #  for 'Customer/VIN Search' section
      | FieldName        |Value                   |
      | NIV              | WAUAUGFF2H1040192      |
      | Nom du client    |                        |
      | Téléphone        |                        |
    And I decode <VIN> in MPI popup windows
    And I enter data set in Create "MPI" popup in French
    # for the 'Vehicle' Section
      | FieldName             |Value                   |
      | Marque                |                        |
      | Année                 |                        |
      | Modèle                |                        |
      | Boîte de vitesse	  |                        |
      | Taille du moteur	  |                        |
      | Groupe motopropulseur |                        |
      | Kilométrage           |                        |
      | Kilométrage Value     | 65400                  |
      | Mois                  |                        |
      | Mois Value            |                        |
    And I enter data set in Create "MPI" popup in French
    # for the 'Customer Service / History Information' Section
      | FieldName                      |              |
      | Prénom                         |              |
      | Nom de famille                 |              |
      | Est-ce une entreprise?         |              |
      | Adresse                        |              |
      | Ville                          |              |
      | Pays                           |              |
      | Province/État                  |              |
      | Code postal                    |              |
      | Comté                          |              |
      | Numéro de téléphone par défaut |              |
      | Téléphone cellulaire           |              |
      | Courriel                       |              |
      | MOYEN DE COMMUNICATION PRÉFÉRÉ |              |
      | Langue préférée                |              |
    Then I see following French verbiage shown on 'MPI' popup
      |English                               |French                                                     |
      |Customer Information / Service History|Coordonnées du client / Historique de service              |
      |Is this a Business?                   |Est-ce une entreprise?                                     |
    When I enter data set in Create "MPI" popup in French
    # for the 'Inspection Information' Section
      | FieldName                                               |Value                                     |
      | Conseiller au service                                   |                                          |
      | Technicien                                              |                                          |
      | Repère                                                  | fr01                                     |
      | N⁰ de BR                                                | French01                                 |
      | Numéro de lot                                           |                                          |
      | Plaque d’immatriculation                                |                                          |
      | HEURE PROMISE                                           | +3                                       |
      | Promise Time                                            |                                          |
      | Quart de temps                                          |                                          |
      | Quarter Time                                            |                                          |
      | Type d’inspection                                       | Audi Canada 29 Point Inspection          |
      | Transport                                               |                                          |
      | Garder les pièces                                       |                                          |
      | Lave-auto                                               |                                          |
      | Le client a refusé de donner accès à la boîte à gants   |                                          |
      | Le client a refusé de donner accès à la roue de secours |                                          |
      | Commentaires                                            |                                          |
    When I click "Créer" button on search popup on "Technician Inspection" Dashboard
    # check dashboard in french version
    Then I see following French verbiage shown on 'Technician Inspection' Dashboard
      | English                 | French                   |
      | TECHNICIAN INSPECTION   | Inspection du technicien |
      | TAG#                    | N⁰ DE REPÈRE             |
      | CUSTOMER                | CLIENT                   |
      | RO#                     | N⁰ DE BR                 |
      | ARRIVAL TIME            | HEURE D'ARRIVÉE          |
      | PROMISE TIME            | HEURE PROMISE            |
      | TECH / ADVISOR          | TECH / CONSEILLER        |
      | INSPECTION STATUS       | ÉTAT D'INSPECTION        |
    And I see Vehicle with "<VIN>" with "créé" status in French on 'TECHNICIAN INSPECTION Dashboard'
    # Open MPI
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    # Check tabs name in french
    Then I should see the following French verbiage shows on "MPI" page
      | English                 | French              |
      | INSPECTION              | INSPECTION          |
      | RECOMMENDATION          | RECOMMANDATION      |
      | PARTS LIST              | LISTE DES PIÈCES    |
      | APPROVED SERVICES       | SERVICES APPROUVÉS  |
      | WALK AROUND             | INSPECTION VISUELLE |
    When I click "Marquer la section tout OK" button on "INSPECTION DES PNEUS" section on "INSPECTION" tab
    And I click "Marquer la section tout OK" button on "PLAQUETTES DE FREIN (USURE)" section on "INSPECTION" tab
    And I click "Red" icon on "TEST ROUTIER DU VÉHICULE" section on "INSPECTION" tab
    # Check inspection Detail in french
    Then I should see the following French verbiage shows on "Inspection Detail" page
      | English                         | French                            |
      #| BATTERY CONDITION               | État du pare-brise               |
      | VEHICLE ROAD TEST               | Pilotage / Freinage / Performance |
      | Checked and OK at this time     | Vérifié et en bon état            |
      | May require future attention    | Devra bientôt être réparé         |
      | Requires immediate attention    | Doit être réparé immédiatement    |
      | Internal Comments               | Commentaires internes             |
      | Customer Comments               | Commentaires du client            |
      #|                                 | Ajouter les commentaires internes |
      #|                                 | Ajouter les commentaires du client|
      | Select services from the list...| Sélectionner les services requis  |
    When I enter data set in addRecommendationField in French
      | FieldName             |Value         |
      | Recommandation        | TestFrench   |
      | OPcode                |              |
      | Heures                | 2,5          |
      | Vente de main-d’œuvre | 25,5         |
      | Vente de pièce        | 100          |
      | Prix                  |              |
    And I click "plus" icon on 'Inspection Detail' page
    And I click "pièces requises" button on "Inspection Detail" page in French
    And I click "Sauvegarder" button on "Inspection Detail" page in French
    And I click "Sauvegarder" button on "MPI" page in French
    And I click "RECOMMENDATION" button on "MPI" page in French
    Then I should see recommendation with "TestFrench" Description with "2,50" Heures with "25,50" Main-d’œuvre with "100,00" Pièces with "125,50" Prix on 'RECOMMENDATION' tab in French
    When I click "Complété" button on "MPI" page in French
    Then I see Vehicle with "<VIN>" with "pièces demandées" status in French on 'TECHNICIAN INSPECTION Dashboard'
    #Login as Parts Clerk, complete and close the mpi
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "audipartsca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Change to French version
    And I click "English (United States)" dropdown in "Technician Inspection" Dashboard
    And I select "Français (Canada)" in "language" dropdown in "Technician Inspection" Dashboard
    Then I see Vehicle with "<VIN>" with "pièces demandées" status in French on 'TECHNICIAN INSPECTION Dashboard'
    #Open MPI, mark parts in-stock, and complete the mpi
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Afficher" button on "MPI" page in French
    Then I should see "pièces demandées" status on 'MPI Details' page in French
    When I click "En stock" button with "TestFrench" Description on 'RECOMMENDATION' page in French
    And I click "Sauvegarder" button on "MPI" page in French
    And I click "Requête de pièces complétée" button on "RECOMMENDATION" tab in French
    #Login as Service Advisor
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "audiadvisorca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    And I launch 'Search popup panel' in Inspection dashboard
    And I select "Select All" in Created By drop down in Search MPI popup
    #Change to French version
    And I click "English (United States)" dropdown in "Technician Inspection" Dashboard
    And I select "Français (Canada)" in "language" dropdown in "Technician Inspection" Dashboard
    And I launch 'Search popup panel' in Inspection dashboard
    And I select "Select All" in Created By drop down in Search MPI popup
    Then I see Vehicle with "<VIN>" with "terminé" status in French on 'TECHNICIAN INSPECTION Dashboard'
    #Open the MPI and Mark review
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Afficher" button on "MPI" page in French
    Then I should see "terminé" status on 'MPI Details' page in French
    When I click "Révisé" button on "MPI" page in French
    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see Vehicle with "<VIN>" with "révision" status in French on 'TECHNICIAN INSPECTION Dashboard'
    #Open the MPI, approve the service and mark confirmed
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Afficher" button on "MPI" page in French
    Then I should see "révision" status on 'MPI Details' page in French
    When I click "approuvée" button with "TestFrench" Description on 'RECOMMENDATION' page in French
    And I click "Sauvegarder" button on "MPI" page in French
    And I click "Afficher les totaux" on 'footbar' tab on 'MPI Details' page in French
    Then I should see "RECOMMANDÉS" with "125,50$" on 'footbar' tab in French
    And I should see "APPROUVÉS" with "125,50$" on 'footbar' tab in French
    When I click "Confirmer" button on "MPI" page in French
#    And I click "vehicle queue" icon on 'MPI Details' page
    Then I see Vehicle with "<VIN>" with "confirmé" status in French on 'TECHNICIAN INSPECTION Dashboard'
    #Check History and menu buttons
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "RECOMMENDATION" button on "MPI" page in French
    And I click "Afficher l'historique" button on "MPI" page in French
    Then I should see "rapport(s) d'inspection multipoint" title on popup in French
    When I click "close" button on "Afficher l'historique" popup in French
    And I click "Journal d'événements" button on "MPI" page in French
    Then I should see "Journal d'événements" title on popup in French
    When I click "close" button on "Journal d'événements" popup in French
    And I click "Afficher le menu" button on "MPI" page in French
#    Then I should see "Audi of Anytown Canada requis par le fabricant Scheduled Maintenance Guide" on link in French
#    ** Disable this line temporarily as the function is not available yet
    #Login as Service Technician
    Given I launch browser "chrome" in "GUI" and open "<URL>"
    When I login the Application as User "auditechca" and password "welcome"
    And I click 'Click Here to Select your Dealership' on 'Home' page
    And I select Dealer with "<DealerName>" and "<DealerCode>" on DFX Page
    And I launch "Technician Inspection" Application on 'Main Page'
    #Change to French version
    And I click "English (United States)" dropdown in "Technician Inspection" Dashboard
    And I select "Français (Canada)" in "language" dropdown in "Technician Inspection" Dashboard
    Then I see Vehicle with "<VIN>" with "confirmé" status in French on 'TECHNICIAN INSPECTION Dashboard'
    # Open the MPI and move the vehicle to ready for processing
    When I click vehicle "<VIN>" on 'TECHNICIAN INSPECTION Dashboard'
    And I click "Afficher" button on "MPI" page in French
    Then I should see "confirmé" status on 'MPI Details' page in French
    #Check vehicle info card in french
    Then I should see the following French verbiage shows on "Car info" page
    |English              |French                       |
    |RO#                  |N⁰ de BR                     |
    |VIN Number           |N⁰ de NIV                    |
    |Year/Model           |Année/Modèle                 |
    |Odometer             |Kilométrage                  |
    |Customer Name        |Nom du client                |
    |Customer Phone Number|Numéro de téléphone du client|
    |Service Advisor      |Conseiller au service        |
    |INSPECTION ID        |ID D'INSPECTION              |
    And I should see "TestFrench" Description with "2,50" labor with "125,50 $" price in 'recommendations' section on 'SERVICES APPROUVÉS' tab in French
    When I click "green" toggle with "TestFrench" Description on 'SERVICES APPROUVÉS' tab in French
    And I click "Sauvegarder" button on "MPI" page in French
    And I click "Prêt à traiter" button on "MPI" page in French
    Then I see Vehicle with "<VIN>" with "Prêt à traiter" status in French on 'TECHNICIAN INSPECTION Dashboard'
    Examples: this is for data driver testing
      |URL        |DealerName            |DealerCode|TAG   |VIN              |
      |PRD2AUDI   |Audi of Anytown Canada|408A85CA  |fr01  |WAUAUGFF2H1040192|
#      |TST2AUDI   |Audi of Anytown Canada|408A85CA  |fr01  |WAUAUGFF2H1040192|
#      |STG2AUDI   |Audi of Anytown Canada|408A85CA  |fr01  |WAUAUGFF2H1040192|