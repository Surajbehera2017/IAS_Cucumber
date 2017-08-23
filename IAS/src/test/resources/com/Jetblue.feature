Feature: JetBlue


  @BookHotel
  Scenario Outline: Book a Hotel
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When search for hotel and select hotel specification as follows:
      | destination                                         | checkIn    | checkOut   | rooms           |
      | San Francisco, California, United States of America | 07/10/2016 | 07/15/2016 | 1 room, 1 adult |
    And give details to checkIn and to make a payment as follows:
      | firstName | lastName | cardType | cardNo           | securityCode | expM | expY | country                  | zipCode | email          | phn        |
      | kavi      | S        | Visa     | 4111111111111111 |          458 |    8 | 2026 | United States of America |   12345 | rabi@gmail.com | 9790054636 |
    Then the hotel should be booked

    Examples:  
      | browser | scenario_name   |
      | chrome  | Book a Hotel_GC |
      | ff      | Book a Hotel_FF |

  @TravelInfo
  Scenario Outline: Travel Information
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When click on Travel Information tab
    Then the travel informations should be listed

    Examples: 
      | browser | scenario_name |
      | IE      | TravelInfo_FF |
 
  @FareAlerts
  Scenario Outline: Email Preference
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When search for email preference as follows
      | homeAirport      | firstName | lastName | dobMM | dobDD | dobY | emailAdd       |
      | Boston, MA (BOS) | xxx       | yy       |    05 |    10 | 1988 | rabi@gmail.com |
    Then the email preference should be accepted

    Examples: 
      | browser | scenario_name |
      | chrome  | FareAlerts_GC |
      | ff      | FareAlerts_FF |

  @Deals
  Scenario Outline: Deals on flight booking
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When search best travel deals
    And give travellers details and select seat as follows
      | title | firstname | lastname | dobM | dobD | dobY | add | city | country   | email          | phn        | state      | zip   | confirm_email  |
      | Miss  | kavi      | SR       | Jan  |    7 | 1981 | xxx | yyy  | tamilnadu | rabi@gmail.com | 9790054636 | California | 12345 | rabi@gmail.com |
    And give payment details as follows:
      | cardType | cardNo           | expM | expY | cardHolderName |
      | Visa     | 4111111111111111 | Jan  | 2026 | xxx            |
    Then the flight should be booked

    Examples: 
      | browser | scenario_name |
      | chrome  | Deals_GC      |
      | ff      | Deals_FF      |

  @TimeTable
  Scenario Outline: timetable
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When click on Timetable and entering search details as "<departure_City>" and "<arrival_City>"
    Then flight details should be visible to the user as "<departure_City>" and "<arrival_City>"

    Examples: 
      | browser | departure_City          | arrival_City     | scenario_name |
      | chrome  | San Francisco, CA (SFO) | Boston, MA (BOS) | TimeTable_GC  |
      | ff      | San Francisco, CA (SFO) | Boston, MA (BOS) | TimeTable_FF  |

  @Login
  Scenario Outline: Login
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When giving credentials as "<email>" and "<password>"
    Then click on signin button
    Then user should be logged in

    Examples: 
      | browser | email                  | password    | scenario_name |
      | chrome  | RabiyamaS@hexaware.com | hexaware123 | Login_GC      |
      | ff      | RabiyamaS@hexaware.com | hexaware123 | Login_FF      |

  @SuggestionEmail
  Scenario Outline: Email
    Given launch jetblue Website "<browser>" and "<scenario_name>"
    When click on contactus link and Write us an email option and select suggestion "<topic>"
    And give details as "<firstName>" and "<lastName>" and "<email>"
    Then give suggestion as "<suggestion>" and submit

    Examples: 
      | browser | email                  | topic    | firstName | lastName | suggestion                  | scenario_name      |
      | chrome  | RabiyamaS@hexaware.com | Airports | S         | kavitha  | Airport facilities are good | SuggestionEmail_GC |
      | ff      | RabiyamaS@hexaware.com | Airports | S         | kavitha  | Airport facilities are good | SuggestionEmail_FF |
