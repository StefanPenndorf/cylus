Feature: Die Anwendung kann als Walking Skeleton gestartet werden.

  Scenario: Ich kann die Anwendung starten und testen.
    Given die Anwendung ist gestartet
    When ich die erste Seite aufrufe
    Then kann ich "Hello Stefan" lesen

  Scenario: Die Homepage wird angezeigt
    Given die Anwendung ist gestartet
    When  ich die erste Seite aufrufe
    Then  wird der Titel "Cylus - Haushaltsbuch" angezeigt
    And   wird die Überschrift "Cylus - Haushaltsbuch" angezeigt