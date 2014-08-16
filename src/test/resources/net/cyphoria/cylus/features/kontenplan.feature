Business Need: Als Anwender kann ich den Kontenplan ändern, d.h neue Konten hinzufügen.

  Scenario: Ein Anwender kann Konten hinzufügen
    Given ich habe den Kontenplan geöffnet
    When  ich ein neues Aufwands-Konto "4001" "Lebensmittel" anlege
    Then  wird das Konto "4001" "Lebensmittel" im Kontenplan angezeigt