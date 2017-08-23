Feature: IAS_SIND_CALC_TC01_Leasing ITK K jährlich linear DL User

  @tag1
  Scenario: Pre Requisite:
    Given Login as DL User and launch Angebot Sparkassen-Leasing application through iMAP
    When Select the Customer, Leasing Product, zeilmarkt as ITK and navigate to calculation screen
    Then Ensure that the Kalkulationsdaten,Serviceproduk,InterneKalkulation sub tabs are launched under Kalkulation tab
    And Ensure that the following frames are populated in Kalkulationsdaten sub tab Angaben zur Kalkulation, Angaben zu Zahlungen, Ratenverlauf
    And Ensure that the following fields are populated with default values Finanzprodukt, Laufzeit in Monaten, % der AfA
    And Ensure that following fields are populated with default value under the frame Angaben zu Zahlungen, Zahlungsweise, Zahlungsplan, Zinssatz, Bearbeitungsgebuhr in EUR
    And Ensure below fields are available under frame Ratenverlauf, Zahlungsverlauf with info icon, Verlangerungsrate in %, Verlangerungsrate in EUR, new button Abschlusszahlung anzeigen
    And Ensure that the following buttons are enabled in Kalkulationsdaten frame Kalkulieren, Annullieren, Historie, Notizen
    And Ensure that the following buttons are disabled in Kalkulationsdaten frame Wunschrate, Variante erstellen, Speichern, Drucken, zu den Vertragsdaten
    And Check for below values under Finanzprodukt drop down VA,TA,K
    And Select K as Finanzprodukt
    And Modify Laufzeit in Monaten and check for Mogliche Laufzeit text
