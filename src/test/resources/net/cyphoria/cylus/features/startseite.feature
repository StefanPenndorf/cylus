Feature: Die Startseite der Anwendung kann benutzt werden.

  Scenario: Die Homepage wird angezeigt
    Given die Anwendung ist gestartet
    When  ich die erste Seite aufrufe
    Then  wird der Titel "Cylus - Haushaltsbuch" angezeigt
    And   wird die Überschrift "Cylus - Haushaltsbuch" angezeigt