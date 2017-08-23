@tag
Feature: UPM application login scenarios
  Scenario: Pre Requisite:
    Given Login as DL User and launch Angebot Sparkassen-Leasing application through iMAP
    When Select the Customer, Leasing Product, zeilmarkt as ITK and navigate to calculation screen
    Then Ensure that the Kalkulationsdaten,Serviceproduk,InterneKalkulation sub tabs are launched under Kalkulation tab
    And Ensure that the following frames are populated in Kalkulationsdaten sub tab Angaben zur Kalkulation, Angaben zu Zahlungen, Ratenverlauf
